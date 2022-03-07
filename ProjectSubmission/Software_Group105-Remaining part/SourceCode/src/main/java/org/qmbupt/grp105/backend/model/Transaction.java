package org.qmbupt.grp105.backend.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * class for transaction
 * @author Lingsong Feng
 * @version 5.3
 */
public class Transaction {
    @JSONField(name = "transactionId")
    public String transactionId;

    @JSONField(name = "cusId")
    public String cusId;

    @JSONField(name = "time", format = "yyyy-MM-dd hh:mm:ss")
    public Date time;

    @JSONField(name = "mount")
    public int mount;
}
