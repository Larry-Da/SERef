package org.qmbupt.grp105.UI.MyUIComponent;
import org.qmbupt.grp105.UI.MainPanel;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MenuBar extends JPanel
{

    TextButton HomePageButton;
    TextButton PersonalButton;
    TextButton ClassButton;
    TextButton LiveButton;
    TextButton ContactButton;
    String panelName = null;

    public MenuBar(CardLayout cards, MainPanel mainPanel, String panelName)
    {
        this(cards, mainPanel);
        this.panelName = panelName;
    }
    public MenuBar(CardLayout cards, MainPanel mainPanel)
    {
        int buttonWidth = (int)(UIStyle.width / 2 / 6);
        int mid = (int)(UIStyle.width / 2);
        int barHeight = (int)(UIStyle.height) / 10;
        UIStyle.barHeight = barHeight;
        int buttonHeight = barHeight / 2;



        this.setBounds(0, 0, (int) UIStyle.width, barHeight);
        this.setBackground(UIStyle.COLOR_1);

        HomePageButton = new TextButton(buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "HOMEPAGE",  buttonWidth, buttonHeight, "small", false);
        PersonalButton = new TextButton(2 * buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "PERSONAL",  buttonWidth, buttonHeight, "small", false);
        ClassButton = new TextButton(3 * buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "CLASSES",  buttonWidth, buttonHeight, "small", false);
        LiveButton = new TextButton(4 * buttonWidth + mid, buttonHeight,UIStyle.COLOR_1, UIStyle.COLOR_2, "LIVE",  buttonWidth, buttonHeight, "small", false);
        ContactButton = new TextButton(5 * buttonWidth + mid, buttonHeight, UIStyle.COLOR_1, UIStyle.COLOR_2, "CONTACTS",  buttonWidth, buttonHeight, "small", false);

        PersonalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //cards.next(mainPanel);
                cards.show(mainPanel, "personalPanel");
                MainPanel.currentPanel = "personalPanel";
                mainPanel.updatePersonalInfo();
            }
        });
        HomePageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, "homePanel");
                MainPanel.currentPanel = "homePanel";
            }
        });
        ClassButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, "virtualClassPanel");
                MainPanel.currentPanel = "virtualClassPanel";
                mainPanel.updateVideoInfo();
            }
        });
        LiveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, "liveSessionPanel");
                MainPanel.currentPanel = "liveSessionPanel";
                mainPanel.updateLiveInfo();
            }
        });
        ContactButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, "contactPanel");
                MainPanel.currentPanel = "contactPanel";

            }
        });
        this.setLayout(null);
        this.add(HomePageButton);
        this.add(PersonalButton);
        this.add(ClassButton);
        this.add(LiveButton);
        this.add(ContactButton);




    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        if(panelName != null) {
            String[] panelsName = {"Home", "Personal", "Classes", "Live", "Contacts"};
            for(int i = 0; i < 5; i++)
            {
                if(panelsName[i].equals(panelName))
                {
                    g2.setPaint(Color.decode("#EC9730"));
                    int buttonHeight = UIStyle.barHeight / 2;
                    int buttonWidth = (int)(UIStyle.width / 2 / 6);
                    int mid = (int)(UIStyle.width / 2) - buttonWidth / 2;
                    g2.drawLine(mid + (i + 1)* buttonWidth, buttonHeight * 2 - 15, mid + (i + 2) * buttonWidth, buttonHeight * 2 - 15);
                }
                else
                {
                    g2.setPaint(UIStyle.GRAY_SHALLOW);
                    int buttonHeight = UIStyle.barHeight / 2;
                    int buttonWidth = (int)(UIStyle.width / 2 / 6);
                    int mid = (int)(UIStyle.width / 2) - buttonWidth / 2;
                    g2.drawLine(mid + (i + 1)* buttonWidth, buttonHeight * 2 - 15, mid + (i + 2) * buttonWidth, buttonHeight * 2 - 15);

                }
            }
        }
    }

}
