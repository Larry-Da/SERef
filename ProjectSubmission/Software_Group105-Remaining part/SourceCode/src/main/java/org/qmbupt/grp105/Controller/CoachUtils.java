package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.Coach;

/**
 * @Version 5.3
 * @author Wenrui Zhao
 */
public interface CoachUtils {

    public Coach getCoachInfoById(String coId);
    public void updateCoach(Coach coach);
}