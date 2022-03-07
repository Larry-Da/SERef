package org.qmbupt.grp105.Entity;
import java.util.Date;

public class Mail {
    private String from;

    private String to;

    private Date time;

    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Mail(String from, String to, Date time, String content) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.content = content;
    }
}