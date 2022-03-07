package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.SessionManager;
import org.qmbupt.grp105.backend.model.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Version 5.3
 * @author Wenrui Zhao
 */
public interface LiveUtils {

    public ArrayList<LiveSession> getLiveSessionByCusId(String cusId);
    public ArrayList<LiveSession> getLiveSessionByCoachId(String coId);
    public ArrayList<LiveSession> getAllLiveSessions();
    public LiveSession getLiveSessionBySessionId(String liveSessionId);
    public ArrayList<LiveSession> getSessionsByCoach(String keyword);
    public ArrayList<LiveSession> filterSessionByExpire(ArrayList<LiveSession> sessions, boolean expired);
    public ArrayList<LiveSession> filterSessionByCategory(ArrayList<LiveSession> sessions, List<String> category);
    public void addSession(LiveSession liveSession);

}
