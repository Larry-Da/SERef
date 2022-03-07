package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.UI.MyUIComponent.FilterBox;
import org.qmbupt.grp105.UI.MyUIComponent.LivePanel;
import org.qmbupt.grp105.UI.MyUIComponent.TextButton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * <p>This panel is used to check all the lives booked by the user, and it can be accessed by the Customer Panel
 * so that any changes that made can be directly flushed</p>
 * @author daliangrun
 * @version 5.3
 */
public class CustomerBookedLivePanel extends JPanel
{
    private int pageMax;
    private ArrayList<JPanel> resultPanels = new ArrayList<>();
    private FilterBox expired;
    private FilterBox categoryFilter;
    private JPanel contentPanel;
    private CardLayout innerCards = new CardLayout();
    public CustomerBookedLivePanel()
    {
        this.setLayout(null);
        String expiredContent[] = {"Expired", "Yes", "No"};
        expired = new FilterBox(50, expiredContent, "light", true);
        this.add(expired);
        categoryFilter = new FilterBox(10, UIStyle.categories, "light");
        this.add(categoryFilter);
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);


        contentPanel = new JPanel();
        contentPanel.setVisible(true);
        this.add(contentPanel);

        contentPanel.setLayout(innerCards);
        contentPanel.setBounds(0,130, panelWidth, panelHeight - 130);
        TextButton applyChange = new TextButton(panelWidth / 2, 110, UIStyle.BLUE_BUTTRESS, Color.white, "Apply Change", 150, 25, "tiny", true);
        this.add(applyChange);
        applyChange.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateRes();
            }
        });
        updateRes();
    }

    /**
     * <p>It is used to flush the page, so that any changes made in files can be directly presented in the page</p>
     */
    public void updateRes()
    {
        ArrayList<LiveSession> liveSessions = LiveController.getController().getLiveSessionByCusId(LoginToken.getId());
        ArrayList<String> keyCategory = new ArrayList<>();
        boolean[] states = {};
        if(categoryFilter != null)
            states = categoryFilter.getStates();
        int cnt = 1;
        for(boolean i : states)
        {
            if(i)
            {
                keyCategory.add(UIStyle.categories[cnt]);
            }
            cnt++;
        }
        liveSessions = LiveController.getController().filterSessionByCategory(liveSessions,keyCategory);


        states = expired.getStates();
        if(states[0] == true)
        {
            liveSessions = LiveController.getController().filterSessionByExpire(liveSessions, true);
        }
        else if(states[1] == true)
        {
            liveSessions = LiveController.getController().filterSessionByExpire(liveSessions, false);

        }

        pageMax = liveSessions.size() / 4;
        int panelWidth = (int) (UIStyle.width * 0.76);
        int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);
        setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight);


        setBackground(Color.white);

        for(JPanel i : resultPanels)
        {
            contentPanel.remove(i);
        }
        resultPanels.clear();
        for(int i = 0; i <= pageMax; i++)
        {
            int finalI = i;
            ArrayList<LiveSession> liveSessions1 = liveSessions;
            resultPanels.add(new JPanel(){
                {
                    int page = finalI + 1;

                    int panelWidth = (int) (UIStyle.width * 0.76);
                    int panelHeight = (int) (UIStyle.height - UIStyle.barHeight);

                    setBounds((int) (UIStyle.width * 0.24), UIStyle.barHeight, panelWidth, panelHeight- 130);
                    setBackground(Color.WHITE);
                    this.setLayout(null);

                    for(int i = (page - 1) * 3; i < liveSessions1.size() && i < page * 3; i++) {
                        LivePanel test = new LivePanel(liveSessions1.get(i), 0, 150 * (i % 3) , "large", CustomerBookedLivePanel.this);
                        this.add(test);
                    }

                    JSlider pages = new JSlider(1, pageMax + 1, page);

                    int pagesWidth = 100;
                    int pagesHeight = 50;
                    pages.setBounds((int)(panelWidth / 2 - pagesWidth / 2), (int)(getHeight() - pagesHeight+7), pagesWidth,pagesHeight);

                    this.add(pages);
                    pages.setMajorTickSpacing(3);

                    pages.setMinorTickSpacing(1);
                    pages.setForeground(Color.white);
                    pages.setBackground(Color.white);
                    pages.setSnapToTicks(true);
                    JTextField pageShow = new JTextField();
                    pageShow.setBounds((int)(panelWidth / 2 + pagesWidth / 1.5), (int)(getHeight() - pagesHeight + 17), 30, 30);
                    this.add(pageShow);
                    pageShow.setText(page + "");
                    pages.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                            pageShow.setText(pages.getValue() + "");
                        }
                    });
                    pageShow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            innerCards.show(contentPanel, pageShow.getText() + "");
                            pageShow.setText(page + "");
                            pages.setValue(page);
                        }
                    });
                }
            });
            contentPanel.add(resultPanels.get(i), i + 1 + "");
        }
        innerCards.first(contentPanel);
    }
}
