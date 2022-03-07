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
import  org.qmbupt.grp105.UI.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main
{
    public static JFrame frame;
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(
                main::initAll
        );

    }
    public static void initAll()
    {

        new UIStyle();
        UIStyle.updateSetting();
        JPanel mp = new MainPanel();
        JFrame jf = new JFrame();
        jf.setUndecorated(true);

        jf.add(mp);
        jf.setVisible(true);
        mp.setVisible(true);
        jf.setBounds((int)((UIStyle.ScreenWidth - UIStyle.width)/2), (int)(UIStyle.ScreenHeight - UIStyle.height)/2, (int) UIStyle.width, (int) UIStyle.height);







    }
    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = new MediaSceneGenerator().createScene();
        fxPanel.setScene(scene);
    }
}
