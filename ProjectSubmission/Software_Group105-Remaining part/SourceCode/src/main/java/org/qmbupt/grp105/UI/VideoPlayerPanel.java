package org.qmbupt.grp105.UI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;


import org.apache.tomcat.util.scan.JarFactory;
import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.InputArea;
import org.qmbupt.grp105.UI.MyUIComponent.InputText;
import org.qmbupt.grp105.UI.MyUIComponent.PicButton;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * It is used to play the video.
 * @author daliangrun
 * @version 5.3
 */
public class VideoPlayerPanel extends JPanel
{

    private Video currentVideo;
    private ArrayList<InputArea> commentList = new ArrayList<>();
    private JPanel commentPanel;
    private CardLayout cards = new CardLayout();
    private JPanel rightPanels = new JPanel();
    private TextButton delete;

    private static JFXPanel VFXPanel = null;
    static Scene scene;
    static StackPane root;
//    public static void initAndShowGUI() {
//        VideoPlayerPanel.VFXPanel = new JFXPanel();
//        root = new StackPane();
//        scene = new Scene(root);
//        File video_source = new File("/Users/zhaowenrui/Desktop/Memories.mp4");
//        Media m = new Media(video_source.toURI().toString());
//        MediaPlayer player = new MediaPlayer(m);
//        MediaView viewer = new MediaView(player);
//
//        javafx.geometry.Rectangle2D screen = Screen.getPrimary().getVisualBounds();
//        viewer.setX((screen.getWidth() ) / 2);
//        viewer.setY((screen.getHeight()) / 2);
//
//
//        // add video to stackpane
//        root.getChildren().add(viewer);
//        VFXPanel.setScene(scene);
//    }
//    public static void play() {
//        Platform.runLater(() -> initFX(VFXPanel));
//    }

    public VideoPlayerPanel()
    {
        this.setLayout(null);
        this.setBackground(Color.white);
        JPanel videoPanel = new JPanel();
        int videoPanelHeight = (int)((UIStyle.height-UIStyle.barHeight) / 10 * 9);
        int videoPanelWidth = (int)(videoPanelHeight / 9.0 * 16 /10.0 * 9);
        videoPanel.setBounds(10, 10,videoPanelWidth, videoPanelHeight);
        videoPanel.setLayout(null);
        //VideoPlayerPanel.initAndShowGUI();
        //videoPanel.add(VFXPanel);


        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.red);
        panel.setSize(1000, 1000);

        final JFXPanel VFXPanel = new JFXPanel();

        videoPanel.add(panel);
        panel.setVisible(true);
        panel.add(VFXPanel, BorderLayout.CENTER);
        VFXPanel.setVisible(true);

        Platform.runLater(() -> initFX(VFXPanel));



        int buttonWidth = 30;
        int buttonHeight = 30;



        JLabel like = new JLabel("Like", JLabel.CENTER);
        like.setFont(UIStyle.SMALL_FONT);
        int labelX = videoPanelWidth - 2 * buttonWidth - buttonWidth / 2 + 200;
        int labelY = (int)(UIStyle.height - UIStyle.barHeight - 40);
        like.setBounds(labelX, labelY, 40, 20);
        this.add(like);

        videoPanel.setVisible(true);
        PicButton thumbUp = new PicButton(UIStyle.VirtualClass_thumbUp, videoPanelWidth - 3*buttonWidth + 200, (int)(UIStyle.height - UIStyle.barHeight - 35), buttonWidth, buttonHeight);
        this.add(thumbUp);

        thumbUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                TempContentPanel.reminder.OK("Like it!");
            }
        });

        JLabel addToFavorite = new JLabel("Favorite", JLabel.LEFT);
        addToFavorite.setFont(UIStyle.SMALL_FONT);
        addToFavorite.setBounds(labelX - 5 * buttonWidth, labelY, 100, 20);
        this.add(addToFavorite);


        PicButton favorite = new PicButton(UIStyle.VirtualClass_favorite, videoPanelWidth - 8 *buttonWidth + 200, (int)(UIStyle.height - UIStyle.barHeight - 35) , buttonWidth, buttonHeight);
        this.add(favorite);
        favorite.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(LoginToken.getId() != null && LoginToken.getType().equals("Customer")) {
                    PersonalController.getController().addToFavourite(LoginToken.getId(), currentVideo.getVideoId());
                    TempContentPanel.reminder.OK("Add to favorite successfully!");
                }
                else
                {
                    TempContentPanel.reminder.WRONG("Please Login First!");
                }
            }
        });

        this.setVisible(true);
        this.add(videoPanel);

        InputText comment = new InputText(20, videoPanelHeight + 20, 800, 30, 100, true, "Comment Here");


        this.add(comment);
        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comment.getText().length() > 40)
                {
                    TempContentPanel.reminder.WRONG("Comment length should be less than 40 letters");
                }
                else if(!comment.getText().equals(""))
                {
                    ArrayList<String> commentList = currentVideo.getComments();
                    commentList.add(0, comment.getText());
                    VideoController.getController().modifyVideo(currentVideo);
                    TempContentPanel.reminder.OK("Comment successfully");
                    comment.setText("");
                    updateComment();

                }
                else
                {
                    TempContentPanel.reminder.WRONG("Comment can't be empty!");
                }
            }
        });
        rightPanels.setLayout(cards);
        rightPanels.setBounds((int)(UIStyle.width - 200), 0, 200, (int)(UIStyle.height - UIStyle.barHeight  - 50));
        rightPanels.setVisible(true);
        rightPanels.setBackground(Color.white);


    }
    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = new MediaSceneGenerator().createScene();
        fxPanel.setScene(scene);
    }
    /**
     * inject a video here
     * @param currentVideo video injected
     */
    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

    /**
     * <p>since the customer can comment, and it should be presented immediately, this method
     * is used to update the comment.</p>
     */
    public void updateComment()
    {
        if(commentPanel != null)
            rightPanels.remove(commentPanel);
        commentPanel = new JPanel();
        commentPanel.setVisible(true);
        commentPanel.setLayout(null);
        commentPanel.setBackground(Color.white);
        commentPanel.setBounds(0, 0, 200, (int)(UIStyle.height - UIStyle.barHeight - 50));

        ArrayList<String> comments = currentVideo.getComments();
        for(int i = 0; i < 10 && i < comments.size(); i++)
        {

            InputArea temp = new InputArea(180, 50, false,100, 50 + i * 50, comments.get(i), false);
            temp.setEditable(false);
            temp.setLineWrap(true);

            commentPanel.add(temp);
        }
        rightPanels.add(commentPanel);
        if(delete != null)
        {
            commentPanel.remove(delete);
        }
        if("Admin".equals(LoginToken.getType()))
        {
            delete = new TextButton(Color.decode("#E04147"), Color.WHITE, "Delete Comment", 0, 540, 200, 40, UIStyle.NORMAL_FONT, true, "mid");
            commentPanel.add(delete);
            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentVideo.setComments(new ArrayList<String>());
                    VideoController.getController().modifyVideo(currentVideo);
                    TempContentPanel.reminder.OK("Delete successfully");
                    updateComment();
                }
            });
        }

        this.add(rightPanels);
        cards.first(rightPanels);

    }

}
