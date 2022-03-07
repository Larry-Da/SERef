package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Entity.Mail;

import javax.swing.*;
import java.awt.*;

/**
 * <p>This panel contains all the five panels:
 * home panel, virtual class panel, live session panel, contact panel
 *  and home panel</p>
 * @version 5.3
 * @author daliangrun
 */
public class MainPanel extends JPanel
{
    private HomePanel homePanel;
    private PersonalPanel personalPanel;
    private VirtualClassPanel virtualClassPanel;
    private LiveClassPanel liveClassPanel;
    private ContactPanel contactPanel;
    private TempContentPanel tempContentPanel;
    public static String currentPanel = "homePanel";


    public MainPanel()
    {
        CardLayout cards = new CardLayout();
        this.setLayout(cards);

        homePanel = new HomePanel(cards, this);
        this.add(homePanel, "homePanel");
        //homePanel.setVisible(true);

        personalPanel = new PersonalPanel(cards, this);
        this.add(personalPanel, "personalPanel");
        //personalPanel.setVisible(true);

        virtualClassPanel = new VirtualClassPanel(cards, this);
        this.add(virtualClassPanel, "virtualClassPanel");
        //classPanel.setVisible(true);

        liveClassPanel = new LiveClassPanel(cards, this);
        this.add(liveClassPanel, "liveSessionPanel");

        contactPanel = new ContactPanel(cards, this);
        this.add(contactPanel, "contactPanel");

        tempContentPanel = new TempContentPanel(cards, this);
        this.add(tempContentPanel, "tempContentPanel");

    }

    /**
     * <p>We need to show the email detail sometimes. Just invoke this method.</p>
     * @param content indicates email
     * @param email Email we want to show
     */
    public void setTempContent(String content, Mail email)
    {
        tempContentPanel.setContent(content, email);
    }

    /**
     * <p>It is need to show the video detail & live session detail by invoking this
     * method</p>
     * @param content "live" if showing live session, "video" if showing videos
     * @param id live or video id that needs to be shown
     */
    public void setTempContent(String content, String id)
    {
        tempContentPanel.setContent(content, id);
    }

    /**
     * <p>To show the register panel, we use this method</p>
     * @param content indicates register
     */
    public void setTempContent(String content){ tempContentPanel.setContent(content);}

    /**
     * <p>update personal information if any changes were made.</p>
     */
    public void updatePersonalInfo()
    {
        UIStyle.updateSetting();
        if(LoginToken.getId() != null && LoginToken.getType().equals("Customer"))
            personalPanel.updateCus(LoginToken.getId());
        if(LoginToken.getId() != null && LoginToken.getType().equals("Coach"))
            personalPanel.updateCoach(LoginToken.getId());
        if(LoginToken.getId() != null && LoginToken.getType().equals("Admin"))
            personalPanel.updateAdmin();
    }

    /**
     * <p>Update video information</p>
     */
    public void updateVideoInfo()
    {
        UIStyle.updateSetting();
    }

    /**
     * <p>update hottest 4 videos</p>
     */
    public void updateHotVideo()
    {
        virtualClassPanel.updateHotVideo();
    }

    /**
     * <p>update live information</p>
     */
    public void updateLiveInfo()
    {
        UIStyle.updateSetting();
    }


}
