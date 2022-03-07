package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.Coach;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.backend.dblayer.CoachManager;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.DataManager;
import org.qmbupt.grp105.backend.dblayer.VideoManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Version 5.3
 * @author Wenrui Zhao
 */
public interface CustomerUtils
{
    public Customer getCusInfoByCusId(String cusId);
    public Customer getCusInfoByName(String name);
    public void updateCustomer(Customer customer);
    public void increaseBalance(String cusId, int num);
    public void decreaseBalance(String cusId, int num);
    public boolean bookLiveSession(String cusId, String sessionId);
    public void removeBookedSession(String cusId, String sessionId);
    public void watchVideo(String cusId, String videoId);
    public void addToFavourite(String cusId, String videoId);
    public void removeFromFavourite(String cusId, String videoId);
    public String getExpireTimeByCusId(String cusId);
    public ArrayList<Video> getFavouriteVideoByCusId(String cusId);
    public void extendMembership(String cusId);
}
