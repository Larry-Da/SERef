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
public class VideoControllerTest {

    VideoController videoController;

    @Before
    public void setUp() throws Exception {
        videoController = VideoController.getController();
    }



    @Test
    public void modifyVideo() {
        Video video = new Video("V014","usr/local","456sl",8.7,"Yoga",700,1000,"easy");
        videoController.modifyVideo(video);
    }


    @Test
    public void getAllVideos() {
        System.out.println("getAllVideos");
        ArrayList<Video> videos = videoController.getAllVideos();
        for(Video v: videos) {
            System.out.println(v.toString());
        }
    }

    @Test
    public void getVideoByVideoId() {
        System.out.println("getVideoByVideoId: v005");
        System.out.println(videoController.getVideoByVideoId("v005"));
    }

    @Test
    public void sort() {
        System.out.println("sort videos by views");
        ArrayList<Video> res = videoController.sort(videoController.getAllVideos(), "View");
        for(Video v: res) {
            System.out.println(v.toString());
        }
    }

    @Test
    public void getVideoByName() {
        System.out.println("getVideoByName: yoga training tutorial 2");
        System.out.println(videoController.getVideoByName("yoga training tutorial 2").toString());
    }

    @Test
    public void getVideosByName() {
        System.out.println("getVideosByName: yoga");
        ArrayList<Video> videos = videoController.getVideosByName("yoga");
        for(Video v: videos) {
            System.out.println(v.toString());
        }
    }

    @Test
    public void filterByCategory() {
        ArrayList<Video> videos = videoController.getAllVideos();
        List<String> c = new ArrayList<>();
        c.add("Bicycle Training");
        System.out.println("filterByCateogry: Bicycle Training");
        ArrayList<Video> res = videoController.filterByCategory(videos, c);
        for(Video v: res) {
            System.out.println(v.toString());
        }
    }

    @Test
    public void addVideo() {
        System.out.println("addVideo");
        Video video = new Video("v098","usr/local","Sdwerrtyry",8.7,"Yoga",500,1000,"easy");
        videoController.AddVideo(video);
        assert videoController.getVideoByName("Sdwerrtyry") != null;
    }

    @Test
    public void deleteVideo() {
        System.out.println("deleteVideo");
        String videoId = videoController.getVideoByName("Sdwerrtyry").getVideoId();
        videoController.deleteVideo(videoId);
        assert videoController.getVideoByName("Sdwerrtyry") == null;
    }


    @Test
    public void getVideosByCusId() {
        System.out.println("getVideosByCusId: cs3");
        ArrayList<Video> res = videoController.getVideosByCusId("cs3");
        for(Video v: res) {
            System.out.println(v.toString());
        }

    }

}
