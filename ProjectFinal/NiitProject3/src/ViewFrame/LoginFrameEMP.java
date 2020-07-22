package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrameEMP extends JFrame{
    public JButton Submit;
    public JPanel panel;
    public JLabel label1,label2;
    public static JTextField text1,text2;
    Connection conn=null;
    public LoginFrameEMP()
    {
        setTitle("Employee Login");
        label1 = new JLabel();
        label1.setText("Username:");
        label1.setBounds(10,10,80,22);
        text1 = new JTextField(10);
        text1.setBounds(75,10,200,30);
        label2 = new JLabel();
        label2.setBounds(10,40,80,22);
        label2.setText("Password:");
        text2 = new JPasswordField(10);
        text2.setBounds(75,40,200,30);


        Submit=new JButton("Login");
        Submit.setBounds(65,80,100,30);

        panel=new JPanel(null);
        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);

        panel.add(Submit);
        add(panel,BorderLayout.CENTER);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String value1 = text1.getText();
                String value2 = text2.getText();
                try {
                    conn= JDBCConfiguration.getDBConnection();
                    PreparedStatement ps = conn.prepareStatement("SELECT first_name FROM EMP where user_name=? and password=?");
                    ps.setString(1, value1);
                    ps.setString(2, value2);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        EmployeeFrame Eframe=new EmployeeFrame();
                        Eframe.setVisible(true);
                        dispose();
                    }
                    else {

                        JOptionPane.showMessageDialog(null,

                                "Incorrect email-Id or password..Try Again with correct detail");

                    }

                    rs.close();
                    conn.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}

