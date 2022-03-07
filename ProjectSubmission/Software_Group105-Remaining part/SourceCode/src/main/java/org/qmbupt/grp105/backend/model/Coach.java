package org.qmbupt.grp105.backend.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * class for coach
 * @author Lingsong Feng
 * @version 5.3
 */
public class Coach {
    @JSONField(name = "coachId")
    public String coachId;

    @JSONField(name = "age")
    public int age;

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "password")
    public String password;

    @JSONField(name = "phoneNo")
    public String phoneNo;

    @JSONField(name = "email")
    public String email;

    @JSONField(name = "gender")
    public String gender;

    public org.qmbupt.grp105.Entity.Coach converter() {
        return new org.qmbupt.grp105.Entity.Coach(this.coachId, this.age, this.name, this.password, this.phoneNo, this.email, this.gender.charAt(0));
    }
}
