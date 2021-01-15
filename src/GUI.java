import GameLogic.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Class that implements the GUI of the Buzz Game
 */
public class GUI{
    private JFrame window;
    private JButton answer1;
    private GamePlayer player1 = new GamePlayer();
    private GamePlayer player2 = new GamePlayer();
    private final Player user1 = new Player("user1");
    private final Player user2 = new Player("user2");
    private ImageIcon icon;
    private int betPlayer1 = 0;
    private int betPlayer2 = 0;


    /**
     * The constructor of the GUI and the first window that pops up.
     * @param dim the size of the window
     */
    public GUI(Dimension dim){
        JButton playButton, signInButton, statisticsButton, infoOfGame;
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.CENTER));
        window.setLayout(new GridLayout(5,1));


        playButton = new JButton("Παίξε");
        playButton.setFont(new Font("Arial", Font.PLAIN, 25));

        signInButton = new JButton("Εγραφή νέου χρήστη");
        signInButton.setFont(new Font("Arial", Font.PLAIN, 25));
        statisticsButton = new JButton("Στατιστικά");
        statisticsButton.setFont(new Font("Arial", Font.PLAIN, 25));
        infoOfGame = new JButton("Σχετικά");
        infoOfGame.setFont(new Font("Arial", Font.PLAIN, 25));
        String imgName = "/Images/BuzzLogo.jpeg";
        URL imageURL = getClass().getResource(imgName);
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
            window.setIconImage(icon.getImage());

        }
        Image newImg = icon.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        JLabel label = new JLabel(icon, SwingConstants.CENTER);


        window.add(label);
        window.add(playButton);
        window.add(signInButton);
        window.add(statisticsButton);
        window.add(infoOfGame);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }

                Dimension dim = new Dimension(200, 150);
                player1 = new GamePlayer();
                player2 = new GamePlayer();
                startPlaying(dim);
            }
        });

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                Dimension dim = new Dimension(100, 50);
                afterCheckSignIn(dim);

            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                Dimension dim = new Dimension(650, 500);
                try {
                    statisticsGui(dim);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        infoOfGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                Dimension dim = new Dimension(650, 500);
                info(dim);
            }
        });
    }

    /**
     * Info page for the gameplay and information of the programmer.
     * @param dim size of the frame
     */
    public void info(Dimension dim){
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));


        JLabel info1 = new JLabel();
        info1.setText("<html><p style=\"width:600px; font-weight: 400; font-size: 13px\">"+"Το <strong>Buzz Game</strong> είναι ένα παιχνίδι γνώσεων το οποίο μπορεί να παιχτεί από έναν ή από δύο παίκτες ταυτόχρονα. Αν δεν είσαι ήδη εγγεγραμένος ώς παίκτης του παιχνιδιού μπορείς να πατήσεις πάνω στο κουμπί <b>Εγγραφή νέου χρήστη</b> και να συμπληρώσεις το όνομα " +
                "που επιθυμείς να έχεις στο παιχνίδι. Αν είσαι εγγεγραμένος πάικτης και απλώς θέλεις να δεις τις επιδόσεις σου στα παιχνίδια μπορείς να πατήσεις πάνω " +
                "στο κουμπί <b>Στατιστικά</b> και εφόσον βρείς το όνομά σου να δεις τα στατιστικά σου σαν παίκτη. " +
                "Αν έχεις σκοπό να πάιξεις το παιχνίδι γνώσεων τότε πρέπει να πατήσεις πάνω στο κουμπί <b>Παίξε</b> να διαλέξεις αν θα συνεχίσεις μόνος σου ή με παρέα " +
                "και να έρθεις αντιμέτωπος με την πρώτη ερώτηση! <br /><br /> <b>1 παίκτης:</b> επιλέγεις με το ποντίκι την απάντηση που θές και βλέπεις το σκόρ σου. " +
                "<br /><br /><b>2 παίκτες:</b> ο παίκτης από τα αριστερά(1ος παίκτης) χρησιμοποιεί τα πλήκτρα <b>q, w, e, r</b> και ο πάικτης από τα δεξιά(2ος παίκτης) χρησιμοποιεί τα πλήκτρα " +
                "<b>u, i, o, p</b> για να επιλέξει την 1η, 2η, 3η, 4η απάντηση αντίστοιχα. <br /><br /><b>Έκδοση Προγράμματος:</b> Version 2.0 Buzz Game<br /><br /><b>Στοιχεία προγραμματιστών:</b> Νικόλαος Τζαβίδας, Εμμανουήλ Ελευθεριάδης φοιτητες Τμήματος " +
                "Πληροφορικής ΑΠΘ και το project μπορείται να το βρείτε στο <a href=\"https://github.com/auth-csd-oop-2020/buzzquizworld-thelamogia\">github</a>"+"</p></html>");


        info1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goWebsite(info1);
        frame.add(info1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * When the JLabel clicked then goes to the browser to our project's github repo.
     * @param website target JLabel
     */
    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/auth-csd-oop-2020/buzzquizworld-thelamogia"));
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }


    /**
     * Routes the GUI to the right screen based on the round type and single/multiplayer
     * @param game reference to our GameFacade
     * @param solo true if 1 player, false if 2 players
     */
    public void checktheTypeOfRound(GameFacade game, boolean solo) {
        Dimension dim = new Dimension(800, 500);
        switch (game.getCurrentRound().getRoundName()){
            case 1:
                if(solo){
                    rightQuestionRound(dim, game);
                }else {
                    rightQuestionForTwoPlayers(dim, game);
                }
                break;
            case 2:
                if(solo) {
                    stopTheClockRound(dim, game);
                }else{
                    stopTheClock2Round(dim, game);
                }
                break;
            case 3:
                Dimension dimBet = new Dimension(200,150);
                if(solo){
                    bettingChoises(dimBet, game);
                }else{
                    bettingChoissesForTwoPlayers(dim, game);
                }
                break;
            case 4:
                if(!solo) {
                    quickAnswerRound(dim, game);
                }
                break;
            case 5:
                if(!solo) {
                    thermometerRound(dim, game);
                }
                break;
        }
    }




    /**
     * Sets up the screen of the StopTheClock for 2 players
     * @param dim size of the frame
     * @param game reference to our GameFacade
     */
    private void stopTheClock2Round(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel centralPanel = new JPanel();
        CountDown clock = new CountDown() {
            @Override
            public void onFinish() {
                frame.setVisible(false);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    if(player1.getScore()>player2.getScore()){
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            user1.newDataWithTwoPlayers(user1.getName(), true);
                            user2.newDataWithTwoPlayers(user2.getName(), false);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    }else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                JOptionPane.INFORMATION_MESSAGE);
                        try {
                            user1.newDataWithTwoPlayers(user1.getName(), false);
                            user2.newDataWithTwoPlayers(user2.getName(), true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                }else {
                    checktheTypeOfRound(game, false);
                }
            }
        };
        clock.setBorder(compound);


        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ "+user1.getName()+": "+(int)player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ "+user2.getName()+": "+(int)player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);
        centralPanel.add(clock);


        JPanel panel2 = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);


        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.cyan);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        int rightAnswerIndex = game.getCurrentQuestion(player1).getRightAnswerIndex();

        ArrayList<Integer> playerTwoOrder = new ArrayList<>();
        playerTwoOrder.add(0);
        playerTwoOrder.add(1);
        playerTwoOrder.add(2);
        playerTwoOrder.add(3);
        Collections.shuffle(playerTwoOrder);

        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(0)));
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(1)));
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(2)));
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(3)));

        KeyListener myListener = new KeyListener() {
            int answerIndex = -1;
            boolean pressed1 = false;
            boolean pressed2 = false;
            int time1, time2;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        time1 = clock.getRemainingTime();
                        game.answerQuestion(player1, answerIndex, time1);

                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        time2 = clock.getRemainingTime();
                        game.answerQuestion(player2, playerTwoOrder.get(answerIndex), time2);

                    }
                }

                if((pressed1 && pressed2) || (clock.getSecond()==0 && clock.getMilliSecond()==1)){
                    answer2.setBackground(Color.RED);
                    answer3.setBackground(Color.RED);
                    answer4.setBackground(Color.RED);
                    answer5.setBackground(Color.RED);

                    answer6.setBackground(Color.RED);
                    answer7.setBackground(Color.RED);
                    answer8.setBackground(Color.RED);
                    answer1.setBackground(Color.RED);

                    switch(rightAnswerIndex){
                        case 0:
                            answer2.setBackground(Color.GREEN);
                            break;
                        case 1:
                            answer3.setBackground(Color.GREEN);
                            break;
                        case 2:
                            answer4.setBackground(Color.GREEN);
                            break;
                        case 3:
                            answer5.setBackground(Color.GREEN);
                            break;
                    }

                    if(rightAnswerIndex == playerTwoOrder.get(0)) {
                        answer6.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(1)) {
                        answer7.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(2)) {
                        answer8.setBackground(Color.GREEN);
                    } else {
                        answer1.setBackground(Color.GREEN);
                    }

                    clock.buttonClicked();

                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        frame.setVisible(false);
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        new CountDown(1) {
                            @Override
                            public void onFinish() {
                                frame.setVisible(false);
                                checktheTypeOfRound(game, false);
                            }
                        };

                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        answer2.addKeyListener(myListener);
        answer3.addKeyListener(myListener);
        answer4.addKeyListener(myListener);
        answer5.addKeyListener(myListener);



        answer6.addKeyListener(myListener);
        answer7.addKeyListener(myListener);
        answer8.addKeyListener(myListener);
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);





        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        jsp1.setDividerLocation((frame.getBounds().width / 2)-10);
    }




    /**
     * Sets up the screen of the QuickAnswerRound (only for 2 players)
     * @param dim the size of the window
     * @param game reference to our GameFacade
     */
    private void quickAnswerRound(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion,border);

        JPanel centralPanel = new JPanel();



        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ "+user1.getName()+": "+player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ "+user2.getName()+": "+player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);



        JPanel panel2 = new JPanel();
        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        panel1.setBackground(Color.orange);



        JSplitPane jsp = new JSplitPane();
        jsp.setLayout(new GridLayout(2,2));


        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel1, panel2);
        jsp.setDividerLocation(dim.width / 2);

        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.cyan);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        int rightAnswerIndex = game.getCurrentQuestion(player1).getRightAnswerIndex();

        ArrayList<Integer> playerTwoOrder = new ArrayList<>();
        playerTwoOrder.add(0);
        playerTwoOrder.add(1);
        playerTwoOrder.add(2);
        playerTwoOrder.add(3);
        Collections.shuffle(playerTwoOrder);

        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(0)));
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(1)));
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(2)));
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(3)));


        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex);
                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, playerTwoOrder.get(answerIndex));
                    }
                }

                if(pressed1 && pressed2){
                    answer2.setBackground(Color.RED);
                    answer3.setBackground(Color.RED);
                    answer4.setBackground(Color.RED);
                    answer5.setBackground(Color.RED);

                    answer6.setBackground(Color.RED);
                    answer7.setBackground(Color.RED);
                    answer8.setBackground(Color.RED);
                    answer1.setBackground(Color.RED);

                    switch(rightAnswerIndex){
                        case 0:
                            answer2.setBackground(Color.GREEN);
                            break;
                        case 1:
                            answer3.setBackground(Color.GREEN);
                            break;
                        case 2:
                            answer4.setBackground(Color.GREEN);
                            break;
                        case 3:
                            answer5.setBackground(Color.GREEN);
                            break;
                    }

                    if(rightAnswerIndex == playerTwoOrder.get(0)) {
                        answer6.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(1)) {
                        answer7.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(2)) {
                        answer8.setBackground(Color.GREEN);
                    } else {
                        answer1.setBackground(Color.GREEN);
                    }


                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        frame.setVisible(false);
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        new CountDown(1) {
                            @Override
                            public void onFinish() {
                                frame.setVisible(false);
                                checktheTypeOfRound(game, false);
                            }
                        };

                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        answer2.addKeyListener(myListener);
        answer3.addKeyListener(myListener);
        answer4.addKeyListener(myListener);
        answer5.addKeyListener(myListener);



        answer6.addKeyListener(myListener);
        answer7.addKeyListener(myListener);
        answer8.addKeyListener(myListener);
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);





        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        jsp1.setDividerLocation((frame.getBounds().width / 2)-10);

    }




    /**
     * Sets up the screen of the ThermometerRound (only for 2 players)
     * @param dim size of the window
     * @param game reference to our GameFacade
     */
    private void thermometerRound(Dimension dim, GameFacade game) {
        Frame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion,border);

        JPanel centralPanel = new JPanel();



        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ "+user1.getName()+": "+player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ "+user2.getName()+": "+player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);

        JLabel question1 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question1.setBorder(compoundQuestion);





        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);




        JLabel player1Right = new JLabel(String.valueOf(((ThermometerRound)game.getCurrentRound() ) .getPlayerWins(player1)));
        player1Right.setBorder(compound);
        JLabel player2Right = new JLabel(String.valueOf(((ThermometerRound)game.getCurrentRound() ) .getPlayerWins(player2)));
        player2Right.setBorder(compound);

        panel1.add(player1Right);
        panel1.add(question1);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel1.add(labelWithIcon);
        }
        panel1.add(player2Right);

        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.cyan);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        int rightAnswerIndex = game.getCurrentQuestion(player1).getRightAnswerIndex();

        ArrayList<Integer> playerTwoOrder = new ArrayList<>();
        playerTwoOrder.add(0);
        playerTwoOrder.add(1);
        playerTwoOrder.add(2);
        playerTwoOrder.add(3);
        Collections.shuffle(playerTwoOrder);

        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(0)));
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(1)));
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(2)));
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(3)));



        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex);
                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, playerTwoOrder.get(answerIndex));
                    }
                }

                if(pressed1 && pressed2){
                    answer2.setBackground(Color.RED);
                    answer3.setBackground(Color.RED);
                    answer4.setBackground(Color.RED);
                    answer5.setBackground(Color.RED);

                    answer6.setBackground(Color.RED);
                    answer7.setBackground(Color.RED);
                    answer8.setBackground(Color.RED);
                    answer1.setBackground(Color.RED);

                    switch(rightAnswerIndex){
                        case 0:
                            answer2.setBackground(Color.GREEN);
                            break;
                        case 1:
                            answer3.setBackground(Color.GREEN);
                            break;
                        case 2:
                            answer4.setBackground(Color.GREEN);
                            break;
                        case 3:
                            answer5.setBackground(Color.GREEN);
                            break;
                    }

                    if(rightAnswerIndex == playerTwoOrder.get(0)) {
                        answer6.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(1)) {
                        answer7.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(2)) {
                        answer8.setBackground(Color.GREEN);
                    } else {
                        answer1.setBackground(Color.GREEN);
                    }

                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        frame.setVisible(false);
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        new CountDown(1) {
                            @Override
                            public void onFinish() {
                                frame.setVisible(false);
                                checktheTypeOfRound(game, false);
                            }
                        };

                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);

        answer2.addKeyListener(myListener);
        answer3.addKeyListener(myListener);
        answer4.addKeyListener(myListener);
        answer5.addKeyListener(myListener);




        answer6.addKeyListener(myListener);
        answer7.addKeyListener(myListener);
        answer8.addKeyListener(myListener);
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);





        frame.add(centralPanel);
        frame.add(panel1);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        jsp1.setDividerLocation((frame.getBounds().width / 2)-10);
    }



    /**
     * Sets up the screen of the BettingRound for 1 player
     * @param dim size of the frame
     * @param game reference to our GameFacade
     */
    public void bettingRound(Dimension dim, GameFacade game){
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compoundQuestion);

        JLabel label4 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Κατηγορία ερώτησης: " +  game.getCurrentQuestion(player1).getCategory());
        label5.setBorder(compound);




        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label5);
        panel1.add(label2);
        frame.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(label3);

        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();

            URL imageURL = getClass().getResource("Images/" + imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);
            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }






        frame.add(panel2);



        JPanel panel3 = new JPanel();
        JButton answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer1.setBorderPainted(true);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        panel3.add(answer1);
        panel3.add(answer2);
        panel3.add(answer3);
        panel3.add(answer4);
        frame.add(panel3);






        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answer1.setBackground(Color.RED);
                answer2.setBackground(Color.RED);
                answer3.setBackground(Color.RED);
                answer4.setBackground(Color.RED);
                int answerIndex = -1;
                if(e.getSource() == answer1){
                    answerIndex=0;
                }else if(e.getSource() == answer2){
                    answerIndex=1;
                } else if(e.getSource() == answer3){
                    answerIndex = 2;
                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }


                switch(game.getCurrentQuestion(player1).getRightAnswerIndex()){
                    case 0:
                        answer1.setBackground(Color.GREEN);
                        break;
                    case 1:
                        answer2.setBackground(Color.GREEN);
                        break;
                    case 2:
                        answer3.setBackground(Color.GREEN);
                        break;
                    case 3:
                        answer4.setBackground(Color.GREEN);
                        break;
                }

                playSound(answerIndex == game.getCurrentQuestion(player1).getRightAnswerIndex());

                game.answerQuestion(player1, answerIndex, betPlayer1);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);
                    try {
                        user1.newDataAlone(user1.getName(), (int)player1.getScore());
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                }else {
                    new CountDown(1) {
                        @Override
                        public void onFinish() {
                            frame.setVisible(false);
                            checktheTypeOfRound(game, true);
                        }
                    };

                }

            }
        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
    }



    /**
     * Sets up the screen of the StopTheClock for 1 player
     * @param dim size of the frame
     * @param game reference to our GameFacade
     */
    public void stopTheClockRound(Dimension dim, GameFacade game){
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compoundQuestion);

        JLabel label4 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Κατηγορία ερώτησης: " +  game.getCurrentQuestion(player1).getCategory());
        label5.setBorder(compound);

        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label5);
        panel1.add(label2);




        JButton answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        CountDown clock = new CountDown() {
            @Override
            public void onFinish() {
                answer1.setBackground(Color.RED);
                answer2.setBackground(Color.RED);
                answer3.setBackground(Color.RED);
                answer4.setBackground(Color.RED);

                switch(game.getCurrentQuestion(player1).getRightAnswerIndex()){
                    case 0:
                        answer1.setBackground(Color.GREEN);
                        break;
                    case 1:
                        answer2.setBackground(Color.GREEN);
                        break;
                    case 2:
                        answer3.setBackground(Color.GREEN);
                        break;
                    case 3:
                        answer4.setBackground(Color.GREEN);
                        break;
                }

                playSound(false);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if (status == FetchNextQuestionStatus.GAME_FINISHED) {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user1.getName() + "!", "Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);

                    try {
                        user1.newDataAlone(user1.getName(), (int) player1.getScore());
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }


                    player1 = new GamePlayer();
                    player2 = new GamePlayer();
                } else {
                    new CountDown(1) {
                        @Override
                        public void onFinish() {
                            frame.setVisible(false);
                            checktheTypeOfRound(game, true);
                        }
                    };

                }
            }};
        clock.setBorder(compound);

        panel1.add(clock);
        frame.add(panel1);


        JPanel panel2 = new JPanel();
        panel2.add(label3);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);
            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }



        frame.add(panel2);


        JPanel panel3 = new JPanel();


        panel3.add(answer1);
        panel3.add(answer2);
        panel3.add(answer3);
        panel3.add(answer4);
        frame.add(panel3);






        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);



        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answer1.setBackground(Color.RED);
                answer2.setBackground(Color.RED);
                answer3.setBackground(Color.RED);
                answer4.setBackground(Color.RED);
                clock.buttonClicked();
                int answerIndex = -1;
                if(e.getSource() == answer1){
                    answerIndex=0;
                }else if(e.getSource() == answer2){
                    answerIndex=1;
                } else if(e.getSource() == answer3){
                    answerIndex = 2;
                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }

                switch(game.getCurrentQuestion(player1).getRightAnswerIndex()){
                    case 0:
                        answer1.setBackground(Color.GREEN);
                        break;
                    case 1:
                        answer2.setBackground(Color.GREEN);
                        break;
                    case 2:
                        answer3.setBackground(Color.GREEN);
                        break;
                    case 3:
                        answer4.setBackground(Color.GREEN);
                        break;
                }
                playSound(answerIndex == game.getCurrentQuestion(player1).getRightAnswerIndex());

                int remaining = clock.getRemainingTime();
                game.answerQuestion(player1, answerIndex, remaining);

                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);

                    try {
                        user1.newDataAlone(user1.getName(), (int) player1.getScore());
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }else {
                    new CountDown(1) {
                        @Override
                        public void onFinish() {
                            frame.setVisible(false);
                            checktheTypeOfRound(game,true);
                        }
                    };

                }

            }

        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
    }


    /**
     * In this window we show the statistics of a player like the wins in the two players game and high score when he/she players alone
     * @param dim size of the statistics window
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public void statisticsGui(Dimension dim) throws IOException {
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.LEFT));
        window.setLayout(new GridLayout(5,1));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        JPanel panel1 = new JPanel();

        JLabel label = new JLabel("Βρές το όνομά σου:", SwingConstants.CENTER);
        String[] pl = user1.listOfThePlayers();
        JComboBox cb =new JComboBox(pl);
        JButton show = new JButton("Δείξε");
        panel1.add(label);
        panel1.add(cb);
        panel1.add(show);

        JLabel label1 = new JLabel("Παίκτης: ");
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Υψηλότερο σκόρ στο ατομικό παιχνίδι: ");
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Συνολικά ατομικά παιχνίδια: ");
        label3.setBorder(compound);

        JLabel label4 = new JLabel("Νίκες στο παιχνίδι 2 ατόμων: ");
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Συνολικά παιχνίδια 2 ατόμων: ");
        label5.setBorder(compound);



        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel2.add(label1);
        panel3.add(label2);
        panel3.add(label3);
        panel4.add(label4);
        panel4.add(label5);

        window.add(panel1);
        window.add(panel2);
        window.add(panel3);
        window.add(panel4);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                String textFieldValue = (String) cb.getItemAt(cb.getSelectedIndex());
                Player player = new Player(textFieldValue);
                try {
                    if(player.checkTheNames(textFieldValue)){
                        label1.setText("Παίκτης: "+player.getName());
                        label2.setText("Υψηλότερο σκόρ στο ατομικό παιχνίδι: "+player.getHighScore());
                        label3.setText("Συνολικά ατομικά παιχνίδια: "+player.getTotalGamesAlone());
                        label4.setText("Νίκες στο παιχνίδι 2 ατόμων: "+player.getWins()+"("+(player.getWins()*1.0/player.getTotalGAmesFriends())*100+"%"+")");
                        label5.setText("Συνολικά παιχνίδια 2 ατόμων: "+player.getTotalGAmesFriends());

                    } else{
                        JOptionPane.showMessageDialog(null, "Λάθος όνομα χρήστη");
                    }
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

    }


    /**
     * In this window it is decided whether the game will be played with two or one players.
     * And who will these players or player be
     * @param dim the size of the window
     */
    public void startPlaying(Dimension dim){
        window = new JFrame("Buzz Game");
        JPanel panel = new JPanel();
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(5,1));




        JRadioButton r1 = new JRadioButton("'Ενας παίκτης");
        JRadioButton r2 = new JRadioButton("Δύο παίκτες");
        JButton play = new JButton("Επόμενο");
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        panel.add(r1);
        panel.add(r2);
        panel.add(play);
        window.add(panel);



        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                if(r1.isSelected()){
                    GameFacade game = new GameFacadeDirector().buildSoloGame();
                    JPanel panel2 = new JPanel();
                    JLabel label = new JLabel("Όνομα Παίκτη:", SwingConstants.CENTER);
                    String[] pl;
                    try {
                        pl = user1.listOfThePlayers();
                        JButton run = new JButton("Ξεκίνα");
                        JComboBox cb = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);
                        panel2.add(run);
                        window.add(panel2);
                        window.setVisible(true);
                        run.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Clip clip;
                                try {
                                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                                    clip = AudioSystem.getClip();
                                    clip.open(audioIn);
                                    clip.start();
                                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                                    lineUnavailableException.printStackTrace();
                                }
                                window.setVisible(false);
                                String name = (String) cb.getItemAt(cb.getSelectedIndex());
                                user1.setName(name);
                                window.setVisible(false);
                                checktheTypeOfRound(game,true);
                            }
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if(r2.isSelected()){
                    GameFacade game = new GameFacadeDirector().buildTwoPlayersGame();
                    JPanel panel2 = new JPanel();
                    JPanel panel3 = new JPanel();
                    JPanel panel4 = new JPanel();
                    JLabel label = new JLabel("Όνομα 1ou Παίκτη:", SwingConstants.CENTER);
                    JLabel label2 = new JLabel("Όνομα 2ou Παίκτη:", SwingConstants.CENTER);
                    String[] pl;
                    try {
                        pl = user1.listOfThePlayers();
                        JButton run = new JButton("Ξεκινήστε");
                        JComboBox cb = new JComboBox(pl);
                        JComboBox cb2 = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);

                        panel3.add(label2);
                        panel3.add(cb2);
                        panel4.add(run);
                        window.add(panel2);
                        window.add(panel3);
                        window.add(panel4);
                        window.setVisible(true);
                        run.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Clip clip;
                                try {
                                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                                    clip = AudioSystem.getClip();
                                    clip.open(audioIn);
                                    clip.start();
                                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                                    lineUnavailableException.printStackTrace();
                                }
                                String name1 = (String) cb.getItemAt(cb.getSelectedIndex());
                                user1.setName(name1);
                                String name2 = (String) cb2.getItemAt(cb2.getSelectedIndex());
                                user2.setName(name2);
                                if(name1.equals(name2)){
                                    JOptionPane.showMessageDialog(null, "Οι δύο παίκτες πρέπει να είναι διαφορετικοί");
                                } else {
                                    window.setVisible(false);
                                    player1 = new GamePlayer();
                                    player2 = new GamePlayer();
                                    checktheTypeOfRound(game,false);
                                }
                            }
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * At this point of the application new player is register and he/she give us the nickname that he/she would like to have
     * @param dim the size of the window
     */
    public void afterCheckSignIn(Dimension dim){
        window = new JFrame("Buzz Game");
        window.setSize(dim);
        window.setMinimumSize(dim);

        JLabel label1 = new JLabel("Βάλε ένα όνομα:",JLabel.CENTER);

        JTextField text1 = new JTextField(15);


        JButton SUBMIT=new JButton("ΥΠΟΒΟΛΗ");

        JPanel panel=new JPanel(new GridLayout(1,1));
        panel.add(label1);
        panel.add(text1);
        panel.add(SUBMIT);

        window.add(panel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        SUBMIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clip clip;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("ButtonClick.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                String textFieldValue = text1.getText();
                Player player = new Player(textFieldValue);
                try {
                    if (player.newPlayer(player)) {
                        window.setVisible(false);

                    } else{
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Αυτό το όνομα χρήστη υπάρχει ήδη","Σφάλμα",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        });
    }

    /**
     * Sets up the screen of the RightQuestionRound for 1 player
     * @param dim size of the frame
     * @param game reference to our GameFacade
     */
    public void rightQuestionRound(Dimension dim, GameFacade game){
        JFrame frame = new JFrame("Buzz Game");

        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion,border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ: "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compoundQuestion);

        JLabel label4 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Κατηγορία ερώτησης: " +  game.getCurrentQuestion(player1).getCategory());
        label5.setBorder(compound);

        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label5);
        panel1.add(label2);
        frame.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(label3);


        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }




        frame.add(panel2);


        JPanel panel3 = new JPanel();
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        panel3.add(answer1);
        panel3.add(answer2);
        panel3.add(answer3);
        panel3.add(answer4);
        frame.add(panel3);







        ActionListener answerListener = new ActionListener() {

            public void actionPerformed(ActionEvent e){
                answer1.setBackground(Color.RED);
                answer2.setBackground(Color.RED);
                answer3.setBackground(Color.RED);
                answer4.setBackground(Color.RED);
                int answerIndex = -1;
                if(e.getSource() == answer1){
                    answerIndex=0;

                }else if(e.getSource() == answer2){
                    answerIndex=1;

                } else if(e.getSource() == answer3){
                    answerIndex = 2;

                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }
                switch(game.getCurrentQuestion(player1).getRightAnswerIndex()){
                    case 0:
                        answer1.setBackground(Color.GREEN);
                        break;
                    case 1:
                        answer2.setBackground(Color.GREEN);
                        break;
                    case 2:
                        answer3.setBackground(Color.GREEN);
                        break;
                    case 3:
                        answer4.setBackground(Color.GREEN);
                        break;
                }

                playSound(answerIndex == game.getCurrentQuestion(player1).getRightAnswerIndex());



                game.answerQuestion(player1, answerIndex);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);

                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    new CountDown(1) {
                        @Override
                        public void onFinish() {
                            frame.setVisible(false);
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);

                            try {
                                user1.newDataAlone(user1.getName(), (int)player1.getScore());
                                player1 = new GamePlayer();
                                player2 = new GamePlayer();
                            } catch (IOException | ClassNotFoundException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    };
                }else {
                    new CountDown(1) {
                        @Override
                        public void onFinish() {
                            frame.setVisible(false);
                            checktheTypeOfRound(game, true);
                        }
                    };
                }
            }
        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }




    /**
     * Sets up the screen of the rightQuestionForTwoPlayers for 2 player
     * @param dim size of the frame
     * @param game reference to our GameFacade
     */
    public void rightQuestionForTwoPlayers(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel centralPanel = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ "+user1.getName()+": "+(int)player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ "+user2.getName()+": "+(int)player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);
        centralPanel.add(question);


        JPanel panel2 = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);


        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.cyan);



        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        answer2.setOpaque(true);
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        answer3.setOpaque(true);
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        answer4.setOpaque(true);
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
        answer5.setOpaque(true);
        int rightAnswerIndex = game.getCurrentQuestion(player1).getRightAnswerIndex();

        ArrayList<Integer> playerTwoOrder = new ArrayList<>();
        playerTwoOrder.add(0);
        playerTwoOrder.add(1);
        playerTwoOrder.add(2);
        playerTwoOrder.add(3);
        Collections.shuffle(playerTwoOrder);

        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(0)));
        answer6.setOpaque(true);
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(1)));
        answer7.setOpaque(true);
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(2)));
        answer8.setOpaque(true);
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(3)));
        answer1.setOpaque(true);


        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex);
                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, playerTwoOrder.get(answerIndex));
                    }
                }

                if(pressed1 && pressed2){
                    answer2.setBackground(Color.RED);
                    answer3.setBackground(Color.RED);
                    answer4.setBackground(Color.RED);
                    answer5.setBackground(Color.RED);

                    answer6.setBackground(Color.RED);
                    answer7.setBackground(Color.RED);
                    answer8.setBackground(Color.RED);
                    answer1.setBackground(Color.RED);

                    switch(rightAnswerIndex){
                        case 0:
                            answer2.setBackground(Color.GREEN);
                            break;
                        case 1:
                            answer3.setBackground(Color.GREEN);
                            break;
                        case 2:
                            answer4.setBackground(Color.GREEN);
                            break;
                        case 3:
                            answer5.setBackground(Color.GREEN);
                            break;
                    }

                    if(rightAnswerIndex == playerTwoOrder.get(0)) {
                        answer6.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(1)) {
                        answer7.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(2)) {
                        answer8.setBackground(Color.GREEN);
                    } else {
                        answer1.setBackground(Color.GREEN);
                    }

                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        new CountDown(1) {
                            @Override
                            public void onFinish() {
                                frame.setVisible(false);
                                if(player1.getScore()>player2.getScore()){
                                    JOptionPane.showMessageDialog(new JFrame(),
                                            "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    try {
                                        user1.newDataWithTwoPlayers(user1.getName(), true);
                                        user2.newDataWithTwoPlayers(user2.getName(), false);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }

                                }else {
                                    JOptionPane.showMessageDialog(new JFrame(),
                                            "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    try {
                                        user1.newDataWithTwoPlayers(user1.getName(), false);
                                        user2.newDataWithTwoPlayers(user2.getName(), true);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                }
                                player1 = new GamePlayer();
                                player2 = new GamePlayer();
                            }
                        };

                    }else {
                        new CountDown(1) {
                            @Override
                            public void onFinish() {
                                frame.setVisible(false);
                                checktheTypeOfRound(game, false);
                            }
                        };
                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);


        answer2.addKeyListener(myListener);
        answer3.addKeyListener(myListener);
        answer4.addKeyListener(myListener);
        answer5.addKeyListener(myListener);

        answer6.addKeyListener(myListener);
        answer7.addKeyListener(myListener);
        answer8.addKeyListener(myListener);
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(frame.getBounds().width / 2);





        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        jsp1.setDividerLocation((frame.getBounds().width / 2)-10);



    }

    /**
     * We convert a number between 1 and 5 to the name of the round.
     * @param roundId the number id that corresponds to the name of the Round.
     * @return a String of the name of the Round
     */
    public String getRoundName(int roundId){
        switch (roundId){
            case 1:
                return "Σωστή Απάντηση";
            case 2:
                return "Σταμάτησε το χρονόμετρο";
            case 3:
                return "Ποντάρισμα";
            case 4:
                return "Γρήγορη Απάντηση";
            case 5:
                return "Θερμόμετρο";
        }
        return null;
    }


    /**
     * This window shows the betting choices for 1 player BettingRound
     * @param dim size of the window
     * @param game reference to our GameFacade
     */
    public void bettingChoises(Dimension dim, GameFacade game){
        window = new JFrame("Buzz Game");
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(5,1));



        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("Η επόμενη ερώτηση ανήκει στην κατηγορία "+game.getCurrentQuestion(player1).getCategory());
        panel1.add(label);


        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Πόσους πόντους θέλεις να ποντάρεις? ");
        panel2.add(label2);




        JPanel panel3 = new JPanel();
        JButton bet1 = new JButton("250");
        JButton bet2 = new JButton("500");
        JButton bet3 = new JButton("750");
        JButton bet4 = new JButton("1000");
        panel3.add(bet1);
        panel3.add(bet2);
        panel3.add(bet3);
        panel3.add(bet4);

        window.add(panel1);
        window.add(panel2);
        window.add(panel3);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        bet1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 250;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
        bet2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 500;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
        bet3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 750;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
        bet4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                betPlayer1 = 1000;
                Dimension dimension = new Dimension(800,500);
                bettingRound(dimension, game);
            }
        });
    }





    /**
     * This window shows the betting choices for 2 player BettingRound
     * @param dim size of the window
     * @param game reference to our GameFacade
     */
    public void bettingChoissesForTwoPlayers(Dimension dim, GameFacade game){

        window = new JFrame("Buzz Game");
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(5,1));



        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("Η επόμενη ερώτηση ανήκει στην κατηγορία "+game.getCurrentQuestion(player1).getCategory());
        panel1.add(label);


        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Πόσους πόντους θέλεις να ποντάρεις "+user1.getName()+"?");
        panel2.add(label2);

        JLabel label3 = new JLabel("Πόσους πόντους θέλεις να ποντάρεις "+user2.getName()+"?");



        JPanel panel3 = new JPanel();
        JButton bet1 = new JButton("250");

        JButton bet2 = new JButton("500");
        JButton bet3 = new JButton("750");
        JButton bet4 = new JButton("1000");
        JButton bet5 = new JButton("250");
        JButton bet6 = new JButton("500");
        JButton bet7 = new JButton("750");
        JButton bet8 = new JButton("1000");
        panel2.add(bet1);
        panel2.add(bet2);
        panel2.add(bet3);
        panel2.add(bet4);

        panel3.add(label3);
        panel3.add(bet5);
        panel3.add(bet6);
        panel3.add(bet7);
        panel3.add(bet8);



        window.add(panel1);
        window.add(panel2);
        window.add(panel3);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        KeyListener myListener = new KeyListener() {
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            betPlayer1 =250;
                            break;
                        case 'w':
                            pressed1 = true;
                            betPlayer1 = 500;
                            break;
                        case 'e':
                            pressed1 = true;
                            betPlayer1 = 750;
                            break;
                        case 'r':
                            pressed1 = true;
                            betPlayer1 = 1000;
                            break;
                    }


                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            betPlayer2 = 250;
                            break;
                        case 'i':
                            pressed2 = true;
                            betPlayer2 = 500;
                            break;
                        case 'o':
                            pressed2 = true;
                            betPlayer2 = 750;
                            break;
                        case 'p':
                            pressed2 = true;
                            betPlayer2 = 1000;
                            break;
                    }
                }

                if(pressed1 && pressed2){
                    window.setVisible(false);
                    Dimension dimension = new Dimension(800, 500);
                    betting2Round(dimension, game);
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        bet1.addKeyListener(myListener);
        bet2.addKeyListener(myListener);
        bet3.addKeyListener(myListener);
        bet4.addKeyListener(myListener);
        bet5.addKeyListener(myListener);
        bet6.addKeyListener(myListener);
        bet7.addKeyListener(myListener);
        bet8.addKeyListener(myListener);

    }




    /**
     * Sets up the screen of the BettingRound for 2 players
     * @param dimension size of the frame
     * @param game reference to our GameFacade
     */
    private void betting2Round(Dimension dimension, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        LineBorder lineQuestion = new LineBorder(Color.red,2,true);
        CompoundBorder compound = new CompoundBorder(line, border);
        CompoundBorder compoundQuestion = new CompoundBorder(lineQuestion, border);

        JPanel centralPanel = new JPanel();
        JLabel label1 = new JLabel("Γύρος: "+game.getCurrentRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ "+user1.getName()+": "+(int)player1.getScore());
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ "+user2.getName()+": "+(int)player2.getScore());
        label4.setBorder(compound);

        JLabel label5 = new JLabel("Τύπος Γύρου: "+getRoundName(game.getCurrentRound().getRoundName()));
        label5.setBorder(compound);


        JLabel question = new JLabel("Ερώτηση: "+game.getCurrentQuestion(player1).getQuestionText());
        question.setBorder(compoundQuestion);


        centralPanel.add(label5);
        centralPanel.add(label1);
        centralPanel.add(label2);
        centralPanel.add(label4);


        JPanel panel2 = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);


        panel2.add(question);
        if(game.getCurrentQuestion(player1) instanceof QuestionWithImage){
            String imgName =  ((QuestionWithImage) game.getCurrentQuestion(player1)).getImagePath();
            URL imageURL = getClass().getResource("Images/" +imgName);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);

            }

            JLabel labelWithIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            panel2.add(labelWithIcon);
        }


        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.cyan);

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
        JButton answer5 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        int rightAnswerIndex = game.getCurrentQuestion(player1).getRightAnswerIndex();

        ArrayList<Integer> playerTwoOrder = new ArrayList<>();
        playerTwoOrder.add(0);
        playerTwoOrder.add(1);
        playerTwoOrder.add(2);
        playerTwoOrder.add(3);
        Collections.shuffle(playerTwoOrder);

        JButton answer6 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(0)));
        JButton answer7 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(1)));
        JButton answer8 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(2)));
        answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(playerTwoOrder.get(3)));






        KeyListener myListener = new KeyListener() {
            int answerIndex = 0;
            boolean pressed1 = false;
            boolean pressed2 = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressed1) {
                    switch (e.getKeyChar()) {
                        case 'q':
                            pressed1 = true;
                            answerIndex = 0;
                            break;
                        case 'w':
                            pressed1 = true;
                            answerIndex = 1;
                            break;
                        case 'e':
                            pressed1 = true;
                            answerIndex = 2;
                            break;
                        case 'r':
                            pressed1 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed1){
                        game.answerQuestion(player1, answerIndex, betPlayer1);

                    }

                }
                if(!pressed2) {
                    switch (e.getKeyChar()) {
                        case 'u':
                            pressed2 = true;
                            answerIndex = 0;
                            break;
                        case 'i':
                            pressed2 = true;
                            answerIndex = 1;
                            break;
                        case 'o':
                            pressed2 = true;
                            answerIndex = 2;
                            break;
                        case 'p':
                            pressed2 = true;
                            answerIndex = 3;
                            break;
                    }
                    if(pressed2) {
                        game.answerQuestion(player2, playerTwoOrder.get(answerIndex), betPlayer2);
                    }
                }

                if(pressed1 && pressed2){
                    answer2.setBackground(Color.RED);
                    answer3.setBackground(Color.RED);
                    answer4.setBackground(Color.RED);
                    answer5.setBackground(Color.RED);

                    answer6.setBackground(Color.RED);
                    answer7.setBackground(Color.RED);
                    answer8.setBackground(Color.RED);
                    answer1.setBackground(Color.RED);

                    switch(rightAnswerIndex){
                        case 0:
                            answer2.setBackground(Color.GREEN);
                            break;
                        case 1:
                            answer3.setBackground(Color.GREEN);
                            break;
                        case 2:
                            answer4.setBackground(Color.GREEN);
                            break;
                        case 3:
                            answer5.setBackground(Color.GREEN);
                            break;
                    }

                    if(rightAnswerIndex == playerTwoOrder.get(0)) {
                        answer6.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(1)) {
                        answer7.setBackground(Color.GREEN);
                    } else if(rightAnswerIndex == playerTwoOrder.get(2)) {
                        answer8.setBackground(Color.GREEN);
                    } else {
                        answer1.setBackground(Color.GREEN);
                    }

                    FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                    if(status == FetchNextQuestionStatus.GAME_FINISHED){
                        if(player1.getScore()>player2.getScore()){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ "+user1.getName()+": "+player1.getScore()+"\n"+"Σκορ "+user2.getName()+": "+player2.getScore()+"\n"+"Νικητής ο παίκτης "+user1.getName()+"!","Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), true);
                                user2.newDataWithTwoPlayers(user2.getName(), false);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Σκόρ " + user1.getName() + ": " + player1.getScore() + "\n" + "Σκορ " + user2.getName() + ": " + player2.getScore() + "\n" + "Νικητής ο παίκτης " + user2.getName() + "!", "Μπράβο!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            try {
                                user1.newDataWithTwoPlayers(user1.getName(), false);
                                user2.newDataWithTwoPlayers(user2.getName(), true);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        player1 = new GamePlayer();
                        player2 = new GamePlayer();
                    }else {
                        new CountDown(1) {
                            @Override
                            public void onFinish() {
                                frame.setVisible(false);
                                checktheTypeOfRound(game, false);
                            }
                        };

                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(myListener);


        answer2.addKeyListener(myListener);
        answer3.addKeyListener(myListener);
        answer4.addKeyListener(myListener);
        answer5.addKeyListener(myListener);




        answer6.addKeyListener(myListener);
        answer7.addKeyListener(myListener);
        answer8.addKeyListener(myListener);
        answer1.addKeyListener(myListener);


        panel4.add(answer2);
        panel4.add(answer3);
        panel4.add(answer4);
        panel4.add(answer5);
        panel5.add(answer6);
        panel5.add(answer7);
        panel5.add(answer8);
        panel5.add(answer1);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);





        frame.add(centralPanel);
        frame.add(panel2);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        jsp1.setDividerLocation((frame.getBounds().width / 2)-10);
    }

    /**
     * In this method we start a wav file with sound when a player answers a question to understand if he/she replies correct or wrong
     * @param isCorrect this variable is true when the player answers correct and false when answers wrong.
     */
    private void playSound(boolean isCorrect){
        String audioFilename = "WrongAnswer.wav";
        if(isCorrect) {
            audioFilename = "CorrectAnswer.wav";
        }
        Clip clip;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource(audioFilename));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        }
    }
}