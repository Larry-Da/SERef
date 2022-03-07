package org.qmbupt.grp105.backend.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * class for mail
 * @author Lingsong Feng
 * @version 5.3
 */
public class Mail {
    @JSONField(name = "from")
    public String from;

    @JSONField(name = "to")
    public String to;

    @JSONField(name = "time", format = "yyyy-MM-dd hh:mm:ss")
    public Date time;

    @JSONField(name = "content")
    public String content;
    public org.qmbupt.grp105.Entity.Mail converter()
    {
        return new org.qmbupt.grp105.Entity.Mail(from, to, time, content);
    }

}
