package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.Entity.Mail;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.MyReminder;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p>It is used to show some temporary page, for example, live session, video,
 * register and email details.</p>
 * @author daliangrun
 * @version 5.3
 */
public class TempContentPanel extends JPanel
{
    private JPanel contentContainer;
    private CardLayout innerCards = new CardLayout();
    private String currentContent;
    private VideoPlayerPanel videoPanel;
    private VideoDetailPanel videoDetailPanel;
    private EmailDetailPanel emailPanel;
    private RegisterPanel registerPanel;
    private LiveDetailPanel liveDetailPanel;
    public static MyReminder reminder;
    public TempContentPanel(CardLayout cards, MainPanel mainPanel)
    {
        this.setLayout(null);
        this.setBounds(0, 0, (int)UIStyle.width, (int)UIStyle.height);
        JPanel backBar = new JPanel();
        backBar.setBounds(0, 0, (int) UIStyle.width, UIStyle.barHeight);
        backBar.setBackground(UIStyle.COLOR_1);
        reminder = new MyReminder(backBar);
        this.add(backBar);
        backBar.setLayout(null);

        TextButton back = new TextButton(100, UIStyle.barHeight / 2, UIStyle.COLOR_1.brighter().brighter(), Color.white, "Back", 100, (int)(UIStyle.barHeight / 2), "normal", true);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(mainPanel, MainPanel.currentPanel);
                mainPanel.updatePersonalInfo();

                    if (MediaSceneGenerator.getVideoView().getMediaPlayer().getStatus().toString().equals("PLAYING")) {
                        MediaSceneGenerator.getVideoView().getMediaPlayer().pause();

                    }


            }
        });
        backBar.add(back);
        this.setVisible(true);
        contentContainer = new JPanel();
        contentContainer.setBounds(0, UIStyle.barHeight, (int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight ));
        this.add(contentContainer);
        contentContainer.setLayout(innerCards);
        videoPanel = new VideoPlayerPanel();

        contentContainer.add(videoPanel, "video");
        currentContent = "video";
        videoDetailPanel = new VideoDetailPanel();
        contentContainer.add(videoDetailPanel, "videoModify");
        emailPanel = new EmailDetailPanel();
        contentContainer.add(emailPanel, "email");
        registerPanel = new RegisterPanel();
        contentContainer.add(registerPanel, "register");
        liveDetailPanel = new LiveDetailPanel(mainPanel);
        contentContainer.add(liveDetailPanel, "liveModify");



    }

    /**
     * It is used to inject a register page
     * @param type indicates register
     */
    public void setContent(String type)
    {
        if(type.equals("register"))
        {
            currentContent = "register";
            innerCards.show(contentContainer, "register");
        }
    }

    /**
     * Inject a email
     * @param type indicates email
     * @param email email that we want to show.
     */
    public void setContent(String type, Mail email)
    {
        if(type.equals("emailRead"))
        {
            if(currentContent.equals("emailRead"))
            {
                emailPanel.setEmail(email, false);
            }
            else
            {
                currentContent = "emailRead";
                emailPanel.setEmail(email, false);
                innerCards.show(contentContainer, "email");
            }
        }
        else if(type.equals("emailWrite"))
        {
            if(currentContent.equals("emailWrite"))
            {
                emailPanel.setEmail(email, true);
            }
            else
            {
                currentContent = "emailWrite";
                emailPanel.setEmail(email, true);
                innerCards.show(contentContainer, "email");
            }
        }
    }

    /**
     * Inject a video or a live session to be shown
     * @param type indicates video or live session
     * @param id video or live id
     */
    public void setContent(String type, String id)
    {
        updateUI();
        if(type.equals("video"))
        {
            if(currentContent.equals("video"))
            {
                videoPanel.setCurrentVideo(VideoController.getController().getVideoByVideoId(id));
                videoPanel.updateComment();
            }
            else {
                currentContent = "video";
                videoPanel.setCurrentVideo(VideoController.getController().getVideoByVideoId(id));
                videoPanel.updateComment();
                innerCards.show(contentContainer, "video");
            }
        }
        else if(type.equals("videoModify"))
        {
            if(currentContent.equals("videoModify"))
            {
                Video video = VideoController.getController().getVideoByVideoId(id);
                videoDetailPanel.setAdding(false);
                videoDetailPanel.setCurrentVideo(video);
                innerCards.show(contentContainer, "videoModify");
            }
            else
            {
                currentContent = "videoModify";
                Video video = VideoController.getController().getVideoByVideoId(id);
                videoDetailPanel.setAdding(false);
                videoDetailPanel.setCurrentVideo(video);

                innerCards.show(contentContainer, "videoModify");
            }
        }
        else if(type.equals("videoAdd"))
        {
            if(currentContent.equals("videoAdd"))
            {
                videoDetailPanel.setAdding(true);
                innerCards.show(contentContainer, "videoModify");
            }
            else
            {
                currentContent = "videoAdd";
                videoDetailPanel.setAdding(true);
                innerCards.show(contentContainer, "videoModify");
            }
        }
        else if(type.equals("liveAdd"))
        {
            liveDetailPanel.setEditable(true);
            if(currentContent.equals("liveAdd"))
            {
                liveDetailPanel.setAdding(true);
                innerCards.show(contentContainer, "liveModify");
            }
            else
            {
                currentContent = "liveAdd";
                liveDetailPanel.setAdding(true);
                innerCards.show(contentContainer, "liveModify");
            }
        }
        else if(type.equals("liveModify"))
        {
            if(!currentContent.equals("liveModify"))
            {
                currentContent = "liveModify";
            }
            liveDetailPanel.setEditable(true);
            LiveSession liveSession = LiveController.getController().getLiveSessionBySessionId(id);
            liveDetailPanel.setAdding(false);
            liveDetailPanel.setCurrentLive(liveSession);
            innerCards.show(contentContainer, "liveModify");
        }
        else if(type.equals("live"))
        {
            if(!currentContent.equals("live"))
            {
                currentContent = "live";
            }

            LiveSession liveSession = LiveController.getController().getLiveSessionBySessionId(id);
            liveDetailPanel.setEditable(false);
            liveDetailPanel.setAdding(false);
            liveDetailPanel.setCurrentLive(liveSession);
            innerCards.show(contentContainer, "liveModify");
        }

    }

}
