package org.qmbupt.grp105.Controller;
import org.qmbupt.grp105.Entity.Coach;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.UI.UIStyle;

import java.util.ArrayList;
import java.util.regex.*;

import static org.qmbupt.grp105.Controller.PersonalController.getController;

/**
 * @Version 5.3
 * @Author Liangrun Da
 */
public class Toolbox implements ToolRequired
{
    public static final String dateForm1Format = "yyyy-mm-dd";
    public static final String dateForm2Format = "yyyy-mm-dd hh:mm:ss";
    public static final String emailFormat = "xxx@yy.zz.ww";
    public static final String passwordFormat = "more than 6 digits and contains at least one number and one letter";
    public static final String genderFormat = "M or F";
    public static Toolbox toolbox = new Toolbox();
    private Toolbox(){}
    public static Toolbox getInstance()
    {
        return toolbox;
    }

    /**
     * <p>
     *
     * </p>
     * @param date
     * @return
     */
    public boolean isDateForm1(String date)
    {
        return Pattern.matches("^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$", date);
    }
    public boolean isDateForm2(String date)
    {
        String yyyymmdd = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))";
        String hhmmss = "[ ]([01][1-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        return Pattern.matches(yyyymmdd + hhmmss, date);
    }

    /**
     * <p>
     *     Check the format of the email
     * </p>
     * @param email email
     * @return True for valid input, False for invalid input
     */
    public boolean isEmail(String email)
    {
        String emailPattern = "^[\\w]+@([\\w]+.)+[\\w]+$";
        return Pattern.matches(emailPattern, email);
    }

    /**
     * <p>
     *     Check whether the password is valid or not (Must more than 6 digits and contain at least one number and one letter)
     * </p>
     * @param password password
     * @return True for valid input, False for invalid input
     */
    public boolean isPassword(String password)
    {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        return Pattern.matches(passwordPattern, password);
    }

    /**
     * <p>
     *     Check the input gender format
     * </p>
     * @param gender gender
     * @return True for valid input, False for invalid input
     */
    public boolean isGender(String gender)
    {
        return Pattern.matches("^[MF]$", gender);
    }

    /**
     * <p>
     *     Check the pirture format(suffix: .png, .jpeg, .jpg, .bmp)
     * </p>
     * @param picture Picture name
     * @return True for valid format, False for invalid format
     */
    public boolean isPicture(String picture)
    {
        String picPattern = "^.*(.png|.jpeg|.jpg|.bmp)$";
        return Pattern.matches(picPattern, picture);
    }

    /**
     * <p>
     *     Check whether the category belongs to the system category list
     * </p>
     * @param category Category name
     * @return True for valid input, False for invalid input
     */
    public boolean isCategory(String category)
    {
        String[] a = UIStyle.categories;
        for(String i: a)
        {
            if(i.equals(category))
                return true;
        }
        return false;
    }

    /**
     * <p>
     *     Check whethter the membership belongs to the system membership list
     * </p>
     * @param membership Membership name
     * @return True for valid input, False for invalid input
     */
    public boolean isMembership(String membership)
    {
        String[] a = UIStyle.memberships;
        for(String i : a)
        {
            if(i.equals(membership))
                return true;
        }
        return false;
    }

    /**
     * <p>
     *     Check whether the coachID belongs to the system coach list
     * </p>
     * @param coachID
     * @return True for valid input, False for invalid input
     */
    public boolean isCoachID(String coachID)
    {
        ArrayList<Coach> coaches = PersonalController.getController().getAllCoaches();
        for(Coach c: coaches)
        {
            if(c.getCoachId().equals(coachID))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isCustomerID(String cusID)
    {
        ArrayList<Customer> customers = PersonalController.getController().getAllCustomer();
        for(Customer c: customers)
        {
            if(c.getCusId().equals(cusID))
                return true;
        }
        return false;
    }



}
