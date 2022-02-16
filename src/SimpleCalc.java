import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalc {
    JFrame f = new JFrame("Simple Calculator");
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton minusButton;
    private JButton enterButton;
    private JPanel mainPanel;
    private JLabel resultlabel;

    private Integer op1=0;
    private Integer op2=0;
    private Character operator = null;

    private void deselectButton () {
        addButton.setBackground(Color.LIGHT_GRAY);
        minusButton.setBackground(Color.LIGHT_GRAY);
        divideButton.setBackground(Color.LIGHT_GRAY);
        multiplyButton.setBackground(Color.LIGHT_GRAY);
        resultlabel.setForeground(Color.BLACK);

    }

    private void resetVars () {
        textField1.setText("");
        textField2.setText("");
        operator= null;
    }


    private void calculate() {
        op1 = Integer.parseInt(textField1.getText());
        op2 = Integer.parseInt(textField2.getText());

        if(operator == null){
            resultlabel.setForeground(Color.red);
            resultlabel.setText("NO OPERATOR SELECTED");
            deselectButton();
            resetVars();
            return;
        }
        int result;
        switch (operator){
            case '+':
                result = op1+op2;
               resultlabel.setText(""+op1+"+"+op2+"="+result);
               resetVars();
               break;
            case '-':
                result = op1-op2;
                resultlabel.setText(""+op1+"-"+op2+"="+result);
                resetVars();
                break;
            case '*':
                result = op1*op2;
                resultlabel.setText(""+op1+"*"+op2+"="+result);
                resetVars();
                break;
            case '/':
                result = op1/op2;
                resultlabel.setText(""+op1+"/"+op2+"="+result);
                resetVars();
                break;

        }
    }


    SimpleCalc(){
        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                deselectButton();
                addButton.setBackground(Color.yellow);
                operator = '+';
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                deselectButton();
                minusButton.setBackground(Color.yellow);
                operator = '-';
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                deselectButton();
                multiplyButton.setBackground(Color.yellow);
                operator = '*';
            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                deselectButton();
                divideButton.setBackground(Color.yellow);
                operator = '/';
            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                deselectButton();
                calculate();
            }
        });

    }

    public static void main(String[] args) {
        new SimpleCalc();
    }

}
