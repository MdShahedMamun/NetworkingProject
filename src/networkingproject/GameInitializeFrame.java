/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//it is the second window 
//here the user will choose the length of the match
public class GameInitializeFrame extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JLabel portLabel;
    private JLabel ipAddressLabel;
    private JLabel waitingLabel;
    private JButton submitButton;
    private JTextField nameTextField;
    private JTextField portTextField;
    private JTextField ipAddressTextField;
    public static String name;
    public static String portNo;
    public static String ipAddress;

    public GameInitializeFrame() {

        nameLabel = new JLabel("Name");
        portLabel = new JLabel("Port Number");
        ipAddressLabel = new JLabel("IP Address");
        waitingLabel = new JLabel();
        submitButton = new JButton("Submit");
        nameTextField = new JTextField(15);
        portTextField = new JTextField(15);
        portTextField.setText("1400");
        ipAddressTextField = new JTextField(15);

        setLayout(new GridBagLayout());

        setLocation(500, 100);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(Color.gray);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weighty = 10;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 40;
        add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 40;
        add(nameTextField, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 50;
        add(portLabel, gridBagConstraints);

        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 50;
        add(portTextField, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 60;
        add(ipAddressLabel, gridBagConstraints);

        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 60;
        add(ipAddressTextField, gridBagConstraints);

        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 70;
        add(waitingLabel, gridBagConstraints);

        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 80;
        add(submitButton, gridBagConstraints);

        submitButton.addActionListener(this);

//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                name = nameTextField.getText();
//                portNo = portTextField.getText();
//                ipAddress = ipAddressTextField.getText();
//
//                if (PlayerSelectionFrame.isHost) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                new Server();
//                            } catch (IOException ex) {
//                                JOptionPane.showInputDialog("error in connection , please restrat after closing current");
//                            }
//                        }
//                    }).start();
//                }
//                dispose();
//                new OverSelectionFrame();
//
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        dispose();
////                        new OverSelectionFrame();
////                    }
////                }).start();
//            }
//        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        name = nameTextField.getText();
        portNo = portTextField.getText();
        ipAddress = ipAddressTextField.getText();
        System.out.println("name: " + name);

        waitingLabel.setText("Wait for connection...");
        submitButton.setEnabled(false);

        if (PlayerSelectionFrame.isHost) {
            OverSelectionFrame.startServer();
        }
//        dispose();
//        new OverSelectionFrame();
        final JFrame frame = this;
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OverSelectionFrame.startGame(frame, name);
                } catch (IOException ex) {
                    System.out.println("exception :" + ex);
                }
            }
        }).start();

    }

    //    //That is the main method only for check
//    public static void main(String[] args) {
//
//        new GameInitializeFrame();
//
//    }
}
