import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SelectJDBC {
    Connection c = null;
    Statement stmt = null;
    JFrame f = new JFrame("SELECT Query");
    private JTextField tableName;
    private JTextField attributeName;
    private JButton fetchButton;
    private JTextPane output;
    private JPanel mainPanel;
    private JLabel connectionLabel;

    SelectJDBC(){
        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
//        Class.forName("org.postgresql.Driver");
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
            connectionLabel.setForeground(Color.green);
            connectionLabel.setText("Connected: True");
            System.out.println("Connected database successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                String[] attrList= {"*"};
                String attr = attributeName.getText();
                String table = tableName.getText();
                System.out.println("tablename: "+ table=="");
                if(table.isEmpty()){
                 output.setForeground(Color.red);
                 output.setText("No table name given");
                } else {
                    if(attr.isEmpty()){
                        output.setText("No attributes given");
                    } else {
                        selectQuery(table, attr);
                    }
                }
            }
        });

    }
    private void selectQuery(String table,String attr){
        output.setForeground(Color.black);
        output.setText("");
        executeQuery("SELECT " + attr + " FROM " +table + ";");
    }

    private void executeQuery(String q){
        String op = "";
        try{
            stmt = c.createStatement();
            ResultSet res = stmt.executeQuery(q);
            ResultSetMetaData rsmd = res.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + ", ");
                op += metaData.getColumnName(i) + ", ";
            }

            System.out.println("");
            op += "\n";

            while (res.next()) {

                try{
                    int equip_id = res.getInt("equip_id");
                    op += equip_id + ", ";
                } catch(java.sql.SQLException e){
                  op += "";
                }

                try{
                    String type = res.getString("type");
                    op += type + ", ";
                } catch(java.sql.SQLException e){
                    op += "";
                }

                try{
                    String color = res.getString("color");
                    op += color + ", ";
                } catch(java.sql.SQLException e){
                    op += "";
                }

                try{
                    String location = res.getString("location");
                    op += location + ", ";
                } catch(java.sql.SQLException e){
                    op += "";
                }

//                try{
//                    Date install_date = res.getDate("install_date");
//                    op += install_date + ", ";
//                } catch(java.sql.SQLException e){
//                    op += "";
//                }
                op += "\n";

            }

            output.setText(op);

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        new SelectJDBC();
    }

}
