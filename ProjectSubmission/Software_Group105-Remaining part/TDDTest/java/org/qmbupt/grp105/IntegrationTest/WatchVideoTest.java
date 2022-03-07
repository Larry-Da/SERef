package org.qmbupt.grp105.IntegrationTest;

import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.Video;

import java.util.ArrayList;

public class WatchVideoTest {
    VideoController videoController;
    PersonalController personalController;
    String username = "matiasbrown@hotmail.com";
    String pass = "Matias103";
    Customer customer;
    ArrayList<Video> videos;
    ArrayList<String> videoHistory;
    Video video;
    int viewsCount;
    @Before
    public void setup() throws Exception {
        videoController = VideoController.getController();
        personalController = PersonalController.getController();
        customer = personalController.getCusInfoByCusId(personalController.getIdByEmail(username));
        videos = videoController.getVideosByName("yoga");
        videoHistory = customer.getVideosHistory();
        video = videos.get(0);
        viewsCount = video.getViewsCount();
    }

    @Test
    public void SearchVideo() {
        System.out.println("Begin WatchVideo()");
        assert personalController.check(username, pass) == 1;
        System.out.println("Customer Info:");
        System.out.println(customer.toString());
        System.out.println("Keyword: yoga");
        System.out.println("Videos Info:");
        for(Video v: videos) {
            System.out.println(v.toString());
        }
        System.out.println("Sort videos by view");
        ArrayList<Video> v = videoController.sort(videos, "View");
        for(Video vd: v) {
            System.out.println(vd.toString());
        }

        if(videoHistory.contains(video.getVideoId())) {
            System.out.println(customer.getName() + " have watched the video " + video.getVideoId());
        }
        else {
            System.out.println(customer.getName() + " have not watched the video " + video.getVideoId());
        }
    }
    @Test
    public void WatchVideo() {
        System.out.println("Begin WatchVideo()");
        if(!videoHistory.contains(video.getVideoId())) {
            personalController.watchVideo(customer.getCusId(),video.getVideoId());
            videoHistory = customer.getVideosHistory();
            video = videoController.getVideoByVideoId(video.getVideoId());

            assert (videoHistory.contains(video.getVideoId()));
            System.out.println(customer.getName() + " watched the video " + video.getVideoId() + " successfully");
            assert (viewsCount + 1 == video.getViewsCount());
            System.out.println(video.getVideoId() + " has been updated successfully");
            System.out.println(video.toString());
        }

    }
}
