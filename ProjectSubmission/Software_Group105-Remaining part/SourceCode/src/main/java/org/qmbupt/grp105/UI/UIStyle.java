package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.VideoController;
import org.w3c.dom.ls.LSParserFilter;

import java.awt.*;
import java.net.URL;

/**
 * <p>It is used to store all the UI fonts, size, image resources, colors and settings
 * so that any page uses color should directly reference this static variable and the page
 *  style will be consistent.</p>
 * @author daliangrun
 * @version 5.3
 */
public class UIStyle
{
    public static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 18);
    public static final Font SMALL_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font TINY_FONT = new Font("Arial", Font.PLAIN, 10);
    public static final Font BIG_FONT = new Font("Arial", Font.PLAIN, 36);


    public static final Font SMALL_ARIAL_BOLD = new Font("Arial", Font.BOLD, 14);
    public static final Font TINY_ARIAL_BOLD = new Font("Arial", Font.BOLD, 10);

    public static final Font NORMAL_ARIAL_BOLD = new Font("Arial", Font.BOLD, 18);

    public static final Color COLOR_1 = Color.decode("#292F41");
    public static final Color COLOR_2 = Color.WHITE;
    public static final Color COLOR_3 = Color.decode("#F6F6F6");
    public static final Color COLOR_4 = Color.BLACK;
    public static final Color COLOR_5 = Color.decode("#4D5054");

    public static final Color BLUE_BUTTRESS = Color.decode("#1253CE");
    public static final Color BLUE_SHALLOW = Color.decode("#ACC7F1");
    public static final Color GRAY_BUTTRESS = Color.decode("#58606A");
    public static final Color GRAY_SHALLOW = Color.decode("#F8FAFB");

    public static final Color GREEN_OK = Color.decode("#28943D");

    public static double width;
    public static double height;
    public static int barHeight;
    public static int ScreenWidth;
    public static int ScreenHeight;

    public static final URL HomePanel_BackGround = UIStyle.class.getClassLoader().getResource("Fitness-for-10-Home-Licensing-Information.jpg");
    public static final URL SignInPanel_Login = UIStyle.class.getClassLoader().getResource("LoginPic.jpg");
    public static final URL CustomerPanel_PersonIcon = UIStyle.class.getClassLoader().getResource("PersonalIcon.jpg");
    public static final URL CustomerMember_Money = UIStyle.class.getClassLoader().getResource("Money.png");
    public static final URL CustomerMember_Level = UIStyle.class.getClassLoader().getResource("level-icon.png");
    public static final URL CustomerMember_Time = UIStyle.class.getClassLoader().getResource("remainTime.png");
    public static final URL Administrator_PersonIcon = UIStyle.class.getClassLoader().getResource("adminstrator.png");
    public static final URL VirtualClass_bicycle = UIStyle.class.getClassLoader().getResource("bicycle.jpg");
    public static final URL VirtualClass_HITT = UIStyle.class.getClassLoader().getResource("HIIT.jpg");
    public static final URL VirtualClass_flexibility =  UIStyle.class.getClassLoader().getResource("FLEXIBILITY.jpg");
    public static final URL VirtualClass_yoga = UIStyle.class.getClassLoader().getResource("yoga.jpg");
    public static final URL VirtualClass_strength = UIStyle.class.getClassLoader().getResource("Strength.jpg");
    public static final URL VirtualClass_loseWeight =  UIStyle.class.getClassLoader().getResource("loseWeight.jpg");
    public static final URL VirtualClass_thumbUp = UIStyle.class.getClassLoader().getResource("thumbs-up-512.png");
    public static final URL VirtualClass_play = UIStyle.class.getClassLoader().getResource("media_play.png");
    public static final URL VirtualClass_rate = UIStyle.class.getClassLoader().getResource("rating.png");
    public static final URL VirtualClass_category = UIStyle.class.getClassLoader().getResource("category.png");
    public static final URL Contact_background = UIStyle.class.getClassLoader().getResource("Contact.jpeg");
    public static final URL VirtualClass_pause = UIStyle.class.getClassLoader().getResource("pause.png");
    public static final URL VirtualClass_favorite = UIStyle.class.getClassLoader().getResource("favorite.png");
    public static final URL HomePanel_register = UIStyle.class.getClassLoader().getResource("register.png");
    public static final URL HomePanel_hot = UIStyle.class.getClassLoader().getResource("hot.png");
    public static String[] categories;
    public static String[] memberships;

    public UIStyle()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ScreenWidth =(int)dim.getWidth();
        ScreenHeight = (int)dim.getHeight();
        width = 1120;
        height = 700;
    }

    /**
     * <p>update video categories and membership settings from config file</p>
     */
    public static void updateSetting()
    {
        categories = VideoController.getController().getCategories();
        memberships = VideoController.getController().getMemberships();

    }

}

