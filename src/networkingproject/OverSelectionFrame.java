/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//it is the second window 
//here the user will choose the length of the match
public class OverSelectionFrame extends JFrame {

    public static GameLogic gameLogic1;
    public static GameLogic2 gameLogic2;
    public static String playerNames[] = new String[2];
    public static boolean continueServer = false;
    public static boolean continueClient = false;
    private static Socket socket;
    private static BufferedReader input;
    public static PrintWriter output;

    private JLabel lengthLabel;
    private JLabel l2;

    private JButton fiveOverButton;
    private JButton tenOverButton;
    private JButton fiftyOverButton;

    public OverSelectionFrame(int playerId) {
        if (playerId == 1) {
            setTitle("Player1");
        } else {
            setTitle("Player2");
        }
        lengthLabel = new JLabel("Select Match Length");
//        l2 = new JLabel();

        fiveOverButton = new JButton("5 over ");
        tenOverButton = new JButton("10 over");
        fiftyOverButton = new JButton("15 over");

        setLayout(new GridBagLayout());

        setLocation(500, 100);
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(Color.gray);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.weighty = 20;
        add(lengthLabel, gridBagConstraints);

        gridBagConstraints.weighty = 10;
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 50;
        add(fiveOverButton, gridBagConstraints);

//        gridBagConstraints.gridx = 10;
//        gridBagConstraints.gridy = 55;
//        add(tenOverButton, gridBagConstraints);
//
//        gridBagConstraints.gridx = 10;
//        gridBagConstraints.gridy = 65;
//        add(fiftyOverButton, gridBagConstraints);
        fiveOverButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame fiveOverFrame = new JFrame();
                fiveOverFrame.setSize(1200, 700);
                fiveOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fiveOverFrame.setVisible(true);
                fiveOverFrame.setLocation(70, 3);

                Random random = new Random();
                int target = random.nextInt(30);
                target = target + 30;
                System.out.println("target=" + target);

                if (playerId == 1) {
//                    System.out.println("inside if gameLogic1=" + gameLogic1);
//                    System.out.println("inside if gameLogic2=" + gameLogic2);
                    if (gameLogic1 == null) {
                        gameLogic1 = new GameLogic(0, target, 5);
                    }
                    if (gameLogic2 == null) {
                        gameLogic2 = new GameLogic2(0, target, 5);
                    }
                    fiveOverFrame.add(gameLogic1);
                    fiveOverFrame.setTitle("Player1");
//                    System.out.println("inside if gameLogic1=" + gameLogic1);
//                    System.out.println("inside if gameLogic2=" + gameLogic2);
                } else {
//                    System.out.println("inside else gameLogic1=" + gameLogic1);
//                    System.out.println("inside gameLogic2=" + gameLogic2);
                    if (gameLogic1 == null) {
                        gameLogic1 = new GameLogic(0, target, 5);
                    }
                    if (gameLogic2 == null) {
                        gameLogic2 = new GameLogic2(0, target, 5);
                    }
                    fiveOverFrame.add(gameLogic2);
                    fiveOverFrame.setTitle("Player2");
//                    System.out.println("inside if gameLogic1=" + gameLogic1);
//                    System.out.println("inside if gameLogic2=" + gameLogic2);
                }
                dispose();
            }
        });

//        tenOverButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                JFrame tenOverFrame = new JFrame();
//                tenOverFrame.setSize(1200, 700);
//                tenOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                tenOverFrame.setVisible(true);
//                tenOverFrame.setLocation(70, 3);
//
//                Random r = new Random();
//                int target = r.nextInt(30);
//                target = target + 45;
//
//                if (playerId == 1) {
//                    tenOverFrame.add(new GameLogic(0, target, 10));
//                } else {
//                    tenOverFrame.add(new GameLogic2(0, target, 10));
//                }
//                dispose();
//            }
//        });
//        fiftyOverButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFrame fiftyOverFrame = new JFrame();
//                fiftyOverFrame.setSize(1200, 700);
//                fiftyOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                fiftyOverFrame.setVisible(true);
//                fiftyOverFrame.setLocation(70, 3);
//
//                Random r = new Random();
//                int i = r.nextInt(30);
//                int target = i + 60;
//
//                if (playerId == 1) {
//                    fiftyOverFrame.add(new GameLogic(0, target, 15));
//                } else {
//                    fiftyOverFrame.add(new GameLogic2(0, target, 15));
//                }
//                dispose();
//            }
//        });
    }

    public static void startServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Server();
                } catch (IOException ex) {
                    JOptionPane.showInputDialog("error in connection , please close and start again");
                }
            }
        }).start();
    }

     public static void startGame(JFrame frame, String playerName) throws IOException {

        try {
            int portNo = Integer.parseInt(GameInitializeFrame.portNo);
            String ip = GameInitializeFrame.ipAddress;
            System.out.println("" + portNo);
            System.out.println("trying in port " + portNo + " ip " + ip);
            socket = new Socket(ip, portNo);
            System.out.println("connected in port " + portNo + " ip " + ip);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        System.out.println("waiting for another player");

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        continueClient = true;

        output.println(playerName); // start to send message to server
        System.out.println("playerName: " + playerName);

        String message = input.readLine(); // (block) wait for receiving message from server
        System.out.println("message: " + message);

        if (message.charAt(0) == '0') {
            playerNames[0] = message.substring(1);
            System.out.println("from 0 with name " + playerNames[0]);
            frame.dispose();
            new OverSelectionFrame(2);
        } else if (message.charAt(0) == '1') {
            playerNames[1] = message.substring(1);
            System.out.println("from 1 with name " + playerNames[1]);
            frame.dispose();
            new OverSelectionFrame(1);
        }

//        frame.dispose();
//        new OverSelectionFrame();
        receiveMessage();

        while (continueClient) {

        }

        input.close();
        output.close();
        socket.close();

    }

    public static void sendMessage(String message) {
        System.out.println("inside sendMessage()");
        System.out.println("sendMessage: " + message);
        output.println(message);
        output.flush();
    }

    private static void receiveMessage() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (continueClient) {
                    try {
                        String mainMessage = "";
                        //Controller controller = new Controller();
                        System.out.println("inside receiveMessage() and over readLine()");
                        String message = input.readLine();
                        System.out.println("message: " + message);
                         if (message.charAt(0) == '1' && gameLogic1.willSend == true) {
                            mainMessage = message.substring(1);
                            System.out.println("mainMessage: " + mainMessage);

//                            System.out.println("gameLogic2=" + gameLogic2); 
//                            System.out.println("gameLogic1=" + gameLogic1);
                            // update opponent(this case player1) score
                            if (gameLogic2 != null) {
                                gameLogic2.currentPositionOpponent = mainMessage;
                                gameLogic2.callRepaint();
                            }
                        } else if (message.charAt(0) == '2' && gameLogic2.willSend == true) {
                            mainMessage = message.substring(1);
                            System.out.println("mainMessage: " + mainMessage);

//                            System.out.println("gameLogic2=" + gameLogic2);
//                            System.out.println("gameLogic1=" + gameLogic1);
                            // update opponent(this case player1) score
                            if (gameLogic1 != null) {
                                gameLogic1.currentPositionOpponent = mainMessage;
                                gameLogic1.callRepaint();
                            }
                        }

                        System.out.println("inside receiveMessage and below recieveMessage");
//                        if (message.charAt(0) == 'C') {
//                            // controller.showMessage(message.substring(1));
//                        } //handle button value
//                        else if (message.charAt(0) == 'M') {
//                            //change here
//                            // myTurn[0] = true;
//                            int value = Integer.parseInt(message.substring(2));
//                            System.out.println(message.substring(1));
//                            //System.out.println();
//                            //controller.handleButtonMove(value);
//
//                        } else if (message.charAt(0) == 'S') {
//                            //myTurn[0] = true;
//                            //controller.updateSCore(message.substring(2));
//
//                        } else if (message.charAt(0) == 'E') {
//                            continueClient = false;
//                            input.close();
//                            output.close();
//                            socket.close();
//
//                        }

                    } catch (IOException ex) {
                        continueClient = false;
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
        t.start();
    }

//    public static void main(String args[]) {
//        new OverSelectionFrame(2);
//    }

}
