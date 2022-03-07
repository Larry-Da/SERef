package org.qmbupt.grp105.UI;

import org.qmbupt.grp105.Controller.VideoController;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.UI.MyUIComponent.*;
import org.qmbupt.grp105.UI.MyUIComponent.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * <p>Showing all the videos. And customer can search, comment and sort videos here</p>
 * @author daliangrun
 * @version 5.3
 */
public class VirtualClassPanel extends JPanel
{
    private SearchPanel searchPanel;
    private CategoryPanel categoryPanel;
    private CardLayout searchCards;
    private JPanel contentPanel;
    public VirtualClassPanel(CardLayout cards, MainPanel mainPanel)
    {
        MenuBar menuBar = new MenuBar(cards, mainPanel, "Classes");
        menuBar.setVisible(true);
        this.setLayout(null);
        this.add(menuBar);
        int barHeight = (int)(UIStyle.height) / 10;


        contentPanel = new JPanel();
        contentPanel.setBounds(0, barHeight, (int)UIStyle.width, (int)(UIStyle.height - barHeight));
        this.add(contentPanel);
        contentPanel.setVisible(true);
        searchCards = new CardLayout();
        contentPanel.setLayout(searchCards);

        searchPanel = new SearchPanel(searchCards, contentPanel, cards, mainPanel);
        contentPanel.add(searchPanel, "searchPanel");

        categoryPanel = new CategoryPanel(searchCards, contentPanel, searchPanel);
        contentPanel.add(categoryPanel, "categoryPanel");

        searchCards.show(contentPanel, "categoryPanel");

    }

    /**
     * show the top 4 hottest video
     */
    public void updateHotVideo()
    {
        searchCards.show(contentPanel, "searchPanel");
        searchPanel.updateHottestVideos();
    }
}

/**
 * Showing 6 categories for the customer to choose.
 * @author daliangrun
 * @version 5.3
 */
class CategoryPanel extends JPanel
{
    public CategoryPanel(CardLayout cards, JPanel contentPanel, SearchPanel searchPanel) {
        this.setLayout(null);
        this.setBackground(Color.black);
        int buttonWidth = (int) (UIStyle.width / 3);
        int buttonHeight = (int) ((UIStyle.height  - UIStyle.barHeight) / 2);

        CategoryButton bicycle = new CategoryButton(UIStyle.VirtualClass_bicycle, buttonWidth, buttonHeight, "Bicycle Training");
        bicycle.setBounds(0, 0, buttonWidth, buttonHeight);
        this.add(bicycle);
        bicycle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(contentPanel, "searchPanel");
                searchPanel.setCate("Bicycle Training");
                searchPanel.updateRes();

            }
        });

        CategoryButton HITT = new CategoryButton(UIStyle.VirtualClass_HITT, buttonWidth, buttonHeight, "HITT");
        HITT.setBounds(buttonWidth, 0, buttonWidth, buttonHeight);
        this.add(HITT);
        HITT.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(contentPanel, "searchPanel");
                searchPanel.setCate("HITT");
                searchPanel.updateRes();

            }
        });

        CategoryButton flexbility = new CategoryButton(UIStyle.VirtualClass_flexibility, buttonWidth, buttonHeight, "Flexibility");
        flexbility.setBounds(buttonWidth * 2, 0, buttonWidth, buttonHeight);
        this.add(flexbility);
        flexbility.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(contentPanel, "searchPanel");
                searchPanel.setCate("Flexibility");
                searchPanel.updateRes();

            }
        });

        CategoryButton yoga = new CategoryButton(UIStyle.VirtualClass_yoga, buttonWidth, buttonHeight, "Yoga");
        yoga.setBounds(0, buttonHeight, buttonWidth, buttonHeight);
        this.add(yoga);
        yoga.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(contentPanel, "searchPanel");
                searchPanel.setCate("Yoga");
                searchPanel.updateRes();

            }
        });

        CategoryButton strength = new CategoryButton(UIStyle.VirtualClass_strength, buttonWidth, buttonHeight, "Strength");
        strength.setBounds(buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.add(strength);
        strength.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(contentPanel, "searchPanel");
                searchPanel.setCate("Strength");
                searchPanel.updateRes();

            }
        });

        CategoryButton weightLoss = new CategoryButton(UIStyle.VirtualClass_loseWeight, buttonWidth, buttonHeight, "Weight Loss");
        weightLoss.setBounds(2 * buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.add(weightLoss);
        weightLoss.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cards.show(contentPanel, "searchPanel");
                searchPanel.setCate("Weight Loss");
                searchPanel.updateRes();

            }
        });



    }
}

/**
 * <p>search panel is used for the customer to enter key words and give filter conditions</p>
 * @author daliangrun
 * @version 5.3
 */
class SearchPanel extends JPanel
{
    private int pageMax;
    public static int SearchResultPanelHeight;
    private InputText searchBar;
    private CardLayout resultCards;
    private ArrayList<SearchResultPanel> searchResultPanels =new ArrayList<>();
    FilterBox categoryFilter;
    private JPanel resultContentPanel;
    private CardLayout mainCards;
    private MainPanel mainPanel;
    private FilterBox sortFilter;
    private String[] sortString = {"Sort", "Like", "Rating", "View"};



    public SearchPanel(CardLayout cards, JPanel contentPanel, CardLayout mainCards, MainPanel mainPanel)
    {
        this.setLayout(null);
        this.mainCards = mainCards;
        this.mainPanel = mainPanel;

        this.setBackground(Color.decode("#14151A"));
        searchBar = new InputText(500, 50, 40, true, (int)(UIStyle.width / 2), 55, "Search", true);
        this.add(searchBar);
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRes();
            }
        });


        int startFilter = 90;
        categoryFilter = new FilterBox(startFilter, UIStyle.categories, "dark");
        this.add(categoryFilter);

        sortFilter = new FilterBox(startFilter + 40, sortString, "dark", true);
        this.add(sortFilter);

        SearchResultPanelHeight = (int)(UIStyle.height - UIStyle.barHeight - UIStyle.height / 4);

        resultCards = new CardLayout();
        resultContentPanel = new JPanel();
        resultContentPanel.setBounds(0, (int)(UIStyle.height / 4), (int)(UIStyle.width),(int)(UIStyle.height));
        this.add(resultContentPanel);
        resultContentPanel.setLayout(resultCards);

        updateRes();
        resultContentPanel.setVisible(true);


    }

    /**
     * set category to be shown
     * @param cate category
     */
    public void setCate(String cate)
    {
        int cnt = 0;
        for(String i : UIStyle.categories)
        {
            if(i.equals(cate))
            {
                categoryFilter.setState(true, cnt - 1);
            }
            cnt++;
        }
    }

    /**
     * show the hottest videos in the search result
     */
    public void updateHottestVideos()
    {
        ArrayList<Video> hot = VideoController.getController().getHotVideo(4);
        pageMax = hot.size() / 4;
        for(SearchResultPanel i : searchResultPanels)
        {
            resultContentPanel.remove(i);
        }
        searchResultPanels.clear();

        for(int i = 0; i <= pageMax; i++)
        {
            searchResultPanels.add(new SearchResultPanel(hot, pageMax, resultCards, resultContentPanel, mainCards, mainPanel, i + 1));
            resultContentPanel.add(searchResultPanels.get(i), i + 1 + "");
        }
        resultCards.first(resultContentPanel);
    }

    /**
     * update the search result by keywords, filter condition.
     */
    public void updateRes()
    {
        String key = searchBar.getText();
        if(key.equals("Search") || key.equals(""))
        {
            key = null;
        }
        ArrayList<Video> videos = VideoController.getController().getVideosByName(key);
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
        ArrayList<Video> videos1 = VideoController.getController().filterByCategory(videos, keyCategory);
        states = sortFilter.getStates();
        cnt = 1;
        String sortKey = null;
        for(boolean i : states)
        {
            if(i)
            {
                sortKey = sortString[cnt];
            }
            cnt++;
        }

        videos1 = VideoController.getController().sort(videos1, sortKey);
        pageMax = videos1.size() / 4;
        for(SearchResultPanel i : searchResultPanels)
        {
            resultContentPanel.remove(i);
        }
        searchResultPanels.clear();

        for(int i = 0; i <= pageMax; i++)
        {
            searchResultPanels.add(new SearchResultPanel(videos1, pageMax, resultCards, resultContentPanel, mainCards, mainPanel, i + 1));
            resultContentPanel.add(searchResultPanels.get(i), i + 1 + "");
        }
        resultCards.first(resultContentPanel);// if there is no this statement, updateRes from only 1 page to another only 1 page will show blank

    }
}

/**
 * <p>It is used to show the search result that customers want to see</p>
 * @author daliangrun
 * @version 5.3
 */
class SearchResultPanel extends JPanel
{
    public SearchResultPanel(ArrayList<Video> videos, int pageMax, CardLayout resultCards, JPanel resultContentPanel, CardLayout cards, MainPanel mainPanel, int page)
    {
        this.setLayout(null);
        this.setBackground(Color.decode("#14151A"));

        int xinterval =(int)((UIStyle.width - 100 - 100 - 200) / 3);

        for(int i = (page - 1) * 4; i < videos.size() && i < (page) * 4; i++)
        {
            VideoPanel video = new VideoPanel(videos.get(i), 100+ xinterval * (i % 4), 70, mainPanel, cards, "small");
            this.add(video);
        }
        JSlider pages = new JSlider(1, pageMax + 1, page);
        pages.setValue(page);


        int pagesWidth = 100;
        int pagesHeight = 50;
        pages.setBounds((int)(UIStyle.width / 2 - pagesWidth / 2), (int)(SearchPanel.SearchResultPanelHeight - pagesHeight), pagesWidth,pagesHeight);

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
        pageShow.setBounds((int)(UIStyle.width / 2 + pagesWidth / 1.5), (int)(SearchPanel.SearchResultPanelHeight - pagesHeight + 10), 30, 30);
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