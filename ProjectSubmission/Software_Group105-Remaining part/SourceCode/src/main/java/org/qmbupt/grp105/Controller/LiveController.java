package org.qmbupt.grp105.Controller;
import org.qmbupt.grp105.Entity.*;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.SessionManager;
import org.qmbupt.grp105.backend.model.Session;

import java.io.IOException;
import java.util.*;

/**
 * The controller used for Live Session Operations
 * @version 5.3
 * @author Jinghao Lyu
 */
public class LiveController implements LiveUtils{

    private static LiveController liveController = new LiveController();
    private LiveController() {}
    public static LiveController getController(){
        return liveController;
    }

    /**
     * Get Live Sessions' information by Customer's ID
     * @param cusId Customer's ID
     * @return the searched result of a Live Session list
     */
    public ArrayList<LiveSession> getLiveSessionByCusId(String cusId) {
        ArrayList<LiveSession> res = new ArrayList<>();
        try {
            Customer customer = CustomerManager.getCustomerById(cusId).converter();
            for(String s: customer.getBookedSessions()) {
                res.add(SessionManager.getSessionById(s).converter());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Get Live Sessions' information by Coach's ID
     * @param coId Coach's ID
     * @return the searched result of a Live Session list
     */
    public ArrayList<LiveSession> getLiveSessionByCoachId(String coId)
    {
        ArrayList<LiveSession> sessions = getAllLiveSessions();
        ArrayList<LiveSession> res = new ArrayList<>();
        for(LiveSession i : sessions)
        {
            if(i.getCoach_coachId().equals(coId))
            {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * Get all the live Sessions' information without any filters
     * @return the list of all the live session data
     */
    public ArrayList<LiveSession> getAllLiveSessions() {
        ArrayList<Session> sessions = null;
        ArrayList<LiveSession> res = new ArrayList<>();
        try {
            sessions = SessionManager.getAllSessions();
            for(Session s: sessions) {
                res.add(s.converter());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Get a specified Live Session's information of a particular id
     * @param liveSessionId Live Session's ID
     * @return the searched result of the Live Session
     */
    public LiveSession getLiveSessionBySessionId(String liveSessionId) {
        LiveSession liveSession = null;
        try {
            liveSession = SessionManager.getSessionById(liveSessionId).converter();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return liveSession;
    }

    /**
     * Get Live Sessions' information by Coach's name
     * @param keyword the keyword related to coaches' names
     * @return the searched result of a Live Session list
     */
    public ArrayList<LiveSession> getSessionsByCoach(String keyword) {
        ArrayList<LiveSession> liveSessions = getAllLiveSessions();
        if(keyword == null)
            return liveSessions;
        ArrayList<LiveSession> res = new ArrayList<>();
        for(LiveSession s: liveSessions) {
            if(s.getCoach_coachId().contains(keyword) || s.getCategory().contains(keyword)) {
                res.add(s);
            }
        }
        return res;
    }

    /**
     * Filter a list of Live Sessions by judging whether the live session has been expired or not
     * @param sessions the list of live sessions needed for filtration
     * @param expired true for keeping expired live session, false for keeping live session not expired
     * @return the filtered result of a (expired/unexpired) Live Session list
     */
    public ArrayList<LiveSession> filterSessionByExpire(ArrayList<LiveSession> sessions, boolean expired) {
        ArrayList<LiveSession> res = new ArrayList<>();
        if(expired)
        {
            for(LiveSession l: sessions)
            {
                Date now = new Date(System.currentTimeMillis());
                if(now.after(l.getStartTime()))
                {
                    res.add(l);
                }
            }
        }
        else
        {
            for(LiveSession l: sessions)
            {
                Date now = new Date(System.currentTimeMillis());
                if(now.before(l.getStartTime()))
                {
                    res.add(l);
                }
            }
        }
        return res;
    }

    /**
     * Get Live Sessions' information by a list of category's name
     * @param sessions the list of live sessions needed for filtration
     * @param category the list of category's name that works as a filter key
     * @return the filtered result of a Live Session list
     */
    public ArrayList<LiveSession> filterSessionByCategory(ArrayList<LiveSession> sessions, List<String> category) {
        ArrayList<LiveSession> sessions1 = null;
        ArrayList<LiveSession> res = new ArrayList<>();

        if(sessions == null) {
            sessions1 = getAllLiveSessions();
        }
        else {
            sessions1 = sessions;
        }
        if(category.size() == 0)
            return sessions1;
        for(LiveSession s: sessions1) {
            if(category.contains(s.getCategory())) {
                res.add(s);
            }
        }
        return res;
    }

    /**
     * Call the function in the SessionManger class to add a new live session with auto-generated session ID
     * @param liveSession the live session information
     */
    public void addSession(LiveSession liveSession) {
        try {
            SessionManager.addSession(liveSession.converter());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}