package org.qmbupt.grp105.backend.model;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * class for setting
 * @author Lingsong Feng
 * @version 5.3
 */
public class Setting {
    @JSONField(name = "setting_name")
    public String setting_name;

    @JSONField(name = "setting_value")
    public ArrayList<String> setting_value;

}
