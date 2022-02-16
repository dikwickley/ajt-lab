package test;

import javax.swing.*;
import java.awt.*;

public class SwingCalc {

    final int FRAME_WIDTH = 325, FRAME_HEIGHT = 325;
    final int HEIGHT = 400, WIDTH = 300, H_SPACE = 10, V_SPACE = 10;
    final int TOPX = 30, TOPY = 50;

    SwingCalc() {
        JFrame fr = new JFrame("Swing Calculator");

        JPanel output = new JPanel(new GridLayout(2, 0));
        output.setBounds(0, 0, 300, 100);
        output.setBackground(Color.LIGHT_GRAY);

        JLabel operand = new JLabel();
        operand.setText("21343");
        operand.setBounds(0,0,300,50);

        output.add(operand);

        JPanel numpad = new JPanel(new GridLayout(0, 3));
        numpad.setSize(300, 300);
        numpad.setBounds(0, 100, 300, 300);
        numpad.setBackground(Color.pink);

        fr.add(output);
        fr.add(numpad);

        fr.setSize(WIDTH, HEIGHT);
        fr.setLayout(null);
        fr.setVisible(true);

    }

    public static void main(String[] args) {
        new SwingCalc();
    }


}
