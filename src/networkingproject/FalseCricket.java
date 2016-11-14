/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//It is the main window of this game it has three button and a label
public class FalseCricket extends JFrame {

    private JLabel welcomeLabel;
    private JButton startButton;
    private JButton aboutButton;
    private JButton rulesButton;
//    private JButton firstPlayerButton;
//    private JButton secondPlayerButton;

    public FalseCricket() {
        welcomeLabel = new JLabel("Welcome to False Clicket");
        startButton = new JButton("Start game");
        aboutButton = new JButton("About");
        rulesButton = new JButton("Rules");
//        firstPlayerButton = new JButton("Play as first player");
//        secondPlayerButton = new JButton("Play as second player ");

        setLayout(new GridBagLayout());

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(500, 100);
        getContentPane().setBackground(Color.gray);

        //Gridbagconstrains taken to positionate the button in the perfact place
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.weightx = 5;
        gridBagConstraints.weighty = 5;
        add(welcomeLabel, gridBagConstraints);

        gridBagConstraints.weightx = 2;
        gridBagConstraints.weighty = 2;
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 50;
        add(startButton, gridBagConstraints);

        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 60;
        add(rulesButton, gridBagConstraints);

//        gridBagConstraints.gridx = 10;
//        gridBagConstraints.gridy = 70;
//        add(firstPlayerButton, gridBagConstraints);
//
//        gridBagConstraints.gridx = 10;
//        gridBagConstraints.gridy = 80;
//        add(secondPlayerButton, gridBagConstraints);

//        gridBagConstraints.gridx = 10;
//        gridBagConstraints.gridy = 70;
//        gridBagConstraints.weighty = 3;
//        add(aboutButton, gridBagConstraints);

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // pass control to  GuestHostSelector which is "new PlayerSelectionFrame()"
                dispose();
                new PlayerSelectionFrame();
//                //submit e click korle new OverSelectionFrame() call hobe
//                dispose();
//                new OverSelectionFrame();
            }
        });

        rulesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame rulesFrame = new JFrame();

                rulesFrame.setSize(300, 300);
                //rulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                rulesFrame.setVisible(true);
                rulesFrame.setLocation(500, 100);
                rulesFrame.setResizable(false);

                Rules fcRules = new Rules();
                rulesFrame.add(fcRules);

            }
        });

    }

    public static void main(String[] args) {
        // TODO code application logic here
        new FalseCricket();

    }

}

