package org.qmbupt.grp105.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.LiveSession;

import java.util.ArrayList;
import java.util.List;


public class BookLiveSessionTest {
    LiveController liveController;
    PersonalController personalController;
    String username = "hendrikdepeuter@outlook.com";
    String pass = "Hendrik021";
    String sessionId;
    LiveSession liveSession;
    Customer customer;
    ArrayList<String> sessions;
    ArrayList<LiveSession> liveSessions;
    @Before
    public void setUp() throws Exception {
        personalController = PersonalController.getController();
        liveController = LiveController.getController();
        customer = personalController.getCusInfoByCusId(personalController.getIdByEmail(username));
        sessions = customer.getBookedSessions();

        List<String> c = new ArrayList<>();
        c.add("Bicycle Training");
        c.add("Flexibility");
        c.add("Weight Loss");
        liveSessions = liveController.filterSessionByCategory(liveController.getAllLiveSessions(), c);
        sessionId = liveSessions.get(0).getLiveSessionId();
        liveSession = liveSessions.get(0);
    }
    @Test
    public void SearchLiveSession() {
        System.out.println("Begin SearchLiveSession()");
        assert personalController.check(username, pass) == 1;
        System.out.println("Customer Info:");
        System.out.println(customer.toString());
        System.out.println("Search Keywords: Bicycle Training, Flexibility, Weight Loss");
        System.out.println("LiveSession Info:");
        for(LiveSession lvs: liveSessions) {
            System.out.println(lvs.toString());
        }
        sessionId = liveSessions.get(0).getLiveSessionId();
        if(!sessions.contains(sessionId)) {
            System.out.println(customer.getName() + " have not booked the session " + liveSession.getLiveSessionId());
        }
        else {
            System.out.println(customer.getName() + " have booked the session " + liveSession.getLiveSessionId());
        }
    }

    @Test
    public void BookLiveSession() {

        System.out.println("Begin BookLiveSession()");
        if(!sessions.contains(sessionId)) {
            personalController.bookLiveSession(customer.getCusId(),sessionId);
            sessions = customer.getBookedSessions();
            assert (sessions.contains(sessionId));
            System.out.println(customer.getName() + " booked the session " + liveSession.getLiveSessionId() + " successfully!");

        }
    }


}
