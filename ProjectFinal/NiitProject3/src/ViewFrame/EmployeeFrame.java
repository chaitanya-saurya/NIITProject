package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static ViewFrame.LoginFrameEMP.text1;
import static ViewFrame.LoginFrameEMP.text2;

public class EmployeeFrame extends JFrame {
    public JPanel panel3;
    public JLabel Hi;
    public JButton EView,EUpdate,ESkills,EJob,ELogOut;
    Connection conn=null;
    public EmployeeFrame(){
        setTitle("Employee Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,500);
        setResizable(false);
        panel3 = new JPanel();
        setContentPane(panel3);
        panel3.setLayout(null);
        Hi=new JLabel("Hi "+text1.getText());
        Hi.setBounds(190,10,50,50);
        panel3.add(Hi);

        EView=new JButton("View your Records");
        EView.setBounds(100,60,220,30);
        panel3.add(EView);
        EView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    conn= JDBCConfiguration.getDBConnection();
                    int count = 0;
                    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EMP where user_name=? and password=?");
                    pstmt.setString(1, text1.getText());
                    pstmt.setString(2, text2.getText());
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        String firstname1 = rs.getString("first_name");
                        String lastname1 = rs.getString("last_name");
                        String username1 = rs.getString("user_name");
                        String password1 = rs.getString("password");
                        String email1 = rs.getString("email_id");
                        String mobilenumber1 = rs.getString("mobile_number");
                        String gender1 = rs.getString("Gender");
                        String status1 = rs.getString("Status");
                        String skills1 = rs.getString("Skills");
                        String jobs1 = rs.getString("Jobs");
                        System.out.println(firstname1 + lastname1 + username1);

                        String output = "Employee %s - %s - %s - %s - %s - %s - %s - %s";
                        String output2 = (String.format(output, firstname1, lastname1, username1, password1, email1, mobilenumber1, gender1, status1,skills1,jobs1));
                        JOptionPane.showMessageDialog(null, output2, "The list is", JOptionPane.INFORMATION_MESSAGE);
                    }

                    rs.close();
                    conn.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        EUpdate=new JButton("Update your Records");
        EUpdate.setBounds(100,110,220,30);
        panel3.add(EUpdate);
        EUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               EmployeeUpdateFrame euf=new EmployeeUpdateFrame();
               euf.setVisible(true);

            }
        });

        ESkills=new JButton("Add Skills");
        ESkills.setBounds(100,160,220,30);
        panel3.add(ESkills);
        ESkills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Skills obj=new Skills();
               obj.setVisible(true);

            }
        });

        EJob=new JButton("Apply for Job");
        EJob.setBounds(100,210,220,30);
        panel3.add(EJob);
        EJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobsApply ja=new JobsApply();
                ja.setVisible(true);

            }
        });

        ELogOut=new JButton("Logout");
        ELogOut.setBounds(100,260,220,30);
        panel3.add(ELogOut);
        ELogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logout lg=new Logout();
                lg.setVisible(true);
                dispose();
            }
        });
    }
}
