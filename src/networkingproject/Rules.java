/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkingproject;

import java.awt.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

//It is for show the rules of the game
public class Rules extends JPanel {


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.gray);

        g.setFont(new Font("Serif", Font.PLAIN, 20));

        g.drawString("It is a game that's rules are as like ", 15, 20);
        g.drawString("as cricket. Here you will press the ", 10, 50);
        g.drawString("button on the circle and you will ", 10, 80);
        g.drawString("find the result of this ball. One ", 10, 110);
        g.drawString("click willbe counted as one ball. ", 10, 140);

    }

}
