package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.Toolbox;
import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>This class is the first page of GUI. Once the user open this application,
 * he will see the homepage. Also, homepage contains two buttons, which is register &
 * top 5 hottest videos</p>
 * @version 5.3
 * @author daliangrun
 */
public class HomePanel extends JLayeredPane
{
    public static MyReminder reminder;
    public HomePanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar mb = new MenuBar(cards, mainPanel, "Home");
        reminder = new MyReminder(mb);
        mb.setVisible(true);
        this.setLayout(null);

        this.add(mb);

        URL path = UIStyle.HomePanel_BackGround;
        int barHeight = (int)(UIStyle.height) / 10;
        int picHeight = (int) UIStyle.height - barHeight;
        JLabel picture = new Picture(path, (int) UIStyle.width, picHeight);
        picture.setBounds(0, barHeight, (int) UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(picture);

        int registerWidth = 300;
        int registerHeight = 150;
        Sticker register = new Sticker(registerWidth, registerHeight, "", "Register Now", (int)(UIStyle.width - registerWidth - 50), (int)(UIStyle.height - UIStyle.barHeight - registerHeight), UIStyle.HomePanel_register, Color.white);
        this.add(register);
        Sticker hot = new Sticker(registerWidth, registerHeight, "", "Hottest 4 Videos", (int)(UIStyle.width - registerWidth - 450), (int)(UIStyle.height - UIStyle.barHeight - registerHeight), UIStyle.HomePanel_hot, Color.white);
        this.add(hot);
        hot.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, "virtualClassPanel");
                MainPanel.currentPanel = "virtualClassPanel";
                mainPanel.updateHotVideo();

            }
        });
        setLayer(register, 0);
        setLayer(picture, -1);
        register.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, "tempContentPanel");
                mainPanel.setTempContent("register");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}

/**
 * <p>This class is used to register a customer account. Many data fields will be presented
 * in this page and the customer can enter those data fields & save the data.</p>
 * @version 5.3
 * @author daliangrun
 */
class RegisterPanel extends JPanel
{
    private InputText gender_lower;
    private InputText name_lower;
    private InputText dateOfBirth_lower;
    private InputText email_lower;
    private InputText phoneNo_lower;
    private InputText password_lower;
    private InputText age_lower;
    private TextButton save;


    public RegisterPanel() {
        this.setBackground(Color.WHITE);
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.1 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        setBounds(0, 0,(int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));

        this.setLayout(null);


        DynamicText gender_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Gender", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        gender_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, "");

        this.add(gender_upper);
        this.add(gender_lower);

        DynamicText dateOfBirth_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "DateOfBirth", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        dateOfBirth_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(dateOfBirth_upper);
        this.add(dateOfBirth_lower);

        DynamicText name_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Name", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        name_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(name_upper);
        this.add(name_lower);

        DynamicText age_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Age", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        age_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(age_upper);
        this.add(age_lower);

        DynamicText phoneNo_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 8 * buttonHeight, "left", Color.WHITE, Color.BLACK, "PhoneNo", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        phoneNo_lower = new InputText(buttonStartX, buttonStartY + 9 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(phoneNo_upper);
        this.add(phoneNo_lower);

        DynamicText email_upper = new DynamicText((int)(buttonStartX + UIStyle.width / 2 + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Email", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        email_lower = new InputText((int)(buttonStartX + UIStyle.width / 2), buttonStartY + 1 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(email_upper);
        this.add(email_lower);

        DynamicText password_upper = new DynamicText((int)(buttonStartX + UIStyle.width / 2 + 0.02*buttonWidth), 2*buttonHeight +  buttonStartY, "left", Color.WHITE, Color.BLACK, "Password", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        password_lower = new InputText((int)(buttonStartX + UIStyle.width / 2), buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(password_upper);
        this.add(password_lower);


        save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Register", (int)(buttonStartX +  2.5* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                boolean checkPass = true;
                // check gender
                if(!Toolbox.getInstance().isGender(gender_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Gender should be " + Toolbox.getInstance().genderFormat);
                    checkPass = false;
                }
                else if(!Toolbox.getInstance().isDateForm1(dateOfBirth_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Date of birth should be " + Toolbox.getInstance().dateForm1Format);
                    checkPass = false;
                }
                else if(!Toolbox.getInstance().isEmail(email_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Email should be " + Toolbox.getInstance().emailFormat);
                    checkPass = false;
                }
                else if(!Toolbox.getInstance().isPassword(password_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Password should be " + Toolbox.getInstance().passwordFormat);
                    checkPass = false;
                }
                else if(name_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("Name should not be empty");
                    checkPass = false;
                }
                else if(phoneNo_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("Phone number should not be empty");
                    checkPass = false;
                }
                if(checkPass) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date DoBF = null;
                    try {
                        DoBF = new Date(sdf.parse(dateOfBirth_lower.getText()).getTime());
                    } catch (Exception e1) {
                        ;
                    }
                    Customer cusToBeAdd = null;
                    try {
                        cusToBeAdd = new Customer("", Integer.parseInt(age_lower.getText()), name_lower.getText(), password_lower.getText(), phoneNo_lower.getText(), email_lower.getText(),
                                gender_lower.getText().equals("") ? gender_lower.getText().charAt(0) : 'M', DoBF, "1", "2025-01-01", 0, 0, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
                    }
                    catch(Exception e1)
                    {
                        TempContentPanel.reminder.WRONG("Age format error");
                    }

                    if(cusToBeAdd != null) {
                        PersonalController.getController().updateCustomer(cusToBeAdd);
                        TempContentPanel.reminder.OK("Register Success!");
                    }
                }
            }
        });
    }
}

