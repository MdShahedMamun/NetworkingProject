/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PlayerSelectionFrame extends JFrame {

    public static Boolean isHost=false;

    private JLabel optionLabel;
    private JButton firstPlayerButton;
    private JButton SecondPlayerButton;

    public PlayerSelectionFrame() {

        optionLabel = new JLabel("Play as First Player or Second Player");

        firstPlayerButton = new JButton("First Player ");
        SecondPlayerButton = new JButton("Second Player ");

        setLayout(new GridBagLayout());

        setLocation(500, 100);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(Color.gray);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.weighty = 20;
        add(optionLabel, gridBagConstraints);

        gridBagConstraints.weighty = 10;
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 50;
        add(firstPlayerButton, gridBagConstraints);

        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 55;
        add(SecondPlayerButton, gridBagConstraints);

        firstPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHost=true;     // FirstPlayer is host for which server will start 
                dispose();
                new GameInitializeFrame();
            }
        });

        SecondPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameInitializeFrame();
            }
        });

    }

//    //That is the main method only for check
//    public static void main(String[] args) {
//
//        new PlayerSelectionFrame();
//
//    }
}
