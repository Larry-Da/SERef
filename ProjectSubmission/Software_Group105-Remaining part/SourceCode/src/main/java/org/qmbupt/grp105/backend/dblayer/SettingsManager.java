package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;

import org.qmbupt.grp105.backend.model.Setting;

/**
 * manage settings
 * @author Lingsong Feng
 * @version 5.3
 */
public class SettingsManager {
    /**
     * get fields of a setting
     * @param setting_name
     * @return
     * @throws IOException
     */
    public static ArrayList<String> getSetting(String setting_name) throws IOException {
        ArrayList<Setting> settings =  DataManager.getInstance().settings;
        for (Setting setting : settings) {
            if (setting.setting_name.equals(setting_name)) {
                return setting.setting_value;
            }
        }
        return new ArrayList<>();
    }
}
