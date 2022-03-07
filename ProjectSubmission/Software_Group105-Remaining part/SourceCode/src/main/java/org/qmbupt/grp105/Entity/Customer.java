package org.qmbupt.grp105.Entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person
{
    private String cusId;
    private int age;
    private char gender;
    private Date dateOfBirth;
    private String membershipLevel;//不确定是String类型的
    private String expiredTime;//会员剩余时间
    private int balance;//余额
    private int points; //积分
    private ArrayList<String> videosHistory;
    private ArrayList<String> favouriteVideos;
    private ArrayList<String> bookedSessions;
    //private String exerciseLevel;


    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<String> getVideosHistory() {
        return videosHistory;
    }

    public void setVideosHistory(ArrayList<String> videosHistory) {
        this.videosHistory = videosHistory;
    }

    public ArrayList<String> getFavouriteVideos() {
        return favouriteVideos;
    }

    public void setFavouriteVideos(ArrayList<String> favouriteVideos) {
        this.favouriteVideos = favouriteVideos;
    }

    public ArrayList<String> getBookedSessions() {
        return bookedSessions;
    }

    public void setBookedSessions(ArrayList<String> bookedSessions) {
        this.bookedSessions = bookedSessions;
    }

    public Customer(String cusId, int age, String name, String password, String phoneNo, String email, char gender,
                    Date dateOfBirth, String membershipLevel, String expiredTime, int balance, int points) {
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.cusId = cusId;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.membershipLevel = membershipLevel;
        this.expiredTime = expiredTime;
        this.balance = balance;
        this.points = points;
    }



    public static Customer getSample()
    {
        return new Customer("1", 21, "Vae", "12345", "18055661111", "2018213144@bupt.edu.cn", 'M', new Date(100000000), "Lv1", "12h", 123, 12);
    }

    public Customer(String cusId, int age, String name, String password, String phoneNo, String email, char gender,
                    Date dateOfBirth, String membershipLevel, String expiredTime, int balance, int points,
                    ArrayList<String> videosHistory, ArrayList<String> bookedSessions, ArrayList<String> favouriteVideos) {

        this.bookedSessions = bookedSessions;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.cusId = cusId;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.membershipLevel = membershipLevel;
        this.expiredTime = expiredTime;
        this.balance = balance;
        this.points = points;
        this.videosHistory = videosHistory;
        this.favouriteVideos = favouriteVideos;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", expiredTime=" + expiredTime +
                ", balance=" + balance +
                ", points=" + points +
                '}';
    }
    public org.qmbupt.grp105.backend.model.Customer convert()
    {
        org.qmbupt.grp105.backend.model.Customer customer = new org.qmbupt.grp105.backend.model.Customer();
        customer.balance = this.balance;
        customer.cusId = this.cusId;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date DoBF = null;
        try {
            DoBF = new Date(sdf.parse(this.expiredTime).getTime());
        }
        catch (Exception e1)
        {
            ;
        }
        customer.expireDate = DoBF;
        customer.videosHistory = (ArrayList<String>)this.videosHistory.clone();
        customer.bookedSessions = (ArrayList<String>)this.bookedSessions.clone();
        customer.dateOfBirth = this.dateOfBirth;
        customer.email = this.email;
        customer.gender = this.gender + "";
        customer.level = Integer.parseInt(this.membershipLevel);
        customer.name = this.name;
        customer.password = this.password;
        customer.phoneNo = this.phoneNo;
        customer.xp = 0;
        customer.favoriteVideos = this.favouriteVideos;
        return customer;
    }

}
