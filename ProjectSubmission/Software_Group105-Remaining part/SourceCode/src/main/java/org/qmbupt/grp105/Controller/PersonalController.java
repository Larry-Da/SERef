package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.*;

import java.io.IOException;
import java.util.*;

import org.qmbupt.grp105.backend.dblayer.CoachManager;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.DataManager;
import org.qmbupt.grp105.backend.dblayer.VideoManager;

/**
 * @version 5.3
 * @author Wenrui Zhao
 */
public class PersonalController implements CustomerUtils, AdminUtils, CoachUtils{

    private static PersonalController personalController = new PersonalController();
    private PersonalController() {};
    public  static PersonalController getController()
    {
        return personalController;
    }

    /**
     * <p>
     *     Filter customers based on keywords, if a customer's name or id has
     *     the keyword, it will be collected.
     * </p>
     * @param customers A list of customers
     * @param key any keyword
     * @return A list of customers who have the keyword
     */
    public ArrayList<Customer> filterByKeyword(ArrayList<Customer> customers, String key)
    {
        ArrayList<Customer> res = new ArrayList<>();
        if(key == null)
            return customers;
        for(Customer i : customers)
        {
            if(i.getName().contains(key) || i.getCusId().contains(key))
            {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * <p>
     *     Filter all customers based on gender
     * </p>
     * @param customers A list of customers
     * @param gender Gender ('M' for male and 'F' for female)
     * @return A list of customers who have the same gender
     */
    public ArrayList<Customer> filterByGender(ArrayList<Customer> customers, char gender)
    {
        ArrayList<Customer> res = new ArrayList<>();
        if(gender == 'n')
            return customers;
        for(Customer i: customers)
        {
            if(gender == i.getGender())
                res.add(i);
        }
        return res;
    }

    /**
     * <p>
     *     Filter the customers by level
     * </p>
     * @param customers A list of customers
     * @param levelKeys a certain level (e.g. 2)
     * @return A list of customers who have the same level
     */
    public ArrayList<Customer> filterByCusLevel(ArrayList<Customer> customers,ArrayList<String> levelKeys)
    {
        ArrayList<Customer> res = new ArrayList<>();
        if(levelKeys.size() == 0)
            return customers;
        if(levelKeys.get(0).equals("l"))
            return customers;
        for(Customer i: customers)
        {
            if(levelKeys.contains(i.getMembershipLevel()))
                res.add(i);
        }
        return res;
    }


    /**
     * <p>
     *     Get all information of all customers
     * </p>
     * @return A List of all customers
     */
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<org.qmbupt.grp105.backend.model.Customer> customers = null;
        try {
            customers = DataManager.getInstance().customers;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<Customer> res = new ArrayList<>();
        for(int i = 0; i < customers.size(); i++)
        {
            res.add(customers.get(i).converter());
        }
        return res;
    }


    /**
     * <p>
     *     Get all information of a given customer
     * </p>
     * @param cusId customer ID (e.g. cusId:"cs2")
     * @return  A customer entity
     */
    public Customer getCusInfoByCusId(String cusId) {
        try {
            return CustomerManager.getCustomerById(cusId).converter();
        }

        catch (Exception e){

        }
        return null;
    }

    /**
     * <p>
     *     Get all information of a given coach
     * </p>
     * @param coId coach ID (e.g. coId:"co3")
     * @return A coach entity
     */
    public Coach getCoachInfoById(String coId)
    {
        ArrayList<Coach> coaches = this.getAllCoaches();
        for(Coach i : coaches)
        {
            if(i.getCoachId().equals(coId))
                return i;
        }
        return null;

    }



    /**
     * <p>
     *     Get the customer by his name
     * </p>
     * @param name Customer name
     * @return A customer entity
     */
    public Customer getCusInfoByName(String name) {
        ArrayList<Customer> customers = getAllCustomer();
        for(Customer customer : customers) {
            if(customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * <p>
     *     Get a list of customers of the same gender
     * </p>
     * @param gender male or female
     * @return a list of customers
     */
    public ArrayList<Customer> getCusByGender(char gender) {
        ArrayList<Customer> customers = getAllCustomer();
        ArrayList<Customer> cuslist = new ArrayList<>();
        for(Customer customer : customers) {
            if(customer.getGender() == gender) {
                cuslist.add(customer);
            }
        }
        return cuslist;
    }

    /**
     * <p>
     *     Get the number of customer for a given membership level
     * </p>
     * @param level A specific level (e.g. level:"1")
     * @return Number of customer
     */
    public int getCusNumByLevel(String level) {
        ArrayList<Customer> customers = getAllCustomer();
        int num = 0;
        for(Customer customer : customers) {
            if(customer.getMembershipLevel().equalsIgnoreCase(level)) {
                num++;
            }
        }
        return num;
    }

    /**
     * <p>
     *     Check the username and the password
     * </p>
     * @param username username
     * @param pass password
     * @return Return 1 for legal customer, 4 for illegal customer(wrong password) and illegal coach(wrong password), 2 for legal coach, 3 for non-existed customer and coach
     */
    public int check(String username, String pass) {
        try {
            if(CustomerManager.getCustomerById(getIdByEmail(username)) == null) {
                if (CoachManager.getCoachById(getIdByEmail(username)) == null) {
                    return 3;
                }
                else {
                    Coach coach = CoachManager.getCoachById(getIdByEmail(username)).converter();
                    if(!coach.getPassword().equals(pass)) {
                        return 4;
                    }
                    else {
                        return 2;
                    }
                }
            }
            else {
                Customer customer = CustomerManager.getCustomerById(getIdByEmail(username)).converter();
                if(!customer.getPassword().equals(pass)) {
                    return 4;
                }
                else {
                    return 1;
                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * <p>
     *     Update coach information
     * </p>
     * @param coach A coach entity
     */
    public void updateCoach(Coach coach)
    {
        try {
            CoachManager.writeCoachInfo(coach.convert());
        }
        catch (Exception e)
        {

        }
    }

    /**
     * <p>
     *     Update customer information
     * </p>
     * @param customer A customer entity
     */
    public void updateCustomer(Customer customer) {
        try {
            if(customer.getCusId().equals(""))
            {
                int num = getAllCustomer().size();
                String id = "cs" + (num + 1);
                customer.setCusId(id);
            }
            CustomerManager.writeCustomerInfo(customer.convert());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     Increase a specific customer's balance
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param num Amount of money
     */
    public void increaseBalance(String cusId, int num) {
        try {
            CustomerManager.increaseBalance(cusId, num);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * <p>
     *     Decrease a specific customer's balance
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param num Amount of money
     */
    public void decreaseBalance(String cusId, int num) {
        try {
            CustomerManager.decreaseBalance(cusId, num);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>
     *     Book specific live sessions of a given customer
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param sessionId Live session ID (e.g. sessionId="lvs1")
     * @return True for booking successfully, False for booking unsuccessfully
     */
    public boolean bookLiveSession(String cusId, String sessionId) {
        boolean res = false;
        try {
            res = CustomerManager.bookSession(cusId, sessionId);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * <p>
     *     Remove specific live sessions of a given customer
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param sessionId Live session ID (e.g. sessionId="lvs1")
     */
    public void removeBookedSession(String cusId, String sessionId) {
        try {
            Customer customer = getCusInfoByCusId(cusId);
            ArrayList<String> sessions = customer.getBookedSessions();
            if(sessions.contains(sessionId)) {
                sessions.remove(sessionId);
                customer.setBookedSessions(sessions);
            }
            CustomerManager.writeCustomerInfo(customer.convert());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     Watch a specific video
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param videoId Video ID (e.g. videoId="v001")
     */
    public void watchVideo(String cusId, String videoId) {
        try {
            Customer customer = CustomerManager.getCustomerById(cusId).converter();
            if(!customer.getVideosHistory().contains(videoId)) {
                CustomerManager.watchVideo(cusId,videoId);
            }
            else {
                int i = 0;
                int index = customer.getVideosHistory().indexOf(videoId);
                for(i = index; i < customer.getVideosHistory().size() - 1; i++) {
                    customer.getVideosHistory().set(i, customer.getVideosHistory().get(i + 1));
                }
                customer.getVideosHistory().set(i, videoId);
                updateCustomer(customer);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     Add the video to personal favourite list
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param videoId Video ID (e.g. videoId="v001")
     */
    public void addToFavourite(String cusId, String videoId) {
        try {
            CustomerManager.addFavoriteVideo(cusId, videoId);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     Remove the video from personal favourite list
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @param videoId Video ID (e.g. videoId="v001")
     */
    public void removeFromFavourite(String cusId, String videoId) {
        try {
            CustomerManager.removeFavoriteVideo(cusId, videoId);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * <p>
     *     Get the expire time of a given customer' membership
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @return  Expire time of a given customer
     */
    public String getExpireTimeByCusId(String cusId) {
        Customer customer = getCusInfoByCusId(cusId);
        return customer.getExpiredTime();
    }

    /**
     * <p>
     *     Get the favourite video list of a given customer
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     * @return A favourite video list of a given customer
     */
    public ArrayList<Video> getFavouriteVideoByCusId(String cusId) {
        Customer customer = getCusInfoByCusId(cusId);
        ArrayList<Video> res = new ArrayList<>();
        try {
            for(String s: customer.getFavouriteVideos()) {
                res.add(VideoManager.getVideoById(s).converter());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;

    }

    /**
     * <p>
     *     Get all information of all coaches
     * </p>
     * @return A list of all coaches
     */
    public ArrayList<Coach> getAllCoaches() {
        ArrayList<org.qmbupt.grp105.backend.model.Coach> coaches = new ArrayList<>();
        ArrayList<Coach> res = new ArrayList<>();
        try {
            coaches = CoachManager.getAllCoaches();
            for(org.qmbupt.grp105.backend.model.Coach c: coaches) {
                res.add(c.converter());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * <p>
     *     Get customer's ID or coach's ID by his email
     * </p>
     * @param email Customer's or coach's email
     * @return Customer ID or coach ID
     */
    public String getIdByEmail(String email)
    {
        ArrayList<Customer> customers = getAllCustomer();
        for(Customer c: customers) {
            if(c.getEmail().equals(email)) {
                return c.getCusId();
            }
        }
        ArrayList<Coach> coaches = getAllCoaches();
        for(Coach c: coaches) {
            if(c.getEmail().equals(email)) {
                return c.getCoachId();
            }
        }
        return null;
    }

    /**
     * <p>
     *     Extend the membership
     * </p>
     * @param cusId Customer ID (e.g. cusId="cs2")
     */
    public void extendMembership(String cusId) {
        try {
            CustomerManager.extendMembership(cusId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        PersonalController personalController = PersonalController.getController();
        personalController.watchVideo("cs5","v001");
    }

}
