package org.qmbupt.grp105.IntegrationTest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Video;

import java.util.Date;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdministratorTest {
    VideoController videoController;
    LiveController liveController;
    PersonalController personalController;
    @Before
    public void setup() throws Exception {
        videoController = VideoController.getController();
        liveController = LiveController.getController();
        personalController = PersonalController.getController();
    }

    @Test
    public void action1() {
        System.out.println("Begin to add a video");
        Video video = new Video("v205","usr/local","New Video",8.7,"Yoga",500,1000,"easy");
        videoController.AddVideo(video);
        Video v = videoController.getVideoByName("New Video");
        assert v != null;
        System.out.println("Video has been added" + ", video info:");
        System.out.println(v.toString());

        System.out.println("Begin to add a live session");
        LiveSession liveSession = new LiveSession("lvs99",7.8, "HITT", new Date(), new Date(), 100, 120, "cs17","co3", 30, 10);
        liveController.addSession(liveSession);
        int size = liveController.getAllLiveSessions().size();
        String id = "lvs" + size;
        System.out.println(id);
        LiveSession live = liveController.getLiveSessionBySessionId(id);
        assert live != null;
        System.out.println("Live session has been added" + ", live session info: ");
        System.out.println(live.toString());

    }
    @Test
    public void action2() {
        System.out.println("Begin to modify a video");
        String videoId = videoController.getVideoByName("New Video").getVideoId();
        Video video = videoController.getVideoByVideoId(videoId);
        video.setName("newName");
        videoController.modifyVideo(video);
        video = videoController.getVideoByVideoId(videoId);
        assert video.getName().equals("newName");
        System.out.println("Video has been modified, video info:");
        System.out.println(video.toString());

    }

    @Test
    public void action3() {
        System.out.println("Begin to search a video");
        String videoId = videoController.getVideoByName("newName").getVideoId();
        Video video = videoController.getVideoByVideoId(videoId);

        assert video != null;
        System.out.println("Search a video successfully" + ", video info:");
        System.out.println(video.toString());

        System.out.println("Begin to search a live session");
        int size = liveController.getAllLiveSessions().size();
        String id = "lvs" + size;

        LiveSession liveSession = liveController.getLiveSessionBySessionId(id);
        assert liveSession != null;
        System.out.println("Search a live session successfully" + ", live sesssion info:");
        System.out.println(liveSession.toString());
    }

    @Test
    public void action4() {
        System.out.println("Begin to delete a video");
        String videoId = videoController.getVideoByName("newName").getVideoId();
        videoController.deleteVideo(videoId);
        System.out.println("Video " + videoId + " has been deleted successfully");

    }


}
