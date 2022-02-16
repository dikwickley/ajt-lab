package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstSwing {

    int count = 0;

    FirstSwing() {
        JFrame f = new JFrame("test");

        final JLabel l =  new JLabel();
        l.setBounds(130, 130, 400,40);
        l.setText("");

        JButton b = new JButton("click me");
        b.setBounds(130, 100, 100, 40);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                count+=1;
                l.setText(Integer.toString(count));

            }
        });

        f.add(b);
        f.add(l);

        f.setSize(400, 500);
        f.setLayout(null);
        f.setVisible(true);
    }


    public static void main(String[] args) {
        new FirstSwing();

    }
}
