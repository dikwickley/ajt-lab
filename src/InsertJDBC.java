import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertJDBC {
    Connection c = null;
    PreparedStatement pstmt = null;
    JFrame f = new JFrame("INSERT Query");
    private JPanel mainPanel;
    private JTextField tabelField;
    private JTextField typeField;
    private JButton INSERTButton;
    private JTextField colorField;
    private JTextField locationField;
    private JLabel connectionLabel;
    private JTextField idField;
    private JLabel outputLabel;

    InsertJDBC(){
        f.setVisible(true);
        f.setContentPane(mainPanel);

        try{
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
            connectionLabel.setForeground(Color.green);
            connectionLabel.setText("Connected: True");
            System.out.println("Connected database successfully");
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //get names and insert

                try {
                    String table = tabelField.getText();
                    int id = Integer.parseInt(idField.getText());
                    String type = typeField.getText();
                    String color = colorField.getText();
                    String location = locationField.getText();

//                    if(id==null || type=="" || color=="" || location==""){
//
//                    }

                    pstmt = c.prepareStatement("INSERT INTO " + table + " VALUES(?,?,?,?)");

                    pstmt.setInt(1,id);
                    pstmt.setString(2,type);
                    pstmt.setString(3,color);
                    pstmt.setString(4,location);

                    int i=pstmt.executeUpdate();
                    System.out.println(i+" records inserted");
                    outputLabel.setText(i+" records inserted");
                    idField.setText("");
                    typeField.setText("");
                    colorField.setText("");
                    locationField.setText("");


                } catch ( Exception e ) {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        new InsertJDBC();
    }
}


