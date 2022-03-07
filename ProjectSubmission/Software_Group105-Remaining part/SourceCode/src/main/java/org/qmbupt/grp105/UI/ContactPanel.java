package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.MailController;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * <p>This class is used for the user to submit advice to the gym system, and the administrator can see
 * it in the administrator page</p>
 * @author daliangrun
 * @version 5.3
 */
public class ContactPanel extends JLayeredPane
{
    public static MyReminder reminder;
    public ContactPanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar mb = new MenuBar(cards, mainPanel, "Contacts");
        reminder = new MyReminder(mb);
        mb.setVisible(true);
        this.setLayout(null);
        this.add(mb);

        URL path = UIStyle.Contact_background;
        int barHeight = (int)(UIStyle.height) / 10;
        int picHeight = (int) UIStyle.height - barHeight;
        JLabel picture = new Picture(path, (int) UIStyle.width, picHeight);
        picture.setBounds(0, barHeight, (int) UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(picture);
        int inputWidth = (int)(UIStyle.width / 3);
        int inputHeight = 30;


        InputText email = new InputText(inputWidth, inputHeight, 20, true, (int)(UIStyle.width/2 - (inputWidth / 2)) - 10, 300, "Email", false);
        email.setOpaque(false);

        this.add(email);


        InputText subject = new InputText(inputWidth, inputHeight, 20, true, (int)(UIStyle.width/2 + (inputWidth / 2)) + 10, 300, "Subject", false);
        this.add(subject);

        InputArea advice = new InputArea(inputWidth * 2 + 20, inputHeight*5, true, (int)(UIStyle.width / 2), 450, "Input Here...", false);
        this.add(advice);



        TextButton submit = new TextButton((int)(UIStyle.width / 2), (int)(UIStyle.height -  inputHeight * 3),  UIStyle.BLUE_BUTTRESS, java.awt.Color.WHITE, "Submit", (int)(inputWidth / 2), (int)inputHeight, "normal", true);
        this.add(submit);
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String content = "Email: " + email.getText() +
                        "\nSubject: " + subject.getText()+
                        "\nAdvice: " + advice.getText();
                if("Customer".equals(LoginToken.getType())) {
                    MailController.getController().writeMail(LoginToken.getId(), "Admin", content);
                    reminder.OK("Submit Advice Successfully!");
                }
                else
                {
                    reminder.WRONG("Please Login First.");
                }

            }
        });
        setLayer(submit, 0);
        setLayer(advice, 0);
        setLayer(email, 0);
        setLayer(subject, 0);
        setLayer(picture, -1);



    }
}
