package org.qmbupt.grp105.Controller;

import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.Entity.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonalControllerTest {

    PersonalController personalController;
    @Before
    public void setUp() throws Exception {
        personalController = PersonalController.getController();
    }

    @Test
    public void getAllCustomer() {
        System.out.println("begin to test getAllCustomer()");
        ArrayList<Customer> customers = new ArrayList<>();
        customers = personalController.getAllCustomer();
        System.out.println("All customers are as follows");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("test for getAllCustomer() end");
        System.out.println("");
    }

    @Test
    public void getCusInfoByCusId() {
        System.out.println("begin to test getCusInfoByCusId()");

        Customer customer = personalController.getCusInfoByCusId("cs2");
        System.out.println(customer.toString());
        System.out.println("test for getACusInfoByCusId() end");
        System.out.println("");
    }



    @Test
    public void getCusInfoByName() {
        System.out.println("begin to test getCusInfoByName()");
        Customer customer = personalController.getCusInfoByName("Jose Nunes");
        System.out.println(customer.toString());
        System.out.println("test for getCusInfoByName() end");
        System.out.println("");
    }

    @Test
    public void getCusByGender() {
        System.out.println("begin to test getCusInfoByGender()");
        ArrayList<Customer> customers = new ArrayList<>();
        customers = personalController.getCusByGender('M');
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("test for getCusByGender() end");
        System.out.println("");
    }

    @Test
    public void getCusNumByLevel() {
        System.out.println("begin to test getCusNumByLevel()");
        System.out.println(personalController.getCusNumByLevel("3"));
        System.out.println("test for getCusNumByLevel() end");
        System.out.println("");
    }

//    @Test
//    public void getMonthlyIncome() {
//        System.out.println("begin to test getMonthlyIncome()");
//        System.out.println(personalController.getMonthlyIncome(2));
//        System.out.println("test for getMonthlyIncome() end");
//        System.out.println("");
//    }

    @Test
    public void getExpireTimeByCusId() {
        System.out.println("begin to test getExpireTimeByCusId()");
        System.out.println(personalController.getExpireTimeByCusId("cs32"));
        System.out.println("test for getExpireTimeByCusId() end");
        System.out.println("");
    }
    @Test
    public void filterByKeyword() {
        ArrayList<Customer> customers = personalController.getAllCustomer();
        ArrayList<Customer> res = personalController.filterByKeyword(customers, "cs");
        for(Customer cus : res) {
            System.out.println(cus.toString());
        }
    }
    @Test
    public void filterByGender() {
        ArrayList<Customer> customers = personalController.getAllCustomer();
        ArrayList<Customer> res = personalController.filterByGender(customers, 'M');
        for(Customer cus : res) {
            System.out.println(cus.toString());
        }
    }
    @Test
    public void filterByCusLevel() {
        ArrayList<Customer> customers = personalController.getAllCustomer();
        ArrayList<String> level = new ArrayList<>();
        level.add("3");
        level.add("9");
        ArrayList<Customer> res = personalController.filterByCusLevel(customers, level);
        for(Customer cus : res) {
            System.out.println(cus.toString());
        }
    }


    @Test
    public void check() {
        Customer cus = personalController.getCusInfoByCusId("cs1");
        System.out.println("Wrong case!");
        System.out.println(personalController.check(cus.getEmail(), "123"));
        assert personalController.check(cus.getEmail(), cus.getPassword()) == 1;
        System.out.println(personalController.check(cus.getEmail(), cus.getPassword()));

    }

    @Test
    public void updateCustomer() {
        Customer customer = personalController.getCusInfoByCusId("cs1");
        customer.setName("Iceberg");
        System.out.println("before changed");
        System.out.println(customer.toString());
        personalController.updateCustomer(customer);
        System.out.println("after changed");
        assert personalController.getCusInfoByCusId("cs1").getName().equals("Iceberg");
        System.out.println(customer.toString());
    }

    @Test
    public void increaseBalance() {
        int balance = personalController.getCusInfoByCusId("cs1").getBalance();
        System.out.println(personalController.getCusInfoByCusId("cs1").toString());
        personalController.increaseBalance("cs1", 1000);
        System.out.println("after changed");
        assert balance + 1000 == personalController.getCusInfoByCusId("cs1").getBalance();
        System.out.println(personalController.getCusInfoByCusId("cs1").getBalance());
    }

    @Test
    public void decreaseBalance() {
        int balance = personalController.getCusInfoByCusId("cs1").getBalance();
        System.out.println(personalController.getCusInfoByCusId("cs1").toString());
        personalController.decreaseBalance("cs1", 800);
        System.out.println("after changed");
        assert balance - 800 == personalController.getCusInfoByCusId("cs1").getBalance();
        System.out.println(personalController.getCusInfoByCusId("cs1").getBalance());
    }

    @Test
    public void bookedLiveSession() {
        System.out.println("Live sessions for cs1");
        ArrayList<String> livesession = personalController.getCusInfoByCusId("cs1").getBookedSessions();
        for(String sid: livesession) {
            System.out.println(sid);
        }
        personalController.bookLiveSession("cs1", "lvs4");
        System.out.println("after changed");
        livesession = personalController.getCusInfoByCusId("cs1").getBookedSessions();
        assert livesession.contains("lvs4");
        for(String sid: livesession) {
            System.out.println(sid);
        }
    }
    @Test
    public void removeBookedSession() {
        System.out.println("Remove Booked Session for cs1");
        System.out.println("Before removal");
        ArrayList<String> livesession = personalController.getCusInfoByCusId("cs1").getBookedSessions();
        for(String sid: livesession) {
            System.out.println(sid);
        }
        personalController.removeBookedSession("cs1","lvs4");
        System.out.println("after changed");
        livesession = personalController.getCusInfoByCusId("cs1").getBookedSessions();
        assert !livesession.contains("lvs4");
        for(String sid: livesession) {
            System.out.println(sid);
        }
    }
    @Test
    public void watchVideo() {
        System.out.println("Video history for cs1");
        ArrayList<String> videoIds = personalController.getCusInfoByCusId("cs1").getVideosHistory();
        for(String sid: videoIds) {
            System.out.println(sid);
        }
        personalController.watchVideo("cs1","v005");
        System.out.println("after changed");
        videoIds = personalController.getCusInfoByCusId("cs1").getVideosHistory();
        assert videoIds.contains("v005");
        for(String sid: videoIds) {
            System.out.println(sid);
        }
    }

    @Test
    public void addToFavourite() {
        System.out.println("Favourite videos for cs1");
        ArrayList<String> videoIds = personalController.getCusInfoByCusId("cs1").getFavouriteVideos();
        for(String sid: videoIds) {
            System.out.println(sid);
        }
        personalController.addToFavourite("cs1","v004");
        System.out.println("after changed");
        videoIds = personalController.getCusInfoByCusId("cs1").getFavouriteVideos();
        assert videoIds.contains("v004");
        for(String sid: videoIds) {
            System.out.println(sid);
        }
    }

    @Test
    public void removeFromFavourite() {
        System.out.println("Favourite videos for cs1");
        ArrayList<String> videoIds = personalController.getCusInfoByCusId("cs1").getFavouriteVideos();
        for(String sid: videoIds) {
            System.out.println(sid);
        }
        personalController.removeFromFavourite("cs1","v004");
        System.out.println("after changed");
        videoIds = personalController.getCusInfoByCusId("cs1").getFavouriteVideos();
        assert !videoIds.contains("v004");
        for(String sid: videoIds) {
            System.out.println(sid);
        }
    }

    @Test
    public void getAllCoaches() {
        ArrayList<Coach> coaches = personalController.getAllCoaches();
        for(Coach c: coaches) {
            System.out.println(c.toString());
        }
    }

    @Test
    public void getCoachById() {
        System.out.println(personalController.getCoachInfoById("co1").toString());
    }

    @Test
    public void getIdByEmail() {
        System.out.println(personalController.getIdByEmail("andrecruz@gmail.com"));
    }

    @Test
    public void extendMembership() {
        System.out.println("ExpireTime for cs1:" + personalController.getCusInfoByCusId("cs1").getExpiredTime());
        personalController.extendMembership("cs1");
        System.out.println("after changed" + personalController.getCusInfoByCusId("cs1").getExpiredTime());
    }
 }