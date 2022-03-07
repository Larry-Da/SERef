package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.Entity.Mail;
import org.qmbupt.grp105.UI.MainPanel;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

public class EmailEntry extends JPanel implements MouseListener
{
    int width;
    int height;
    private CardLayout cards;
    private MainPanel mainPanel;
    private Mail email;
    public EmailEntry(Mail email, int x, int y, CardLayout cards, MainPanel mainPanel)
    {
        width = (int) UIStyle.width - (int)(UIStyle.width * 0.24);
        height = 150;
        this.email = email;
        this.mainPanel = mainPanel;
        this.cards = cards;
        this.setLayout(null);
        int picHeight = 100;
        int space = 20;

        String a = email.getFrom()+".png";
        URL picPath = null;
        try {
            picPath = UIStyle.class.getClassLoader().getResource(a);
        }
        catch (Exception e)
        {
            picPath = UIStyle.class.getClassLoader().getResource("default_user.png");
        }


        JLabel picturePreview = new Picture(picPath, picHeight, picHeight);
        this.add(picturePreview);
        picturePreview.setBounds(space, space, picHeight, picHeight);

        String contents = email.getContent();
        JLabel contentPreview = new JLabel(contents);
        contentPreview.setFont(UIStyle.NORMAL_FONT);
        contentPreview.setBounds( 3* space + picHeight, 13, (int)(width / 1.5), 30);
        this.add(contentPreview);

        int buttonHeight = 40;
        int buttonWidth = 100;
        TextButton button = new TextButton((int)(width - buttonWidth / 1.5) - 100, (int)(height-1.5*buttonHeight), Color.decode("#192D33"), Color.WHITE, "Detail", buttonWidth, buttonHeight, "small", true);

        this.add(button);
        button.addMouseListener(this);

        this.setVisible(true);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.white);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        cards.show(mainPanel, "tempContentPanel");
        mainPanel.setTempContent("emailRead", email);
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
}
