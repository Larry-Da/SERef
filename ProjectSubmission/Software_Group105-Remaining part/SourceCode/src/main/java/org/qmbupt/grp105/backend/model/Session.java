package org.qmbupt.grp105.backend.model;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * class for session
 * @author Lingsong Feng
 * @version 5.3
 */
public class Session {

    @JSONField(name = "sessionId")
    public String sessionId;

    @JSONField(name = "rating")
    public Double rating;

    @JSONField(name = "category")
    public String category;

    @JSONField(name = "startTime", format = "yyyy-MM-dd hh:mm")
    public Date startTime;

    @JSONField(name = "endTime", format = "yyyy-MM-dd hh:mm")
    public Date endTime;

    @JSONField(name = "likes")
    public int likes;

    @JSONField(name = "viewsCount")
    public int viewsCount;

    @JSONField(name = "coachId")
    public String coachId;

    @JSONField(name = "availableNum")
    public int availableNum;

    @JSONField(name = "price")
    public int price;

    public org.qmbupt.grp105.Entity.LiveSession converter() {
        return new org.qmbupt.grp105.Entity.LiveSession(this.sessionId, this.rating, this.category, this.startTime,
                this.endTime, this.likes, this.viewsCount, null, this.coachId, this.availableNum, this.price);
    }

}
