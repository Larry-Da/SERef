package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.UI.UIStyle;

import java.awt.*;
import java.net.URL;

public class CategoryButton extends Picture
{
    private int x;
    private int y;
    private String text;
    public CategoryButton(URL path, int x, int y, String text)
    {
        super(path, x, y);
        this.x = x;
        this.y = y;
        this.text = text;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int textWidth = x / 3;
        int textHeight = y / 10;
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.WHITE);
        g2.setFont(UIStyle.NORMAL_FONT);
        g2.drawString(text, textWidth, y - textHeight);

    }
}
