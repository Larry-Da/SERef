package org.qmbupt.grp105.Controller;

/**
 * @Version 5.3
 * @author Liangrun Da
 */
public interface ToolRequired
{
    public boolean isDateForm1(String date);
    public boolean isDateForm2(String date);
    public boolean isEmail(String email);
    public boolean isPassword(String password);
    public boolean isGender(String gender);
    public boolean isPicture(String picture);
    public boolean isCategory(String category);
    public boolean isMembership(String membership);
    public boolean isCoachID(String coachID);

}
