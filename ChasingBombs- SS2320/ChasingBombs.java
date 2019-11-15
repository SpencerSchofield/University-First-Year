import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * ChasingBombs is a game where the player needs to the tile which the bomb is hidden under.
 * There are three difficulties to play on: Easy, Intermediate and Difficult.
 *
 * @author (Spencer Schofield ss2320)
 * @version (19/3/2019)
 */
public class ChasingBombs extends JFrame
{
    private JFrame frame = new JFrame("Chasing Bombs");
    //Makes the three panels where the screens are shown
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();

    //Makes the buttons for the game
    private JButton button1 = new JButton("Play Game");
    private JButton button2 = new JButton("Exit");
    private JButton button3 = new JButton("Easy");
    private JButton button4 = new JButton("Intermediate");
    private JButton button5 = new JButton("Difficult");

    private JLabel label1 = new JLabel();//Makes a label to display the score

    private int rows = 2;
    private int cols = 5;
    private int gap = 4;
    private int NUM = rows * cols;
    private int x;
    private int y;
    private JPanel [] tile = new JPanel[NUM];
    private Color clickedTile = Color.ORANGE;

    private int difficulty = 5;
    private int moves = 0;
    private int score = 0;
    private int bomb = (int)(Math.random() * 9 + 1);//Sets the bomb 
    private boolean isActive = false;//game set to inactive at first
    /**
     * The Constructor for ChasingBombs
     * Sets the frame's size, visiability, layout and runs the makeFrame method.
     */
    public ChasingBombs(){
        setSize(1200,500);
        setVisible(true);
        setLayout(new GridLayout());
        makeFrame();
    }

    /**
     * MakeFrame method makes and displays the game.
     */
    public void makeFrame(){
        //add the panels to the frame
        add(panel1);
        add(panel2);
        add(panel3);
        panel1.setLayout(new GridLayout(rows,cols, gap, gap));
        
        //setup panel2 
        BoxLayout b1 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(b1);
        panel2.setBackground(Color.GREEN);
        panel2.add(button1);
        panel2.add(Box.createVerticalStrut(20));
        panel2.add(button2);
        panel2.add(Box.createVerticalStrut(20));
        panel2.add(label1);
        
        //setup panel3
        BoxLayout b2 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(b2);
        panel3.setBackground(Color.BLUE);
        panel3.add(button3);
        panel3.add(Box.createVerticalStrut(20));
        panel3.add(button4);
        panel3.add(Box.createVerticalStrut(20));
        panel3.add(button5);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Make the game tiles and setup panel 1
        for(int x = 0; x < NUM; x++) {
            tile[x] = new JPanel();
            panel1.add(tile[x]);
            tile[x].setBackground(Color.RED);
            int y = x;

            tile[x].addMouseListener(new MouseAdapter(){//function for when a tile is pressed
                    public void mousePressed(MouseEvent e)
                    {
                        if (!isActive){
                            return;//returns nothing if game is inactive
                        }
                        
                        else if (tile[y].getBackground() != clickedTile && y != bomb){
                            tile[y].setBackground(clickedTile);
                            score += 50;//add to the score
                            label1.setText("Score : " + score);//update scoreboard
                            moves ++;
                            if (moves == difficulty){
                            label1.setText("Congratulations! You've Won! Your Final Score : " + score);
                        }
                        }
                        else if (y == bomb){
                            label1.setText("KABOOM! The Bomb Exploded! Your Final Score : " + score);
                            isActive = false;
                        }
                    }
                });
        }

        button1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    gameReset();
                }
            });
        button2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    dispose();
                }
            });
        button3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    difficulty = 5;
                    gameReset();
                }
            });
        button4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    difficulty = 7;
                    gameReset();
                }
            });
        button5.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    difficulty = 9;
                    gameReset();
                }
            });
    }

    /**
     * gameReset method is called when the game finishes, or the player resets the game 
     * or when the player changes the difficulty.
     */
    public void gameReset(){
        for (int x = 0; x < NUM; x++){
            tile[x].setBackground(Color.RED);
        }
        moves = 0;
        score = 0;
        label1.setText("Score : " + score);
        bomb = (int)(Math.random() * 9 + 1); 
        label1.setText("Score : " + score);
        isActive = true;
    }

}
