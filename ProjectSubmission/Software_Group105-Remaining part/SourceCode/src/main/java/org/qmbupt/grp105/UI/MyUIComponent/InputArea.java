package org.qmbupt.grp105.UI.MyUIComponent;

import org.qmbupt.grp105.UI.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;

public class InputArea extends JTextArea
{
    private Color borderColor_unselected = Color.decode("#DEE2E6");
    private Color borderColor_selected = UIStyle.BLUE_BUTTRESS;
    private Color textColor_unselected = UIStyle.GRAY_BUTTRESS;
    private Color textColor_selected = Color.BLACK;
    private Color backgroundColor_unselected = UIStyle.GRAY_SHALLOW;
    private Color backgroundColor_selected = Color.WHITE;
    private Color borderColor;
    private Font font = UIStyle.NORMAL_FONT;

    private boolean arc = true;

    public InputArea(int width, int height, boolean autoVanish, int centerX, int centerY, String defaultText, boolean arc)
    {


        this.arc = arc;
        int x = centerX - width / 2;
        int y = centerY - height / 2;

        setParameters(x, y, width, height, defaultText);
        setEvent(autoVanish, defaultText);
    }

    void setParameters(int x, int y, int width, int height, String text)
    {
        setOpaque(false);
        setForeground(this.textColor_unselected); //Text color
        setBorderColor(this.borderColor_unselected);
        setBackground(this.backgroundColor_unselected);// background color

        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setFont(font);

        setText(text);
        this.setBounds(x, y, width, height);


    }
    void setEvent(boolean autoVanish, String defaultText)
    {
        this.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if(autoVanish) {
                    if (InputArea.this.getText().equals(defaultText)) {
                        InputArea.this.setText("");
                    }
                }
                InputArea.this.setForeground(textColor_selected);
                InputArea.this.setBorderColor(borderColor_selected);
                InputArea.this.setBackground(backgroundColor_selected);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(autoVanish) {
                    if (InputArea.this.getText().isEmpty()) {
                        InputArea.this.setText(defaultText);
                    }
                }

                InputArea.this.setForeground(textColor_unselected);
                InputArea.this.setBorderColor(borderColor_unselected);
                InputArea.this.setBackground(backgroundColor_unselected);
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
        if(arc)
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        else
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 0, 0);

        super.paintComponent(g2);
    }

    protected void paintBorder(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(borderColor);

        if(arc)
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        else
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 0, 0);

    }
    public void setBorderColor(Color color) {
        borderColor = color;
        repaint();
    }
    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        if(b)
        {
            borderColor_unselected = Color.decode("#DEE2E6");
            borderColor_selected = UIStyle.BLUE_BUTTRESS;
            textColor_unselected = UIStyle.GRAY_BUTTRESS;
            textColor_selected = Color.BLACK;
            backgroundColor_unselected = UIStyle.GRAY_SHALLOW;
            backgroundColor_selected = Color.WHITE;
            setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        }
        else
        {
            borderColor_unselected = Color.decode("#DEE2E6");
            borderColor_selected = borderColor_unselected;
            textColor_unselected = UIStyle.GRAY_BUTTRESS;
            textColor_selected = textColor_unselected;
            backgroundColor_unselected = UIStyle.GRAY_SHALLOW;
            backgroundColor_selected = backgroundColor_unselected;
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

}
