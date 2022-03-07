package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.HashMap;

public class VideoPanel extends JPanel implements MouseListener
{
    private int space;
    private String title;
    private int likes;
    private int likesX;
    private int likesY;

    private int picHeight;
    private int lineHeight;

    private int viewCount;
    private int viewCountX;
    private int viewCountY;

    private double rate;
    private int rateX;
    private int rateY;

    private String category;
    private int categoryX;
    private int categoryY;

    private MainPanel mainPanel;
    private CardLayout cards;
    private String size;
    private String videoId;


    public VideoPanel(Video video, int x, int y, MainPanel mainPanel, CardLayout cards, String size)
    {
        this.setLayout(null);
        this.size = size;
        this.mainPanel = mainPanel;
        this.videoId = video.getVideoId();

        int width;
        int height;
        URL picPath = UIStyle.class.getClassLoader().getResource(video.getUrl());
        //picPath = picPath.replaceAll("%20", " ");
        //String picPath = UIStyle.class.getClassLoader().getResource(video.getURl())

        if(size.equals("small"))
        {
            width = 200;
            height = 300;
            space = width / 10;
            picHeight = height / 3 * 2;
            lineHeight  = height / 3 / 3;
            JLabel picturePreview = new Picture(picPath, width, picHeight);
            this.add(picturePreview);
            picturePreview.setBounds(0, 0, width, picHeight);
            this.addMouseListener(this);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            likesX = 46;
            likesY = 247;

            viewCountX = 146;
            viewCountY = 247;

            rateX = 46;
            rateY = 280;

            categoryX = 146;
            categoryY = 280;
        }
        else
        {
            space = 20;
            width = (int)UIStyle.width - (int)(UIStyle.width * 0.24);
            height = 120;
            picHeight = height - 10;
            JLabel picturePreview = new Picture(picPath, picHeight, picHeight);
            this.add(picturePreview);
            picturePreview.setBounds(space, space, picHeight, picHeight);
            picturePreview.addMouseListener(this);
            picturePreview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            likesX = 400;
            likesY = 110;

            viewCountX = 500;
            viewCountY = 110;

            rateX = 600;
            rateY = 110;

            categoryX = 700;
            categoryY = 110;

        }




        title = video.getName();
        likes = video.getLikes();
        viewCount = video.getViewsCount();
        rate = video.getRating();
        category = video.getCategory();


        setBackground(Color.WHITE);
        this.cards = cards;
        this.mainPanel = mainPanel;

        this.setVisible(true);
        this.setBounds(x, y, width, height);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>()
        {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        super.paintComponent(g2);


        if(size.equals("small")) {
            g2.setPaint(Color.BLACK);
            g2.setFont(UIStyle.TINY_FONT);
            g2.drawString(title, space, picHeight + space);
        }
        else
        {
            g2.setPaint(Color.BLACK);
            g2.setFont(UIStyle.NORMAL_FONT);
            g2.drawString(title, 150, 40);
        }

        g2.setPaint(Color.gray);
        g2.setFont(UIStyle.TINY_FONT);

        g2.drawString(likes + "", likesX, likesY);
        g2.drawString(viewCount + "", viewCountX, viewCountY);
        g2.drawString(rate + "", rateX, rateY);
        g2.drawString(category, categoryX, categoryY);


        Image img1 = new ImageIcon(UIStyle.VirtualClass_thumbUp).getImage();
        g2.drawImage(img1, likesX - 26, likesY - 17, 20, 20,null);// must set width andd height, otherwise it will not display it

        Image img2 = new ImageIcon(UIStyle.VirtualClass_play).getImage();
        g2.drawImage(img2, viewCountX - 26, viewCountY - 17, 20, 20,null);// must set width andd height, otherwise it will not display it

        Image img3 = new ImageIcon(UIStyle.VirtualClass_rate).getImage();
        g2.drawImage(img3,rateX - 26, rateY - 17, 20, 20,null);// must set width andd height, otherwise it will not display it

        Image img4 = new ImageIcon(UIStyle.VirtualClass_category).getImage();
        g2.drawImage(img4, categoryX - 26, categoryY - 17, 20, 20, null);

        if(!size.equals("small"))
        {
            g2.setPaint(UIStyle.COLOR_5);
            g2.drawLine(150, 120, (int)UIStyle.width - (int)(UIStyle.width * 0.24), 120);
        }
    }
    public void mousePressed(MouseEvent e) {
        //
        if(size.equals("manage")) {
            cards.show(mainPanel, "tempContentPanel");
            mainPanel.setTempContent("videoModify", videoId);
        }
        else
        {
            cards.show(mainPanel, "tempContentPanel");
            mainPanel.setTempContent("video", videoId);
            if(LoginToken.getId() != null && LoginToken.getType().equals("Customer"))
                PersonalController.getController().watchVideo(LoginToken.getId(), videoId);
        }
        //MainPanel.tempContentPanel.setContent(new VideoDetailPanel());
        //
    }

    public void mouseClicked(MouseEvent e)
    {

    }
    public void mouseReleased(MouseEvent e)
    {
        //
    }
    public void mouseEntered(MouseEvent e)
    {

    }


    public void mouseExited(MouseEvent e)
    {

    }
}
