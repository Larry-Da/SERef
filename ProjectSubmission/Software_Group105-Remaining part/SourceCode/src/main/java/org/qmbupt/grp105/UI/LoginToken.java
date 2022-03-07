package org.qmbupt.grp105.UI;

/**
 * <p>It is used to record the current user the system is logging.
 * type == "Customer" means customer is logging
 * type == "Coach" means coach is logging
 * type == "Admin" means administrator is logging</p>
 * @author daliangrun
 * @version 5.3
 */
public class LoginToken
{
    private static String type;
    private static String id;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        LoginToken.type = type;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        LoginToken.id = id;
    }
}
