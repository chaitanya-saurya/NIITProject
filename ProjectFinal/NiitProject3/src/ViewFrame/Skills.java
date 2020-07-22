package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import static ViewFrame.LoginFrameEMP.text1;


public class Skills extends JFrame {
    public JPanel panelSkills;
    public JLabel Jlb;
    public JComboBox Jcb;
    public String skill[]={"Java","Python","SpringBoot","C++","HTML"};
    public JButton Jb;
    public String newSkills;
    Connection conn=null;
public Skills(){
    setTitle("Skills");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(450, 190, 1000, 600);
    setResizable(false);
    panelSkills = new JPanel();
    setContentPane(panelSkills);
    panelSkills.setLayout(null);

    Jlb=new JLabel("Please select your skill");
    Jlb.setBounds(90,10,150,50);
    panelSkills.add(Jlb);

    Jcb=new JComboBox(skill);
    Jcb.setBounds(110,70,100,28);
    panelSkills.add(Jcb);

    Jb = new JButton("Submit");
    Jb.setBounds(110, 140, 100, 30);
    panelSkills.add(Jb);
    setSize(400,400);

    Jb.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newSkills=String.valueOf(Jcb.getSelectedItem().toString());

            try {
                conn= JDBCConfiguration.getDBConnection();
                String UsernameS=text1.getText();
                String statusOf="Deactivate";
                String query="INSERT INTO Skills values('" + UsernameS + "','" + newSkills +"','" + statusOf + "')";
                Statement sta = conn.createStatement();
                sta.executeUpdate(query);
                JOptionPane.showMessageDialog(Jb, "Skill added to "+UsernameS+". Please wait for HR to activate your skill.");
                conn.close();
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
  }
}
