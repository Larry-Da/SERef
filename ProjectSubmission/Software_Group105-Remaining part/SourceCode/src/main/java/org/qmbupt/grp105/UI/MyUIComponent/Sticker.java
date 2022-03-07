package org.qmbupt.grp105.UI.MyUIComponent;
import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;

public class Sticker extends JLabel
{
    int width;
    int height;
    String title;
    String contents;
    URL imagePath;
    Color fontColor = null;
    public Sticker(int width, int height, String title, String contents, int x, int y, URL imagePath, Color fontColor)
    {
        this(width,  height,  title,  contents,  x,  y,  imagePath);
        this.fontColor = fontColor;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    public Sticker(int width, int height, String title, String contents, int x, int y, URL imagePath)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.contents = contents;
        this.imagePath = imagePath;
        this.setBounds(x, y, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>()
        {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        super.paintComponent(g2);

        g2.setPaint(Color.decode("#6C7089"));
        g2.setFont(UIStyle.TINY_FONT);
        g2.drawString(title, (int)(0.067 * width), (int)(0.1 * width));

        g2.setPaint(Color.decode("#F6F6F8"));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        g2.setPaint(fontColor);
        if(fontColor == null) {
            g2.setPaint(Color.BLACK);
        }
        else
        {
            g2.setPaint(fontColor);
        }
        g2.setFont(UIStyle.NORMAL_FONT);
        if (contents != null)
            g2.drawString(contents, (int) (0.067 * width), (int) (0.6 * height));

        Image img = new ImageIcon(imagePath).getImage();
        g.drawImage(img, width / 2, (int) (0.1 * width), (int)(getHeight() / 1.5), (int)(getHeight() / 1.5),null);// must set width andd height, otherwise it will not display it
    }
    protected void paintBorder(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setPaint(Color.decode("#F6F6F8"));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
}
