package org.qmbupt.grp105.UI.MyUIComponent;

import javax.swing.*;
import java.awt.*;

public class MyLabelComponent extends JLabel
{
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected String text;
    protected Color[] colors = {Color.BLACK, Color.WHITE};
    protected Font font;
    protected String aligned = "mid";

    public MyLabelComponent(int width, int height, int x, int y, String text, Color backColor, Color foreColor, String aligned) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.text = text;
        this.colors[0] = backColor;
        this.colors[1] = foreColor;
        this.aligned = aligned;
        setParameters();

    }

    protected void setParameters()
    {
        setBounds(x, y, width, height);

    }


}
