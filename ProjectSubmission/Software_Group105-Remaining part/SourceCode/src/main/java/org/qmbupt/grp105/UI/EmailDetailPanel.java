package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.MailController;
import org.qmbupt.grp105.Controller.Toolbox;
import org.qmbupt.grp105.Entity.Mail;
import org.qmbupt.grp105.UI.MyUIComponent.DynamicText;
import org.qmbupt.grp105.UI.MyUIComponent.InputArea;
import org.qmbupt.grp105.UI.MyUIComponent.InputText;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p>This panel is used to present the email content by others.
 * Also, it can be used to send email to others.</p>
 * @version 5.3
 * @author daliangrun
 */
public class EmailDetailPanel extends JPanel
{
    private Mail email;
    InputText from_lower;
    InputText to_lower;
    InputArea content;
    DynamicText from_upper;

    public EmailDetailPanel()
    {
        this.setBackground(Color.WHITE);
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.1 * UIStyle.height);
        int buttonStartX = (int)(UIStyle.width / 2 - 400);

        setBounds(0, 0,(int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));

        this.setLayout(null);

        DynamicText to_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "To", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        to_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(to_upper);
        this.add(to_lower);

        from_upper = new DynamicText((int)(UIStyle.width / 2 + buttonStartX + 0.02*buttonWidth - 30), buttonStartY, "left", Color.WHITE, Color.BLACK, "From", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        from_lower = new InputText((int)(UIStyle.width / 2 + buttonStartX - 30), buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(from_upper);
        this.add(from_lower);

        DynamicText content_upper = new DynamicText((int)(buttonStartX), buttonStartY + buttonHeight * 3, "left", Color.WHITE, Color.BLACK, "Mail Content", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);


        content = new InputArea(800, 300, true, (int)(UIStyle.width / 2), 400, "", false);
        this.add(content);
        this.add(content_upper);


        TextButton send = new TextButton(UIStyle.GREEN_OK, Color.white, "Send", (int)(UIStyle.width / 2 + buttonStartX - 30 + buttonWidth / 2), buttonStartY + buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(send);
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!Toolbox.getInstance().isCoachID(to_lower.getText()) && !Toolbox.getInstance().isCustomerID(to_lower.getText()))
                    TempContentPanel.reminder.WRONG("There is no such user!");
                else {
                    MailController.getController().writeMail(LoginToken.getId(), to_lower.getText(), content.getText());
                    TempContentPanel.reminder.OK("Send Success!");
                }
            }
        });

    }

    /**
     * <p>To change the email content of this page, we do not need to load this page again.
     * Instead, just use setEmail method so that the email content can be changed.</p>
     * @param email
     * @param writeable
     */
    public void setEmail(Mail email, boolean writeable)
    {
        this.email = email;
        from_lower.setText(email.getFrom());
        to_lower.setText(email.getTo());
        content.setText(email.getContent());

        if(writeable)
        {
            from_lower.setEditable(true);
            to_lower.setEditable(true);
            content.setEditable(true);
            from_lower.setVisible(false);
            from_upper.setVisible(false);

        }
        else
        {
            from_lower.setEditable(false);
            to_lower.setEditable(false);
            content.setEditable(false);
            from_lower.setVisible(true);
            from_upper.setVisible(true);
        }
    }

}
