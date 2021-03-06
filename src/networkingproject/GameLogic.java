/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import java.awt.*;
import java.awt.BasicStroke;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class GameLogic extends JPanel implements MouseListener, MouseMotionListener {

    String currentPositionOpponent = "  0          0.0        0";
    String currentPositionSelf = "  0          0.0        0";
    String initialCompare = "  0          0.0        0";
    public static String yourTarget = "1000";

    public boolean willSend;

    //Shape circle=new Ellipse2D.Float(10,20,600,600);
    //this is for scoring
    private int score = 0;
    private int over = 0;
    private int wicket = 0;

    //these strings ar taken to converte the score int to string
    //Score controlling properties
    private String msg = "";
    static int x = 0, y = 0;
    private int scoreDiterminer;
    private int ballCount = 0;
    private int target;
    private int fOver;

    //This variables are used to count mouse click
    private int flag = 1;
    private int clickCount = 0;
    private Graphics2D g;

    //it is a constructor for defining score
    public GameLogic(int sD, int t, int f) {

        scoreDiterminer = sD;
        target = t;
        fOver = f;
        willSend = true;
    }

    //this is to draw circle with partitioning in 8 part
    @Override
    public void paintComponent(Graphics g2) {

        g = (Graphics2D) g2;
        //basic window properties
        this.setBackground(Color.gray);
        super.paintComponent(g);
        this.addMouseListener(this);

        g.fillOval(x + 2, y + 2, 5, 5);

        //circle properties
        g.setColor(Color.lightGray);

        g.fillOval(10, 20, 600, 600);
        g.setStroke(new BasicStroke(2.0F));
        g.setColor(Color.black);
        g.drawLine(310, 20, 310, 620);
        g.drawLine(10, 320, 610, 320);
        g.drawLine(98, 108, 522, 532);
        g.drawLine(98, 532, 522, 108);

        if (x > 60 && x < 560 && y > 70 && y < 570) {
            g.setFont(
                    new Font("Serif", Font.PLAIN, 40));
            g.setColor(Color.BLACK);
            g.fillOval(x - 4, y - 20, 3, 3);
            g.setColor(Color.black);
            g.drawString(msg, x, y);

        }
        //This is to convet the scors integer property to string
        String sScore = "";
        String sOver = "";
        String sWicket = "";
        String sBall = "";
        String sTarget = "";
        String sfOver = "";
        String stringTarget = "";

//        if (score >= target) {
//            FinalResult r = new FinalResult(1);
//        } else if (wicket >= 10 || over >= fOver) {
//            FinalResult r = new FinalResult(0);
//        }
        sScore = Integer.toString(score);
        sOver = Integer.toString(over);
        sWicket = Integer.toString(wicket);
        sBall = Integer.toString(ballCount);
        sTarget = Integer.toString(target);
        sfOver = Integer.toString(fOver);
        //Here the currentscore is shown

        currentPositionSelf = "  " + sScore + "          " + sOver + "." + sBall + "        " + sWicket;
        System.out.println("currentPositionSelf=" + currentPositionSelf);
//        System.out.println("here1");
        System.out.println("initialCompare=" + initialCompare);
//        System.out.println("here2");
        if (!currentPositionSelf.equals(initialCompare) && willSend == true) {
            System.out.println("here3");
//            System.out.println(" check=" + !currentPositionSelf.equals(initialCompare));
            String sendTo2from1 = "1" + currentPositionSelf;
            System.out.println("sendto2from1=" + sendTo2from1);
            OverSelectionFrame.sendMessage(sendTo2from1);
        }

        g.setFont(
                new Font("Serif", Font.BOLD, 30));
        g.drawString(
                "SCORE BOARD", 780, 120);

        //Another font is chosen to show the score
        g.setFont(
                new Font("Serif", Font.PLAIN, 20));
        g.drawString(
                "Player 1", 860, 156);
        g.drawString("________", 852, 160);
        g.drawString(
                "Run       Over     Wicket", 800, 185);
        g.drawString(currentPositionSelf,
                800, 210);

        /*stringTarget = "Target: " + sTarget + " (" + sfOver + " over)";
        g.drawString(stringTarget, 800, 230);*/
        //The second player score
        g.drawString(
                "Player 2", 860, 256);
        g.drawString("________", 852, 260);
        g.drawString(
                "Run       Over     Wicket", 800, 285);
        g.drawString(currentPositionOpponent,
                800, 310);

        if (wicket >= 10 || over >= fOver) {
            System.out.println("sScore=" + sScore);
            willSend = false;
            GameLogic2.yourTarget = sScore;
        }

    }

    //This method will be called by single click of mouse
    public void clickOperation() {

//        System.out.println(" You clicked :");
        //random number is used to calculate the score randomly
        Random r = new Random();
        int randomNumber;
        randomNumber = r.nextInt(13);
        scoreDiterminer = 0 + randomNumber;

        if (x > 60 && x < 560 && y > 70 && y < 570) {
            if (scoreDiterminer == 0 || scoreDiterminer == 8 || scoreDiterminer == 10) {
                msg = "It is a dot ball";
                ballCount++;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 1 || scoreDiterminer == 5 || scoreDiterminer == 7) {
                msg = "Single";
                ballCount++;
                ++score;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 2) {
                msg = "Double";
                ballCount++;
                score = score + 2;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 3) {
                msg = "3 Run";
                ballCount++;
                score = score + 3;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 4 || scoreDiterminer == 9) {
                msg = "Four";
                ballCount++;
                score = score + 4;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 6) {
                msg = "Six";
                ballCount++;
                score = 6 + score;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 11) {
                msg = "Bold";
                ballCount++;
                wicket++;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 12) {
                msg = "Caught out";
                ballCount++;
                wicket++;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else if (scoreDiterminer == 13) {
                msg = "Lbw";
                ballCount++;
                wicket++;
                if (ballCount == 6) {
                    ballCount = 0;
                    over = over + 1;
                }
            } else {

            }

            repaint();
        }
    }

    public void callRepaint() {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        clickCount++;

        if (flag < clickCount) {
            flag = clickCount;
            clickCount = 0;
            if (willSend == true) {
                clickOperation();
            }
        }

    }

    //these are unused
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
