package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.Coach;
import org.qmbupt.grp105.Entity.Customer;
import java.util.ArrayList;

/**
 * @Version 5.3
 * @author Liangrun Da
 */
public interface AdminUtils {
    public ArrayList<Customer> filterByKeyword(ArrayList<Customer> customers, String key);
    public ArrayList<Customer> filterByGender(ArrayList<Customer> customers, char gender);
    public ArrayList<Customer> filterByCusLevel(ArrayList<Customer> customers,ArrayList<String> levelKeys);
    public ArrayList<Customer> getAllCustomer();
    public ArrayList<Customer> getCusByGender(char gender);
    public int getCusNumByLevel(String level);
    public ArrayList<Coach> getAllCoaches();
}
