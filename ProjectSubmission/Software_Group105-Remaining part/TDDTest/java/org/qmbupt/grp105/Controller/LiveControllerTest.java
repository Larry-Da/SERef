package org.qmbupt.grp105.Controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.qmbupt.grp105.Entity.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LiveControllerTest {

    LiveController liveController;

    @Before
    public void setUp() throws Exception {
        liveController = LiveController.getController();
    }

    @Test
    public void getAllLiveSessions() {
        System.out.println("getAllLiveSessions");
        ArrayList<LiveSession> liveSessions = new ArrayList<>();
        liveSessions = liveController.getAllLiveSessions();
        for (LiveSession liveSession : liveSessions) {
            System.out.println(liveSession.toString());
        }
    }

    @Test
    public void getLiveSessionBySessionId() {
        System.out.println("getLiveSessionBySessionId: lvs10");
        LiveSession liveSession = liveController.getLiveSessionBySessionId("lvs10");
        System.out.println(liveSession.toString());
    }

    @Test
    public void getLiveSessionByCoachId() {
        System.out.println("getLiveSessionByCoachId: co5");
        ArrayList<LiveSession> liveSessions = liveController.getLiveSessionByCoachId("co5");
        for(LiveSession lvs: liveSessions) {
            System.out.println(lvs.toString());
        }
    }

    @Test
    public void getLiveSessionByCusId() {
        System.out.println("getLiveSessionByCusId: cs2");
        ArrayList<LiveSession> liveSessions = liveController.getLiveSessionByCusId("cs2");
        for(LiveSession lvs: liveSessions) {
            System.out.println(lvs.toString());
        }
    }

    @Test

    public void filterSessionByCategory() {
        System.out.println("filterSessionByCategory");
        List<String> c = new ArrayList<>();
        c.add("Bicycle Training");
        c.add("Flexibility");
        c.add("Weight Loss");
        ArrayList<LiveSession> liveSessions = liveController.filterSessionByCategory(liveController.getAllLiveSessions(), c);
        for(LiveSession lvs: liveSessions) {
            System.out.println(lvs.toString());
        }
    }
}

