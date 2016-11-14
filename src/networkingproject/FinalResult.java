/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;


//It is to show the result
public class FinalResult extends JFrame {

    private int result;

    private JLabel winLabel = new JLabel("Congratulation!! You win :) ");
    private JLabel loseLabel = new JLabel("You lose! :( ");

    public FinalResult(int i) {

        setLayout(new GridBagLayout());
        result = i;
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(500, 150);
        getContentPane().setBackground(Color.gray);
        setFont(new Font("Serif", Font.BOLD, 250));

        if (i == 1) {
            add(winLabel);
        } else {
            add(loseLabel);
        }

    }

//    public static void main(String[] args) {
//        new FinalResult(0);
//    }

}
