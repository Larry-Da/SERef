package org.qmbupt.grp105.UI.MyUIComponent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PicButton extends JLabel
{
    private ImageIcon img;
    private int width;
    private int height;
    public PicButton(URL path, int centerx, int centery, int width, int height)
    {
        img = new ImageIcon(path);
        this.width = width;
        this.height = height;

        int x = centerx - width / 2;
        int y = centery - height / 2;
        setBounds(x, y, width, height);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(img.getImage(), 0, 0, width, height,null);// must set width and height, otherwise it will not display it

    }
}
