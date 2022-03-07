package org.qmbupt.grp105.backend.dblayer;

import org.qmbupt.grp105.backend.model.Coach;

import java.io.IOException;
import java.util.ArrayList;

/**
 * CoachManager is for managing coaches
 * @author Lingsong Feng
 * @version 5.3
 */
public class CoachManager {

    /**
     * get a Coach object by its id
     * @param coachId
     * @return the reference of that Coach object
     * @throws IOException
     */
    public static Coach getCoachById(String coachId) throws IOException {
        ArrayList<Coach> coaches = DataManager.getInstance().coaches;
        for (Coach coach : coaches) {
            if (coach.coachId.equals(coachId)) {
                return coach;
            }
        }
        return null;
    }

    
    /**
     * return an array of coaches
     * @return array of coaches
     * @throws IOException
     */
    public static ArrayList<Coach> getAllCoaches() throws IOException {
        return DataManager.getInstance().coaches;
    }

    /**
     * write (add and modify) coach information into file
     * @param coach
     * @throws IOException
     */
    public static void writeCoachInfo(Coach coach) throws IOException {
        ArrayList<Coach> coaches = DataManager.getInstance().coaches;
        for (int i = 0; i < coaches.size(); i++) {
            if (coaches.get(i).coachId.equals(coach.coachId)) {
                coaches.remove(i);
                coaches.add(coach);
                DataManager.getInstance().commit();
                return;
            }
        }
        coaches.add(coach);
        DataManager.getInstance().commit();
    }

}
