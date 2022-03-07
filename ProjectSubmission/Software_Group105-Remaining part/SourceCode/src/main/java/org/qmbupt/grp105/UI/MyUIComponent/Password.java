package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.UI.UIStyle;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import javax.swing.*;

public class Password extends JPasswordField
{
    private Color borderColor_unselected = Color.decode("#DEE2E6");
    private Color borderColor_selected = UIStyle.BLUE_BUTTRESS;
    private Color textColor_unselected = UIStyle.GRAY_BUTTRESS;
    private Color textColor_selected = Color.BLACK;
    private Color backgroundColor_unselected = UIStyle.GRAY_SHALLOW;
    private Color backgroundColor_selected = Color.WHITE;
    private Color borderColor;

    public Password(int xcenter, int ycenter, int width, int height)
    {
        super(10);

        setForeground(this.textColor_unselected); //Text color
        setBorderColor(this.borderColor_unselected);
        setBackground(this.backgroundColor_unselected);// background color

        int x = xcenter - width / 2;
        int y = ycenter - height / 2;
        setBounds(x, y, width, height);

        setFont(UIStyle.NORMAL_FONT);

        setText("Password");
        setEchoChar((char)0);
        this.addFocusListener(new FocusListener()
        {

            @Override
            public void focusGained(FocusEvent e)
            {
                if (Password.this.getText().equals("Password"))
                {
                    Password.this.setText("");
                    Password.this.setEchoChar('*');
                }
                Password.this.setForeground(textColor_selected);
                Password.this.setBorderColor(borderColor_selected);
                Password.this.setBackground(backgroundColor_selected);
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (Password.this.getText().isEmpty())
                {
                    Password.this.setEchoChar((char)0);
                    Password.this.setText("Password");
                }
                else
                {
                    Password.this.setEchoChar('*');
                }
                Password.this.setForeground(textColor_unselected);
                Password.this.setBorderColor(borderColor_unselected);
                Password.this.setBackground(backgroundColor_unselected);
            }
        });


    }
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 0, 0);

        super.paintComponent(g2);
    }

    protected void paintBorder(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(borderColor);

        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 0, 0);
    }
    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
}