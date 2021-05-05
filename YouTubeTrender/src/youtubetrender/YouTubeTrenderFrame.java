package youtubetrender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * YoutubeTrenderFrame class extends jFrame which drives the GUI components
 */
public class YouTubeTrenderFrame extends JFrame {

    Dimension frmWDim = new Dimension(10, 0);
    Dimension frmHDim = new Dimension(0, 10);
    Dimension smlVgap = new Dimension(0, 5);
    Dimension smlHgap = new Dimension(5, 0);


    // jTextFields and jArea
    private JTextField jTextFieldDataFile;
    private JTextField jTextFieldChannel;
    private JTextField jTextFieldDate;
    private JTextField jTextFieldTitle;
    private JTextArea jTextAreaDescription;
    private JTextField jTextFieldViewCount;
    private JTextField jTextFieldLike;
    private JTextField jTextFieldComment;
    private JTextField jTextFieldDisLike;
    private JTextField jTextFieldSearch;
    private JTextField jTextFieldRepeats;
    private JTextField jTextFieldAssociatedVideos;
    private JTextArea jTextAreaVideoList;

    // List and Model components
    private JList<YouTubeVideo> jListVideo;
    private JList<YouTubeWordItem> jWordList;
    private DefaultListModel<YouTubeVideo> model;
    private List<YouTubeVideo> list;
    private DefaultListModel<YouTubeWordItem> wmodel = new DefaultListModel();
    private List<YouTubeWordItem> wordItemList;
    private ButtonGroup sortGroup;
    private String dataFile;


    /**
     * Constructs new YouTubeTrenderFrame
     */
    public YouTubeTrenderFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(650, 500));
        setResizable(false);
        initComponents();
    }

    /**
     * Driver method to run the GUI components
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) { // feel free to change this as you see fit.
                    // Available: Nimbus, CDE, Metal, Windows...
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(YouTubeTrenderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(() -> new YouTubeTrenderFrame().setVisible(true));
    }

    /**
     * Outputs all jPanels by adding them to Container Panels
     */
    private void initComponents() {

        JPanel jpnlContainerCenter = new JPanel();
        JPanel jpnlContainerNorth = new JPanel();
        JPanel jpnlContainerSouth = new JPanel();

        // Layout and Colour
        jpnlContainerCenter.setLayout(new BorderLayout());
        jpnlContainerNorth.setBorder(new MatteBorder(10, 10, 0, 10, new Color(204, 227, 240)));
        jpnlContainerCenter.setBorder(new MatteBorder(0, 10, 0, 10, new Color(204, 227, 240)));
        jpnlContainerSouth.setBorder(new MatteBorder(0, 10, 10, 10, new Color(204, 227, 240)));

        // Adding panels to the container and Modifying layout of panels w/ BorderLayout
        jpnlContainerCenter.add(Box.createRigidArea(frmHDim));
        jpnlContainerNorth.add(createTopPanel());
        jpnlContainerCenter.add(Box.createRigidArea(frmHDim));
        jpnlContainerCenter.add(createSortPanel(), BorderLayout.NORTH);
        jpnlContainerCenter.add(Box.createRigidArea(frmHDim));
        jpnlContainerCenter.add(createTrendingPanel(), BorderLayout.EAST);
        jpnlContainerCenter.add(Box.createRigidArea(frmHDim));
        jpnlContainerCenter.add(createVideoPanel(), BorderLayout.WEST);
        jpnlContainerCenter.add(Box.createRigidArea(frmHDim));
        jpnlContainerCenter.add(createVideoDetailsPanel(), BorderLayout.SOUTH);
        jpnlContainerSouth.add(createWordSearchPanel());

        // Add panels and Modifying Layout of Containers w/ BorderLayout
        add(jpnlContainerNorth, BorderLayout.NORTH);
        add(jpnlContainerCenter, BorderLayout.CENTER);
        add(jpnlContainerSouth, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Top Panel (North) of the GUI
     * Deals with loading and indexing data file
     * @return jPanel
     */
    private JPanel createTopPanel() {
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jTextFieldDataFile = new JTextField(60);
        jTextFieldDataFile.setText("src/data/youtubedata_15_50.json");

        JButton jButtonParseLoad = new JButton();
        JButton jButtonParseIndex = new JButton();

        jButtonParseLoad.setText("Load");
        jButtonParseIndex.setText("Index");
        jButtonParseLoad.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action of pressing Load Button
             * @param evt event of clicking button
             */
            public void actionPerformed(ActionEvent evt) {
                jButtonParseLoadActionPerformed(evt);
            }
        });

        jButtonParseIndex.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action of pressing Index Button
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                jButtonParseIndexActionPerformed(e);
            }
        });

        jPanel.add(Box.createRigidArea(frmWDim));
        jPanel.add(jTextFieldDataFile);
        jPanel.add(Box.createRigidArea(frmWDim));
        jPanel.add(jButtonParseLoad);
        jPanel.add(Box.createRigidArea(frmWDim));
        jPanel.add(jButtonParseIndex);
        jPanel.add(Box.createRigidArea(frmWDim));
        return jPanel;
    }

    /**
     * Sorting Panel which deals with sorting YouTubeVideos
     * @return jPanel
     */
    private JPanel createSortPanel() {

        JPanel jp = new JPanel();

        jp.setMinimumSize(new Dimension(500, 60));
        jp.setPreferredSize(new Dimension(500, 60));
        jp.setBorder(BorderFactory.createTitledBorder("Sort Criteria"));
        JRadioButton channel = new JRadioButton("Channel");
        JRadioButton date = new JRadioButton("Date");
        JRadioButton view = new JRadioButton("View");
        JRadioButton descr = new JRadioButton("Description");
        JRadioButton title = new JRadioButton("Title");
        JRadioButton like = new JRadioButton("Like");
        JRadioButton dislike = new JRadioButton("Dislike");
        JRadioButton comment = new JRadioButton("Comments");
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Listeners
        channel.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when Channel jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                channelActionPerformed(e);
            }
        });

        date.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when date jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                dateActionPerformed(e);
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when view jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                viewActionPerformed(e);
            }
        });

        descr.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when description jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                descriptionActionPerformed(e);
            }
        });

        title.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when title jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                titleActionPerformed(e);
            }
        });

        like.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when like jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                likeActionPerformed(e);
            }
        });

        dislike.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when dislike jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                disLikeActionPerformed(e);
            }
        });

        comment.addActionListener(new ActionListener() {
            @Override
            /**
             * Performed action when comment jRadio Button is pressed
             * @param e event of clicking button
             */
            public void actionPerformed(ActionEvent e) {
                commentActionPerformed(e);
            }
        });

        // Adds the contents to the JPanel
        jp.add(channel);
        jp.add(date);
        jp.add(view);
        jp.add(descr);
        jp.add(title);
        jp.add(like);
        jp.add(dislike);
        jp.add(comment);

        // ButtonGroup called sortGroup is created so that only one RadioButton is selected
        sortGroup = new ButtonGroup();
        sortGroup.add(channel);
        sortGroup.add(date);
        sortGroup.add(view);
        sortGroup.add(descr);
        sortGroup.add(like);
        sortGroup.add(dislike);
        sortGroup.add(comment);
        sortGroup.add(title);
        setVisible(true);

        return jp;
    }

    /**
     * Video Panel which deals with outputting video objects with Scroll Pane
     * @return jPanel
     */
    private JPanel createVideoPanel() {
        JPanel jp = new JPanel();
        JScrollPane jscrlpnListVideo = new JScrollPane();

        model = new DefaultListModel<YouTubeVideo>();
        jListVideo = new JList<YouTubeVideo>(model);

        jp.setMaximumSize(new Dimension(600, 240));
        jp.setMinimumSize(new Dimension(600, 240));
        jp.setBorder(BorderFactory.createTitledBorder("Videos"));

        jscrlpnListVideo.setPreferredSize(new Dimension(500, 200));
        jscrlpnListVideo.setViewportView(jListVideo);
        jp.add(jscrlpnListVideo);

        return jp;
    }

    /**
     *  Trending Panel outputs indexed words from data file
     * @return jPanel
     */
    private JPanel createTrendingPanel() {
        JPanel jpT = new JPanel();
        JScrollPane jscrlpnTrendingPanel = new JScrollPane();

        wmodel = new DefaultListModel<YouTubeWordItem>();
        jWordList = new JList<YouTubeWordItem>(wmodel);

        jpT.setMinimumSize(new Dimension(50, 100));
        jpT.setPreferredSize(new Dimension(129, 120));
        jpT.setBorder(BorderFactory.createTitledBorder("Trending"));
        jscrlpnTrendingPanel.setPreferredSize(new Dimension(105, 200));

        jscrlpnTrendingPanel.setViewportView(jWordList);
        jpT.add(jscrlpnTrendingPanel);
        setVisible(true);

        return jpT;
    }

    /**
     * Video Details panel outputs details of a video once clicked in Videos Panel
     * @return jPanel
     */
    private JPanel createVideoDetailsPanel() {
        JPanel jp = new JPanel();

        jp.setMinimumSize(new Dimension(600, 300));
        jp.setPreferredSize(new Dimension(600, 400));
        jp.setBorder(BorderFactory.createTitledBorder("Video Details"));
        JLabel jlblChannel = new JLabel("Channel");
        jTextFieldChannel = new JTextField();
        jTextFieldChannel.setEditable(false);
        JLabel jlblDate = new JLabel("Date Posted");
        jTextFieldDate = new JTextField();
        jTextFieldDate.setEditable(false);
        JLabel jlblTitle = new JLabel("Title");
        jTextFieldTitle = new JTextField();
        jTextFieldTitle.setEditable(false);
        JLabel jlblLike = new JLabel("Like Count");
        jTextFieldLike = new JTextField();
        jTextFieldLike.setEditable(false);
        JLabel jlblDisLike = new JLabel("Dislike Count");
        jTextFieldDisLike = new JTextField();
        jTextFieldDisLike.setEditable(false);
        JLabel jlblComment = new JLabel("Comment Count");
        jTextFieldComment = new JTextField();
        jTextFieldComment.setEditable(false);
        JLabel jlblDescription = new JLabel("Description");
        JScrollPane jscrlpnAreaDescription = new JScrollPane();
        jTextAreaDescription = new JTextArea();
        jTextAreaDescription.setEditable(false);
        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setLineWrap(true);
        jTextAreaDescription.setRows(5);
        jTextAreaDescription.setWrapStyleWord(true);
        jscrlpnAreaDescription.setViewportView(jTextAreaDescription);
        JLabel jlblViewCount = new JLabel("View Count");
        jTextFieldViewCount = new JTextField();
        jTextFieldViewCount.setEditable(false);
        JTextField jTextField = new JTextField(); // dummy to fill the gap
        jTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        jTextField.setEditable(false);
        jp.add(createHorizontalBox(jlblChannel, jTextFieldChannel));
        jp.add(createHorizontalBox(jlblDate, jTextFieldDate));
        jp.add(createHorizontalBox(jlblTitle, jTextFieldTitle));
        jp.add(createHorizontalBox(jlblViewCount, jTextFieldViewCount));
        jp.add(createHorizontalBox(jlblLike, jTextFieldLike));
        jp.add(createHorizontalBox(jlblDisLike, jTextFieldDisLike));
        jp.add(createHorizontalBox(jlblComment, jTextFieldComment));
        jp.add(createHorizontalBox(jlblDescription, jTextField));

        Box descriptionTextBox = Box.createHorizontalBox();
        descriptionTextBox.setPreferredSize(new Dimension(550, 109));
        descriptionTextBox.add(jscrlpnAreaDescription);
        jp.add(descriptionTextBox);

        jp.setLayout(new FlowLayout(FlowLayout.CENTER));
        jListVideo.addListSelectionListener(new ListSelectionListener() {
            @Override
            /**
             * Values of Video Details Panel changed
             * when another video is selected from jScrollPane in Videos Panel
             * @param e valueChanged when video object is selected
             */
            public void valueChanged(ListSelectionEvent e) {
                jVideoListData(e);
            }
        });

        return jp;
    }

    /**
     * Word Search Panel takes un user input and finds information about that word
     * @return jPanel
     */
    private JPanel createWordSearchPanel() {
        //A simple panel with the Word Search Features

        JPanel jp = new JPanel();
        JScrollPane jscrlpnAreaVideoList = new JScrollPane();
        jp.setBorder(BorderFactory.createTitledBorder("Word Searcher"));
        jp.setMinimumSize(new Dimension(600, 100));
        jp.setPreferredSize(new Dimension(650, 250));

        jTextFieldSearch = new JTextField(10);
        jTextFieldSearch.setText("");
        jp.add(Box.createRigidArea(frmWDim));
        jp.add(jTextFieldSearch);

        JButton jButtonParseWordSearch = new JButton();
        jButtonParseWordSearch.setText("Search");
        JButton jButtonParseSearchAgain = new JButton();
        jButtonParseSearchAgain.setText("Search again");

        jp.add(jButtonParseWordSearch);
        jp.add(jButtonParseSearchAgain);
        JLabel jlblRepeats = new JLabel("Word Repeats");
        jTextFieldRepeats = new JTextField();
        jTextFieldRepeats.setEditable(false);
        jp.add(jTextFieldRepeats);
        jp.add(jlblRepeats);
        jp.add(createHorizontalBox(jlblRepeats, jTextFieldRepeats));

        JLabel jlblAssociatedVideos = new JLabel("Number of videos");
        jTextFieldAssociatedVideos = new JTextField();
        jTextFieldAssociatedVideos.setEditable(false);
        jp.add(jTextFieldAssociatedVideos);
        jp.add(jlblAssociatedVideos);
        jp.add(createHorizontalBox(jlblAssociatedVideos, jTextFieldAssociatedVideos));


        JLabel jlblCVT = new JLabel("Video Titles");
        jTextAreaVideoList = new JTextArea();
        jTextAreaVideoList.setEditable(false);
        jTextAreaVideoList.setColumns(20);
        jTextAreaVideoList.setLineWrap(true);
        jTextAreaVideoList.setRows(5);
        jTextAreaVideoList.setWrapStyleWord(true);
        jscrlpnAreaVideoList.setViewportView(jTextAreaVideoList);

        JTextField a = new JTextField();
        a.setBorder(new EmptyBorder(0, 0, 0, 0));
        a.setEditable(false);

        jp.add(createHorizontalBox(jlblCVT, a));


        Box ListedVideosAndChannels = Box.createHorizontalBox();
        ListedVideosAndChannels.setPreferredSize(new Dimension(550, 90));
        ListedVideosAndChannels.add(jscrlpnAreaVideoList);
        jp.add(ListedVideosAndChannels);


        //Listeners

        jButtonParseSearchAgain.addActionListener(new ActionListener() {
            /**
             * Clears the panel
             * @param evt event triggered when button is pressed
             */
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonParseSearchAgainActionPerformed(evt);
                } catch (Exception e) {
                }
            }
        });

        //Word Search Listener
        //If an exception is encountered, displays an error with possible reasons why. Also clears the search field,
        jButtonParseWordSearch.addActionListener(new ActionListener() {
            /**
             * Searches the word and finds information, if not, exception is caught
             * @param evt event triggered when button is pressed
             */
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButtonParseWordSearch(evt);
                } catch (Exception e) {
                    JPanel errorPanel = new JPanel();
                    JOptionPane.showMessageDialog(errorPanel, "An error occurred. Possible reasons include: " +
                            "\nThe search box is empty." +
                            "\nThe word does not exist in the file." +
                            "\nThe word is not mentioned in the video title and description.", "Error", JOptionPane.ERROR_MESSAGE);
                    jTextFieldSearch.setText("");
                    jTextFieldAssociatedVideos.setText("");
                    jTextFieldRepeats.setText("");
                    jTextAreaVideoList.setText("");
                }
            }
        });

        return jp;
    }

    /**
     * Method which sets the WordSearch Panel to ""
     * @param evt event from actionPerformed
     */
    private void jButtonParseSearchAgainActionPerformed(ActionEvent evt) {
        jTextFieldSearch.setText("");
        jTextFieldRepeats.setText("");
        jTextAreaVideoList.setText("");
        jTextFieldAssociatedVideos.setText("");
    }

    /**
     * Convenience method to create a Horizontal Box
     * @param jLabel     the label on the left of the box
     * @param jTextField the text field on the right of the box
     * @return the horizontal box
     */

    private Box createHorizontalBox(JLabel jLabel, JTextField jTextField) {
        Box b = Box.createHorizontalBox();
        b.setPreferredSize(new Dimension(550, 25));
        jLabel.setPreferredSize(new Dimension(100, 25));
        jLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        b.add(jLabel);
        b.add(Box.createRigidArea(smlHgap));
        b.add(jTextField);

        return b;
    }

    /**
     * Method which sets the text of the Video Details panel
     * @param evt event from actionPerformed
     */
    private void jVideoListData(ListSelectionEvent evt) {
        try {
            jTextFieldChannel.setText(model.get(jListVideo.getSelectedIndex()).getChannel());
            jTextFieldDate.setText(model.get(jListVideo.getSelectedIndex()).getDate());
            jTextFieldTitle.setText(model.get(jListVideo.getSelectedIndex()).getTitle());
            jTextFieldViewCount.setText(String.valueOf(model.get(jListVideo.getSelectedIndex()).getViewCount()));
            jTextFieldLike.setText(String.valueOf(model.get(jListVideo.getSelectedIndex()).getLikeCount()));
            jTextFieldDisLike.setText(String.valueOf(model.get(jListVideo.getSelectedIndex()).getDislikeCount()));
            jTextFieldComment.setText(String.valueOf(model.get(jListVideo.getSelectedIndex()).getCommentCount()));
            jTextAreaDescription.setText(model.get(jListVideo.getSelectedIndex()).getDescription());
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    /**
     * Method to handle the event of pushing the "Load" button
     * @param evt the Action Event associated with this button
     */
    private void jButtonParseLoadActionPerformed(ActionEvent evt) {

        String dataFile = jTextFieldDataFile.getText();
        JPanel errorPanel = new JPanel();

        YouTubeDataParser parser = new YouTubeDataParser();
        list = null;
        try {
            list = parser.parse(dataFile);
        } catch (YouTubeDataParserException ex) {
            // TODO: maybe notify the user something has happened!
            JOptionPane.showMessageDialog(errorPanel, "Could not open file", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (list != null) {
            // YouTube data has been parsed successfully!
            // TODO: connect the list to the GUI
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }

        //Clears the fields before loading the data to ensure there are no problems displaying it.

        wmodel.clear();
        sortGroup.clearSelection();
        sortGroup.clearSelection();
        jTextFieldChannel.setText("");
        jTextFieldDate.setText("");
        jTextFieldTitle.setText("");
        jTextFieldViewCount.setText("");
        jTextFieldLike.setText("");
        jTextFieldDisLike.setText("");
        jTextFieldComment.setText("");
        jTextAreaDescription.setText("");
        jTextFieldRepeats.setText("");
        jTextAreaVideoList.setText("");
        jTextFieldSearch.setText("");

    }

    /**
     * Method to handle the event of pushing the "Index" button
     * @param evt the Action Event associated with this button
     */
    private void jButtonParseIndexActionPerformed(ActionEvent evt) {
        YouTubeDataParser parser = new YouTubeDataParser();
        dataFile = jTextFieldDataFile.getText();
        JPanel errorPanel = new JPanel();
        list = null;
        try {
            list = parser.parse(dataFile);
        } catch (YouTubeDataParserException e) {
            JOptionPane.showMessageDialog(errorPanel, "Could not index file", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (list != null) {
            YouTubeVideoIndexer indexer = new YouTubeVideoIndexer(list);
            indexer.index();
            wordItemList = indexer.getSortedYouTubeWordItems();

            if (wmodel != null) {
                for (int i = 0; i < wordItemList.size(); i++) {
                    wmodel.addElement(wordItemList.get(i));
                }
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Search" button
     * @param evt the Action Event associated with this button
     */
    private void jButtonParseWordSearch(ActionEvent evt) throws Exception {

        //Simply settings the text fields to the respective value of the invoked indexer method.
        if (list != null) {
            YouTubeVideoIndexer indexer = new YouTubeVideoIndexer(list);
            indexer.index();
            jTextFieldRepeats.setText(String.valueOf(indexer.getWordCount(jTextFieldSearch.getText())));
            jTextFieldAssociatedVideos.setText(String.valueOf(indexer.getWordVideos(jTextFieldSearch.getText()).size()));
            jTextAreaVideoList.setText(indexer.WordSearch(jTextFieldSearch.getText()));

        }
    }

    /**
     * Method to handle the event of pushing the "Channel" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void channelActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoChannelComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Date" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void dateActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoDateComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Description" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void descriptionActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoDescriptionComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "View" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void viewActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoViewComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Title" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void titleActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoVideoTitleComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Like" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void likeActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoLikeComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Dislike" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void disLikeActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoDislikeComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }

    /**
     * Method to handle the event of pushing the "Comment" jRadio button
     * @param evt the Action Event associated with this button
     */
    private void commentActionPerformed(ActionEvent evt) {
        if (list != null) {
            list.sort(new YouTubeVideoCommentComparator());
            model.clear();
            for (int i = 0; i < list.size(); i++) {
                model.addElement(list.get(i));
            }
        }
    }
}
