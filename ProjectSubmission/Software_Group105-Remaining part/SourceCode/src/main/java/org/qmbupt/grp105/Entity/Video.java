package org.qmbupt.grp105.Entity;

import java.util.ArrayList;

public class Video
{
    private String videoId;
    private String url;
    private String name; // for keyword search
    //  private String videoDescription;
    private Double rating; // 评分 int score
    private String category;
    private int likes;      // 点赞数
    private int viewsCount; // 访问量 -> hottest
    private String level;
    private ArrayList<String> comments = new ArrayList<>();

//    public ArrayList<LiveSession> getLiveSessions() {
//        backend.getLiveSession(cusId);
//        backend.getLiveSessionInfo()
//    }
//    public ArrayList<Videos> getVideoHistory();

    public Video(String videoId, String url, String name, Double rating, String category, int likes, int viewsCount, String level) {
        this.videoId = videoId;
        this.url = url;
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.likes = likes;
        this.viewsCount = viewsCount;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public static String[] getAllAttributes() {
        return new String[]{"videoId","url","name","rating","category","likes","viewCounts","level"};
    }
    public static Video getSampleVideo()
    {
        return new Video("1", "yoga.jpg", "TestName", 9.1, "HITT", 12, 300, "lv1");
    }
    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", likes=" + likes +
                ", viewCounts=" + viewsCount +
                '}';
    }
    public org.qmbupt.grp105.backend.model.Video convert()
    {
        org.qmbupt.grp105.backend.model.Video video = new org.qmbupt.grp105.backend.model.Video();
        video.videoId = this.videoId;
        video.viewsCount = this.viewsCount;
        video.category = this.category;
        video.level = this.level;
        video.likes = this.likes;
        video.name = this.name;
        video.rating = this.rating;
        video.url=  this.url;
        video.comments = (ArrayList<String>)this.comments.clone();

        return video;
    }
}
