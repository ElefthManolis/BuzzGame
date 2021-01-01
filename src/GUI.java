import GameLogic.*;
import GameLogic.Question;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GUI implements KeyListener{
    private JFrame window;
    private JButton playButton, signInButton, statisticsButton;
    private Player user = new Player("user");
    private JButton answer1;
    public static boolean keyHeld_Q = false, keyHeld_W = false, keyHeld_E = false, keyHeld_R = false, keyHeld_U = false, keyHeld_I = false, keyHeld_O = false, keyHeld_P = false;
    private GamePlayer player1 = new GamePlayer();
    private GamePlayer player2 = new GamePlayer();



    public GUI(Dimension dim){  // Εδώ πρέπει να προσθέσω μια δευτερη παράμετρο που θα φορτώσει όλα το παιχνίδι
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.CENTER));
        window.setLayout(new GridLayout(4,1));


        playButton = new JButton("Παίξε");
        signInButton = new JButton("Εγραφή νέου χρήστη");
        statisticsButton = new JButton("Στατιστκά");

        JLabel label = new JLabel("Μενού", SwingConstants.CENTER);
        window.add(label);
        window.add(playButton);
        window.add(signInButton);
        window.add(statisticsButton);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(200, 150);
                startPlaying(dim);
            }
        });

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(100, 50);
                afterCheckSignIn(dim);
            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Dimension dim = new Dimension(600, 450);
                try {
                    statisticsGui(dim);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void statisticsGui(Dimension dim) throws IOException {
        window = new JFrame("Buzz Game");
        window.setResizable(true);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new FlowLayout(FlowLayout.LEFT));
        window.setLayout(new GridLayout(5,1));
        JPanel panel1 = new JPanel();

        JLabel label = new JLabel("Find your username:", SwingConstants.CENTER);
        String[] pl = user.listOfThePlayers();
        JComboBox cb =new JComboBox(pl);
        JButton show = new JButton("Show");
        panel1.add(label);
        panel1.add(cb);
        panel1.add(show);

        JLabel label1 = new JLabel("Παίκτης: ");

        JLabel label2 = new JLabel("Υψηλότερο σκόρ στο ατομικό παιχνίδι: ");

        JLabel label3 = new JLabel("Συνολικά ατομικά παιχνίδια: ");

        JLabel label4 = new JLabel("Νίκες στο παιχνίδι 2 ατόμων: ");

        JLabel label5 = new JLabel("Συνολικά παιχνίδια 2 ατόμων: ");
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
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }



    public void startPlaying(Dimension dim){
        window = new JFrame("Buzz Game");
        JPanel panel = new JPanel();
        window.setResizable(false);
        window.setSize(dim);
        window.setMinimumSize(dim);
        window.setLayout(new GridLayout(10,1));


        GameFacade game = new GameFacadeDirector().buildGame();

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
                if(r1.isSelected()){
                    JPanel panel2 = new JPanel();
                    JLabel label = new JLabel("Όνομα Παίκτη:", SwingConstants.CENTER);
                    String[] pl = new String[0];
                    try {
                        pl = user.listOfThePlayers();
                        JButton run = new JButton("Ξεκίνα");
                        JComboBox cb = new JComboBox(pl);
                        panel2.add(label);
                        panel2.add(cb);
                        panel2.add(run);
                        window.add(panel2);
                        window.setVisible(true);
                        run.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                window.setVisible(false);
                                String name = (String) cb.getItemAt(cb.getSelectedIndex());
                                Dimension dim = new Dimension(800, 500);
                                initializeAloneGame(dim,game);
                            }
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JComboBox cb =new JComboBox(pl);
                } else if(r2.isSelected()){
                    JPanel panel2 = new JPanel();
                    JPanel panel3 = new JPanel();
                    JPanel panel4 = new JPanel();
                    JLabel label = new JLabel("Όνομα 1ou Παίκτη:", SwingConstants.CENTER);
                    JLabel label2 = new JLabel("Όνομα 2ou Παίκτη:", SwingConstants.CENTER);
                    String[] pl = new String[0];
                    try {
                        pl = user.listOfThePlayers();
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
                                String name1 = (String) cb.getItemAt(cb.getSelectedIndex());
                                String name2 = (String) cb2.getItemAt(cb2.getSelectedIndex());
                                if(name1.equals(name2)){
                                    JOptionPane.showMessageDialog(null, "Οι δύο παίκτες πρέπει να είναι διαφορετικοί");
                                } else {
                                    Dimension dim = new Dimension(800, 500);
                                    initialize2Game(dim,game);
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

    public void afterCheckSignIn(Dimension dim){
        window = new JFrame("Buzz Game");
        window.setSize(dim);
        window.setMinimumSize(dim);

        JLabel label1 = new JLabel("Enter Username:",JLabel.CENTER);

        JTextField text1 = new JTextField(15);


        JButton SUBMIT=new JButton("SUBMIT");

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


    public void initializeAloneGame(Dimension dim,GameFacade game){

        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(4,3));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος "+game.getCurrRoundIndex());
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ "+player1.getScore());
        label2.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση "+game.getCurrentQuestion(player1).getQuestionText());
        label3.setBorder(compound);

        JLabel label4 = new JLabel("Τύπος Γύρου:"+game.getCurrentRound().getRoundName());

        panel1.add(label4);
        panel1.add(label1);
        panel1.add(label3);
        panel1.add(label2);
        frame.add(panel1);



        JPanel panel2 = new JPanel();
        JButton answer1 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(0));

        JButton answer2 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(1));

        JButton answer3 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(2));

        JButton answer4 = new JButton(game.getCurrentQuestion(player1).getAnswerAtIndex(3));

        panel2.add(answer1);
        panel2.add(answer2);
        panel2.add(answer3);
        panel2.add(answer4);
        frame.add(panel2);






        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        ActionListener answerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int answerIndex = 0;
                if(e.getSource() == answer1){
                    answerIndex=0;
                }else if(e.getSource() == answer2){
                    answerIndex=1;
                } else if(e.getSource() == answer3){
                    answerIndex = 2;
                } else if(e.getSource() == answer4){
                    answerIndex = 3;
                }
                game.answerQuestion(player1, answerIndex);
                FetchNextQuestionStatus status = game.fetchNextQuestion(player1);
                if(status == FetchNextQuestionStatus.GAME_FINISHED){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Τελείωσες το παιχνίδι με σκορ "+player1.getScore(),"Μπράβο!",
                            JOptionPane.INFORMATION_MESSAGE);
                }else {
                    label4.setText("Τύπος Γύρου:" + game.getCurrentRound().getRoundName());
                    label1.setText("Γύρος " + game.getCurrRoundIndex());
                    label2.setText("Σκορ " + player1.getScore());
                    label3.setText("Ερώτηση " + game.getCurrentQuestion(player1).getQuestionText());
                    answer1.setText(game.getCurrentQuestion(player1).getAnswerAtIndex(0));
                    answer2.setText(game.getCurrentQuestion(player1).getAnswerAtIndex(1));
                    answer3.setText(game.getCurrentQuestion(player1).getAnswerAtIndex(2));
                    answer4.setText(game.getCurrentQuestion(player1).getAnswerAtIndex(3));
                }

            }

        };

        answer1.addActionListener(answerListener);
        answer2.addActionListener(answerListener);
        answer3.addActionListener(answerListener);
        answer4.addActionListener(answerListener);


    }

    public void initialize2Game(Dimension dim, GameFacade game) {
        JFrame frame = new JFrame("Buzz Game");
        frame.setResizable(true);
        frame.setSize(dim);
        frame.setMinimumSize(dim);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setLayout(new GridLayout(3,2));
        EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
        LineBorder line = new LineBorder(Color.blue, 2, true);
        CompoundBorder compound = new CompoundBorder(line, border);

        JPanel centralPanel = new JPanel();



        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Γύρος ");
        label1.setBorder(compound);

        JLabel label2 = new JLabel("Σκορ ");
        label2.setBorder(compound);

        JLabel label4 = new JLabel("Σκορ ");
        label4.setBorder(compound);

        JLabel label3 = new JLabel("Ερώτηση ");
        label3.setBorder(compound);

        centralPanel.add(label1);
        centralPanel.add(label3);
        centralPanel.add(label4);
        centralPanel.add(label2);


        JPanel panel2 = new JPanel();
        JButton nextQuestion = new JButton("Επόμενη Ερώτηση");
        centralPanel.add(nextQuestion);


        panel1.setBackground(Color.orange);

        panel2.setBackground(Color.green);
        JSplitPane jsp = new JSplitPane();
        jsp.setLayout(new GridLayout(2,2));
        JLabel question1 = new JLabel("Ποιος έκανε αυτο και με τι σκοπό?");
        panel1.add(question1);
        JLabel question2 = new JLabel("Poios ekane auto kai me ti skopo?");
        panel2.add(question2);

        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel1, panel2);
        jsp.setDividerLocation(dim.width / 2);

        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel4.setBackground(Color.orange);
        panel5.setBackground(Color.green);
        answer1 = new JButton("1");
        answer1.addKeyListener(this);
        JButton answer2 = new JButton("fsdafsadfasdfaf");
        answer2.addKeyListener(this);
        JButton answer3 = new JButton("dfsdafasfasdfasf");
        answer3.addKeyListener(this);
        JButton answer4 = new JButton("4");
        answer4.addKeyListener(this);
        JButton answer5 = new JButton("asdfasdfasdfasdf");
        answer5.addKeyListener(this);
        JButton answer6 = new JButton("2");
        answer6.addKeyListener(this);
        JButton answer7 = new JButton("asdfasdfasdfasf");
        answer7.addKeyListener(this);
        JButton answer8 = new JButton("asdfasdfasdf");
        answer8.addKeyListener(this);

        panel4.add(answer1);
        panel4.add(answer2);
        panel4.add(answer5);
        panel4.add(answer6);
        panel5.add(answer3);
        panel5.add(answer4);
        panel5.add(answer7);
        panel5.add(answer8);

        JSplitPane jsp1 = new JSplitPane();
        jsp1.setLayout(new GridLayout(2,2));
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel4, panel5);
        jsp1.setDividerLocation(dim.width / 2);




        frame.add(centralPanel);
        frame.add(jsp);
        frame.add(jsp1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        nextQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                label1.setText("Γύρος ");
                label2.setText("Σκορ "+": ");
                label3.setText("Ερώτηση ");
                label4.setText("Σκορ ");
            }
        });
        if(keyHeld_Q){
            label1.setText("Pressed b");
        }


    }

    public void BetRound(Dimension dim){

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if(keycode == 66){
            keyHeld_Q = true;
            System.out.println("You Typed b");
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if(keycode == KeyEvent.VK_Q){
            keyHeld_Q = true;
            System.out.println("pressed q");

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keycode = keyEvent.getKeyCode();
        if(keycode == KeyEvent.VK_Q){
            keyHeld_Q = true;
            System.out.println("pressed q");

        }
    }



}