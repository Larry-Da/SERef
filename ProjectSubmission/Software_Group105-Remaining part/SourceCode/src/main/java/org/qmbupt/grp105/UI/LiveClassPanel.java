package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.LiveController;
import org.qmbupt.grp105.Controller.PersonalController;
import org.qmbupt.grp105.Controller.ToolRequired;
import org.qmbupt.grp105.Controller.Toolbox;
import org.qmbupt.grp105.Entity.LiveSession;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>This class is used to show all the live sessions. And customer can choose the live session to
 * see the details and decide if he will book it</p>
 * @author daliangrun
 * @version 5.3
 */
public class LiveClassPanel extends JPanel
{
    public static MyReminder reminder;
    ContentPanel contentPanel;
    public LiveClassPanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel, "Live");
        reminder = new MyReminder(menuBar);

        menuBar.setVisible(true);
        this.setLayout(null);
        this.add(menuBar);
        int barHeight = (int)(UIStyle.height) / 10;


        contentPanel = new ContentPanel(cards, mainPanel);
        contentPanel.setBounds(0, barHeight, (int)UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(contentPanel);
        contentPanel.setVisible(true);

    }
}

/**
 * <p>This is used to show the real items of the live sessions</p>
 * @version 5.3
 * @author daliangrun
 */
class ContentPanel extends JPanel
{
    private int pageMax;
    public static int SearchResultPanelHeight;

    private InputText searchBar;
    private String[] sortString = {"Sort", "Like", "Rating", "View"};

    private CardLayout resultCards;
    private ArrayList<ResultPanel> searchResultPanels = new ArrayList<>();
    private FilterBox categoryFilter;
    private JPanel resultContentPanel;
    private CardLayout mainCards;
    private MainPanel mainPanel;
    private FilterBox sortFilter;

    public ContentPanel(CardLayout cards, MainPanel contentPanel)
    {

        this.mainCards = cards;
        this.mainPanel = contentPanel;
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));


        searchBar = new InputText(500, 50, 40, true, (int)(UIStyle.width / 2), 55, "Search", true);
        this.add(searchBar);
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRes();
            }
        });

        int startFilter = 130;
        categoryFilter = new FilterBox(startFilter, UIStyle.categories, "dark");
        this.add(categoryFilter);

        sortFilter = new FilterBox(startFilter + 40, sortString, "dark", true);
        this.add(sortFilter);

        SearchResultPanelHeight = (int)(UIStyle.height - UIStyle.barHeight - startFilter - 90);

        resultCards = new CardLayout();
        resultContentPanel = new JPanel();
        resultContentPanel.setBounds(0, startFilter + 90, (int)(UIStyle.width),SearchResultPanelHeight);
        this.add(resultContentPanel);
        resultContentPanel.setLayout(resultCards);

        updateRes();
        resultContentPanel.setVisible(true);

    }

    /**
     * <p>It is used to flush the page, so that any changes made by users or administrator will be
     * directly presented in the page</p>
     */
    public void updateRes()
    {
        String key = searchBar.getText();
        if(key.equals("Search") || key.equals(""))
        {
            key = null;
        }
        ArrayList<LiveSession> sessions = LiveController.getController().getSessionsByCoach(key);
        ArrayList<String> keyCategory = new ArrayList<>();
        boolean[] states = categoryFilter.getStates();
        int cnt = 1;
        for(boolean i : states)
        {
            if(i)
            {
                keyCategory.add(UIStyle.categories[cnt]);
            }
            cnt++;
        }
        ArrayList<LiveSession> sessions1 = LiveController.getController().filterSessionByCategory(sessions, keyCategory);


        pageMax = sessions1.size() / 8;
        for(ResultPanel i : searchResultPanels)
        {
            resultContentPanel.remove(i);
        }
        searchResultPanels.clear();

        for(int i = 0; i <= pageMax; i++)
        {
            ResultPanel rp = new ResultPanel(sessions1, pageMax, resultCards, resultContentPanel, i + 1, mainPanel, mainCards);
            searchResultPanels.add(i, rp);
            resultContentPanel.add(rp, i + 1 + "");
        }
        resultCards.first(resultContentPanel);// if there is no this statement, updateRes from only 1 page to another only 1 page will show blank

    }
}

/**
 * <p>It is the search result panel of the live session. To be more specific, it contains
 * all the search results made by the user.</p>
 */
class ResultPanel extends JPanel {
    public ResultPanel(ArrayList<LiveSession> liveSessions, int pageMax, CardLayout resultCards, JPanel resultContentPanel, int page, MainPanel mainPanel, CardLayout cards) {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));

        int xinterval =(int)((UIStyle.width - 100 - 100 - 200) / 3);
        for(int i = 8 * (page - 1); i < liveSessions.size() && i < 8 * (page -1) + 4; i++)
        {
            LivePanel live = new LivePanel(liveSessions.get(i), 100+ xinterval * (i%4), 40, "small", mainPanel, cards);
            this.add(live);
        }
        for(int i = 8 *(page - 1) + 4; i < liveSessions.size() && i < 8* (page - 1) + 8; i++)
        {
            LivePanel live = new LivePanel(liveSessions.get(i), 100+ xinterval * ((i-4)%4), 40 + 150, "small",  mainPanel, cards);
            this.add(live);
        }
        JSlider pages = new JSlider(1, pageMax + 1, page);

        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int) (UIStyle.width / 2 - pagesWidth / 2), (int) (SearchPanel.SearchResultPanelHeight - 2 * pagesHeight), pagesWidth, pagesHeight);

        this.add(pages);
        // 设置主刻度间隔
        pages.setMajorTickSpacing(3);

        // 设置次刻度间隔
        pages.setMinorTickSpacing(1);
        pages.setForeground(Color.white);
        pages.setBackground(Color.white);
//        pages.setPaintTicks(true);
//        pages.setPaintLabels(true);
        pages.setSnapToTicks(true);
        JTextField pageShow = new JTextField();
        pageShow.setBounds((int) (UIStyle.width / 2 + pagesWidth / 1.5), (int) (SearchPanel.SearchResultPanelHeight - 2 * pagesHeight + 10), 30, 30);
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
                resultCards.show(resultContentPanel, pageShow.getText() + "");
                pageShow.setText(page + "");
                pages.setValue(page);
            }
        });
    }
}

/**
 * <p>It is used to show the detail of the live session. Once the user click one item,
 * the application will transfer to this page.</p>
 */
class LiveDetailPanel extends JPanel
{
    private LiveSession currentLive;
    private boolean isAdding = false;
    private InputText start_lower;
    private InputText end_lower;
    private InputText category_lower;
    private InputText like_lower;
    private InputText rating_lower;
    private InputText num_lower;
    private Picture pic;
    private TextButton save;
    private TextButton delete;
    private TextButton book;
    private InputText price_lower;
    private MouseListener addingVideo;
    private MouseListener modifyVideo;
    private DynamicText coachId_upper;
    private InputText coachId_lower;
    private boolean editable = true;

    public LiveDetailPanel(MainPanel mainPanel) {

        this.setBackground(Color.WHITE);
        int buttonHeight = (int)(0.06 * UIStyle.height);
        int buttonWidth = (int)(0.244 * UIStyle.width);
        int buttonStartY = (int)(0.1 * UIStyle.height);
        int buttonStartX = (int)(0.1 * UIStyle.width);

        setBounds(0, 0,(int)UIStyle.width, (int)(UIStyle.height - UIStyle.barHeight));

        this.setLayout(null);



        DynamicText start_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Start", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        start_lower = new InputText(buttonStartX, buttonStartY + buttonHeight, buttonWidth, buttonHeight , 15, false, "start");
        this.add(start_upper);
        this.add(start_lower);

        DynamicText end_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "End", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        end_lower = new InputText(buttonStartX, buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false, "end");
        this.add(end_upper);
        this.add(end_lower);

        DynamicText category_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Category", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        category_lower = new InputText(buttonStartX, buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(category_upper);
        this.add(category_lower);


        DynamicText like_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 6 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Likes", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        like_lower = new InputText(buttonStartX, buttonStartY + 7 * buttonHeight, buttonWidth, buttonHeight , 15, false, "");
        this.add(like_upper);
        this.add(like_lower);

        DynamicText rating_upper = new DynamicText((int)(buttonStartX + 0.02*buttonWidth), buttonStartY + 8 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Rating", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        rating_lower = new InputText(buttonStartX, buttonStartY + 9 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(rating_upper);
        this.add(rating_lower);

        DynamicText num_upper = new DynamicText((int)(buttonStartX + UIStyle.width / 2 + 0.02*buttonWidth), buttonStartY, "left", Color.WHITE, Color.BLACK, "Available Num", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        num_lower = new InputText((int)(buttonStartX + UIStyle.width / 2), buttonStartY + 1 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(num_upper);
        this.add(num_lower);

        DynamicText price_upper = new DynamicText((int)(buttonStartX + UIStyle.width / 2 + 0.02*buttonWidth), buttonStartY + 2 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Price", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        price_lower = new InputText((int)(buttonStartX + UIStyle.width / 2), buttonStartY + 3 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(price_upper);
        this.add(price_lower);

        coachId_upper = new DynamicText((int)(buttonStartX + UIStyle.width / 2 + 0.02*buttonWidth), buttonStartY + 4 * buttonHeight, "left", Color.WHITE, Color.BLACK, "Coach ID", buttonWidth, buttonHeight, UIStyle.NORMAL_ARIAL_BOLD);
        coachId_lower = new InputText((int)(buttonStartX + UIStyle.width / 2), buttonStartY + 5 * buttonHeight, buttonWidth, buttonHeight , 15, false,  "");
        this.add(coachId_upper);
        this.add(coachId_lower);



        save = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Save", (int)(buttonStartX +  2.5* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(save);

        delete = new TextButton(Color.decode("#E04147"), Color.WHITE, "Delete", (int)(buttonStartX +  1.6* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(delete);

        book = new TextButton(UIStyle.GREEN_OK, Color.WHITE, "Book", (int)(buttonStartX +  2.5* buttonWidth ), buttonStartY + 9 * buttonHeight, buttonWidth / 2, buttonHeight, UIStyle.NORMAL_FONT, true, "mid");
        this.add(book);
        book.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(LoginToken.getId() != null && LoginToken.getType().equals("Customer")) {
                    PersonalController.getController().bookLiveSession(LoginToken.getId(), currentLive.getLiveSessionId());
                    TempContentPanel.reminder.OK("Book Success!");
                    num_lower.setText((Integer.parseInt(num_lower.getText()) - 1) + "");
                }
            }
        });

//        delete.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                VideoController.getController().deleteVideo(cur.getVideoId());
//                TempContentPanel.reminder.OK("Delete Success!");
//            }
//        });



        modifyVideo = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean checkPass = true;

                if(Toolbox.getInstance().isDateForm2(start_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Start time should be " + Toolbox.getInstance().dateForm2Format);
                    checkPass = false;
                }
                else if(Toolbox.getInstance().isDateForm2(end_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("End time should be " + Toolbox.getInstance().dateForm2Format);
                    checkPass = false;
                }
                else if(Toolbox.getInstance().isCategory(category_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Category not exist");
                    checkPass = false;
                }
                else if(like_lower.getText().equals("") || rating_lower.getText().equals("")
                        || num_lower.getText().equals("") || price_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("Some field is empty");
                    checkPass = false;
                }

                TempContentPanel.reminder.OK("Save Success!");
            }
        };

        addingVideo = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean checkPass = true;
                if(!Toolbox.getInstance().isDateForm2(start_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Start time should be " + Toolbox.getInstance().dateForm2Format);
                    checkPass = false;
                }
                else if(!Toolbox.getInstance().isDateForm2(end_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("End time should be " + Toolbox.getInstance().dateForm2Format);
                    checkPass = false;
                }
                else if(!Toolbox.getInstance().isCategory(category_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Category not exist");
                    checkPass = false;
                }
                else if(!Toolbox.getInstance().isCoachID(coachId_lower.getText()))
                {
                    TempContentPanel.reminder.WRONG("Coach not exist");
                    checkPass = false;
                }
                else if(like_lower.getText().equals("") || rating_lower.getText().equals("")
                        || num_lower.getText().equals("") || price_lower.getText().equals(""))
                {
                    TempContentPanel.reminder.WRONG("Some field is empty");
                    checkPass = false;
                }

                if(checkPass) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

                    Date start = null;
                    Date end = null;
                    try {
                        start = new Date(sdf.parse(start_lower.getText()).getTime());
                        end = new Date(sdf.parse(end_lower.getText()).getTime());

                    } catch (Exception e1) {

                    }
                    try {
                        LiveSession liveToBeModify = new LiveSession("", Double.parseDouble(rating_lower.getText()), category_lower.getText(), start, end, Integer.parseInt(like_lower.getText()),
                                0, "", coachId_lower.getText(), Integer.parseInt(num_lower.getText()), Integer.parseInt(price_lower.getText()));
                        LiveController.getController().addSession(liveToBeModify);
                        TempContentPanel.reminder.OK("Save Success!");
                        mainPanel.updateLiveInfo();
                    }
                    catch(Exception e2)
                    {
                        TempContentPanel.reminder.WRONG("Number format error");
                    }

                }

            }
        };





    }

    /**
     * <p>It is used to set if the page can be used to add new live session</p>
     * @param isAdding true means this page is adding new session.
     */
    public void setAdding(boolean isAdding)
    {
        this.isAdding = isAdding;
        if(isAdding) {
            setCurrentLive(null);
        }
    }

    /**
     * <p>It is used to set if the page can be edited</p>
     * @param editable true if teh page can edited.
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * <p>Once we need to change the live session, we do not need to create a new panel.
     * All we need to do is use this method to update the new live session</p>
     * @param currentLive
     */
    public void setCurrentLive(LiveSession currentLive) {
        if(!isAdding) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String start = sdf.format(currentLive.getStartTime());
            String end = sdf.format(currentLive.getEndTime());

            this.currentLive = currentLive;
            coachId_lower.setVisible(false);
            coachId_upper.setVisible(false);
            num_lower.setText(""+currentLive.getAvailableNum());
            price_lower.setText("" + currentLive.getPrice());
            start_lower.setText(start);
            category_lower.setText(currentLive.getCategory());
            end_lower.setText(end);
            like_lower.setText(currentLive.getLikes() + "");
            rating_lower.setText(currentLive.getRating() + "");

            save.removeMouseListener(addingVideo);
            save.addMouseListener(modifyVideo);
            delete.setVisible(true);

            int buttonWidth = (int)(0.244 * UIStyle.width);
            int buttonStartY = (int)(0.1 * UIStyle.height);
            int buttonStartX = (int)(0.1 * UIStyle.width);
            int picWidth = 150;
            int picHeight = 150;
            if(pic != null)
                this.remove(pic);
            try {
                URL path = UIStyle.class.getClassLoader().getResource(currentLive.getCoach_coachId() + ".png");
                pic = new Picture(path, picWidth, picHeight);
                this.add(pic);
                pic.setBounds((int) (buttonStartX + 2.5 * buttonWidth - (picWidth - buttonWidth / 2)), buttonStartY + 200, picWidth, picHeight);
            }
            catch (Exception e)
            {
                ;
            }
        }
        else
        {
            this.currentLive = currentLive;
            num_lower.setText("");
            price_lower.setText("");
            start_lower.setText("");
            category_lower.setText("");
            end_lower.setText("");
            like_lower.setText("");
            rating_lower.setText("");
            coachId_lower.setText("");
            coachId_lower.setVisible(true);
            coachId_upper.setVisible(true);

            if(pic != null)
                this.remove(pic);
            save.addMouseListener(addingVideo);
            save.removeMouseListener(modifyVideo);
            delete.setVisible(false);

        }

        num_lower.setEditable(editable);
        price_lower.setEditable(editable);
        start_lower.setEditable(editable);
        category_lower.setEditable(editable);
        end_lower.setEditable(editable);
        like_lower.setEditable(editable);
        rating_lower.setEditable(editable);
        coachId_lower.setEditable(editable);

        if(editable)
        {
            book.setVisible(false);
            delete.setVisible(true);
            save.setVisible(true);
        }
        else
        {
            book.setVisible(true);
            delete.setVisible(false);
            save.setVisible(false);
        }
    }

}


