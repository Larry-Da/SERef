package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.qmbupt.grp105.backend.model.Customer;
import org.qmbupt.grp105.backend.model.Session;
import org.qmbupt.grp105.backend.model.Transaction;

/**
 * CustomerManager is for managing customers
 * @author Lingsong Feng
 * @version 5.3
 */
public class CustomerManager {

    /**
     * about balance
     */
    /**
     * increase customer's balance
     * @param cusId
     * @param addition
     * @return the actual balance increased. If the value doesn't match the addition, it indicates the operation failed.
     * @throws IOException
     */
    public static int increaseBalance(String cusId, int addition) throws IOException {

        if (addition <= 0) {
            return 0;
        }

        Customer customer = getCustomerById(cusId);

        customer.balance += addition;

        DataManager.getInstance().commit();

        return addition;
    }

    /**
     * decrease customer's balance
     * @param cusId
     * @param reduction
     * @return the actual balance decreased. If the value doesn't match the reduction, it indicates the operation failed.
     * @throws IOException
     */
    public static int decreaseBalance(String cusId, int reduction) throws IOException {

        if (reduction <= 0) {
            return 0;
        }

        Customer customer = getCustomerById(cusId);

        if (customer.balance < reduction) {
            return 0;
        }
        customer.balance -= reduction;

        DataManager.getInstance().commit();

        return reduction;
    }



    /**
     * about videos
     */
    /**
     * add a video to customer's videosHistory
     * @param cusId
     * @param videoId
     * @throws IOException
     */
    public static void addVideoHistory(String cusId, String videoId) throws IOException {

        Customer customer = getCustomerById(cusId);

        customer.videosHistory.add(videoId);

        DataManager.getInstance().commit();
    }

    /**
     * watching a video involves (1) increasing viewsCount of a video and (2) add that video to customer's videosHistory
     * @param cusId
     * @param videoId
     * @throws IOException
     */
    public static void watchVideo(String cusId, String videoId) throws IOException {

        VideoManager.getVideoById(videoId).viewsCount++;
        addVideoHistory(cusId, videoId);
        DataManager.getInstance().commit();
    }

    /**
     * add a video to favorites
     * @param cusId
     * @param videoId
     * @throws IOException
     */
    public static void addFavoriteVideo(String cusId, String videoId) throws IOException {
        Customer customer = getCustomerById(cusId);
        for (int i = 0; i < customer.favoriteVideos.size(); i++) {
            if (customer.favoriteVideos.get(i).equals(videoId)) {
                customer.favoriteVideos.remove(i);
                break;
            }
        }
        customer.favoriteVideos.add(videoId);
        DataManager.getInstance().commit();
    }

    /**
     * remove a stored video in customer's favorite videos
     * @param cusId
     * @param videoId
     * @throws IOException
     */
    public static void removeFavoriteVideo(String cusId, String videoId) throws IOException {
        Customer customer = getCustomerById(cusId);
        for (int i = 0; i < customer.favoriteVideos.size(); i++) {
            if (customer.favoriteVideos.get(i).equals(videoId)) {
                customer.favoriteVideos.remove(i);
                break;
            }
        }
        DataManager.getInstance().commit();
    }



    /**
     * about sessions
     */
    /**
     * book a specified session for a customer
     * @param cusId
     * @param sessionId
     * @throws IOException
     */
    public static boolean bookSession(String cusId, String sessionId) throws IOException {

        Session session = SessionManager.getSessionById(sessionId);
        int reduceMount = session.price;

        Customer customer = getCustomerById(cusId);
        if ( customer.dateOfBirth.getDate() == new Date().getDate() && customer.dateOfBirth.getMonth() == new Date().getMonth() ) {
            reduceMount *= 0.9;
        }


        if (SessionManager.getSessionById(sessionId).availableNum == 0) {
            return false;
        }

        {
            ArrayList<String> sessionIds = getCustomerById(cusId).bookedSessions;
            for (int i = 0; i < sessionIds.size(); i++) {
                if (sessionIds.get(i).equals(sessionId)) {
                    return false;
                }
            }
        }

        /**
         * Try to decrease balance
         */
        if (decreaseBalance(cusId, reduceMount) != reduceMount) {
            return false;
        }

        SessionManager.getSessionById(sessionId).availableNum--;

        /**
         * Create and add transaction
         */
        Transaction transaction = new Transaction();
        transaction.transactionId = null;
        transaction.cusId = cusId;
        transaction.mount = reduceMount;
        transaction.time = new Date();
        TransactionManager.writeTransaction(transaction);

        customer.bookedSessions.add(sessionId);
        DataManager.getInstance().commit();

        return true;
    }

    /**
     * about menbership
     */
    /**
     * extend customer's membership for 30 days by reducing 100 of balance
     * @param cusId
     * @return does operation succeed
     */
    public static boolean extendMembership(String cusId) throws IOException {
        int reduceMount = 100;

        /**
         * Try to decrease balance
         */
        if (decreaseBalance(cusId, reduceMount) != reduceMount) {
            return false;
        }

        /**
         * Create and add transaction
         */
        Transaction transaction = new Transaction();
        transaction.transactionId = null;
        transaction.cusId = cusId;
        transaction.mount = reduceMount;
        transaction.time = new Date();
        TransactionManager.writeTransaction(transaction);

        /**
         * Calculate the next expired time
         */
        Date prexiousExpireTime = getCustomerById(cusId).expireDate;
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        if (prexiousExpireTime.before(now)) {
            calendar.setTime(now);
        } else {
            calendar.setTime(prexiousExpireTime);
        }
        calendar.add(Calendar.DATE, 30);
        Date nextTime = calendar.getTime();
        getCustomerById(cusId).expireDate = nextTime; // Modify the expired time
        DataManager.getInstance().commit(); // commit changes


        return true;
    }



    /**
     * basic operations
     */
    /**
     * get a MUTABLE reference of a customer specified by cusId
     * @param cusId
     * @return reference of that customer
     * @throws IOException
     */
    public static Customer getCustomerById(String cusId) throws IOException {
        ArrayList<Customer> customers = DataManager.getInstance().customers;
        for (Customer customer : customers) {
            if (customer.cusId.equals(cusId)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * create or modify a customer's information
     * @param customer
     * @throws IOException
     */
    public static void writeCustomerInfo(Customer customer) throws IOException {
        ArrayList<Customer> customers = DataManager.getInstance().customers;
        boolean isDuplicated = false;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).cusId.equals(customer.cusId)) {
                customers.remove(i);
                customers.add(customer);
                DataManager.getInstance().commit();
                return;
            }
        }
        customers.add(customer);
        DataManager.getInstance().commit();
    }

}
