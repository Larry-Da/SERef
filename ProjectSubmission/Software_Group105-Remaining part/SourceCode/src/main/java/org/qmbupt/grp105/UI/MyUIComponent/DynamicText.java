package org.qmbupt.grp105.UI.MyUIComponent;

import java.awt.*;

import java.util.*;
import javax.swing.*;

public class DynamicText extends MyLabelComponent
{

    public DynamicText(int x, int y, String aligned, Color background, Color foreground, String text, int width, int height, Font font) {
        super(width, height, x, y, text, background, foreground, aligned);
        this.font = font;

    }

    public DynamicText(Color background, Color foreground, String text, int xcenter, int ycenter, int width, int height, Font font) {
        super(width, height, xcenter - width / 2, ycenter - height / 2, text, background, foreground, "mid");
        this.font = font;

    }
    public void changeText(String text)
    {
        this.text = text;
        repaint();
    }

    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>()
        {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        super.paintComponent(g2);

        Insets insets = getInsets();
        g2.setColor(colors[0]);
        //g2.fillRoundRect(insets.left, insets.top, getWidth() - insets.left - insets.right, getHeight() - insets.top - insets.bottom, 20, 20);

        int x2 = 0;
        int y2 = 0;
        FontMetrics metrics = g2.getFontMetrics(font);
        if(aligned.equals("mid")) {
            x2 = (getWidth() - metrics.stringWidth(text)) / 2;
            y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        }
        else if(aligned.equals("left")){
            x2 = 0;
            y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        }
        else if (aligned.equals("right"))
        {
            x2 = (getWidth() - metrics.stringWidth(text));
            y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        }
        g2.setPaint(colors[1]);
        g2.setFont(font);

        g2.drawString(text, x2, y2);
    }

}
