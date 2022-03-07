package org.qmbupt.grp105.backend.dblayer;

import com.alibaba.fastjson.JSON;
import org.qmbupt.grp105.backend.model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * manage for data
 * @author Lingsong Feng
 * @version 5.3
 */
public class DataManager {
    private static DataManager instance = null;

    /**
     * get the unique instance of DataManager
     * @return reference of DataManager object
     * @throws IOException
     */
    public static DataManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<Customer>    customers;
    public ArrayList<Video>       videos;
    public ArrayList<Transaction> transactions;
    public ArrayList<Session>     sessions;
    public ArrayList<Coach>       coaches;
    public ArrayList<Mail>        mails;
    public ArrayList<Setting>     settings;

    /**
     * DataManager constructor
     * @throws IOException
     */
    private DataManager() throws IOException {

        /**
         * Read information which are stored in JSON files.
         */
        customers    = (ArrayList<Customer>)    JSON.parseArray(IO.read("customer.json"),    Customer.class);
        videos       = (ArrayList<Video>)       JSON.parseArray(IO.read("video.json"),       Video.class);
        transactions = (ArrayList<Transaction>) JSON.parseArray(IO.read("transaction.json"), Transaction.class);
        sessions     = (ArrayList<Session>)     JSON.parseArray(IO.read("sessions.json"),    Session.class);
        coaches      = (ArrayList<Coach>)       JSON.parseArray(IO.read("coaches.json"),     Coach.class);
        mails        = (ArrayList<Mail>)        JSON.parseArray(IO.read("mails.json"),       Mail.class);
        settings     = (ArrayList<Setting>)     JSON.parseArray(IO.read("settings_config.json"), Setting.class);
    }

    /**
     * write all data from memory into disk (files)
     * @throws IOException
     */
    public void commit() throws IOException {
        IO.write("customer.json",    JSON.toJSONString(customers));
        IO.write("video.json",       JSON.toJSONString(videos));
        IO.write("transaction.json", JSON.toJSONString(transactions));
        IO.write("sessions.json",    JSON.toJSONString(sessions));
        IO.write("coaches.json",     JSON.toJSONString(coaches));
        IO.write("mails.json",       JSON.toJSONString(mails));
        IO.write("settings_config.json", JSON.toJSONString(settings));
    }

}
