package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class SkillsHR extends JFrame {
    public JPanel panelSkills;
    public JTextField usernameHR;
    public JLabel Jlb,Jlb2;
    public JComboBox Jcb;
    public String skill[]={"Java","Python","SpringBoot","C++","HTML"};
    public JButton Jb;
    public String newSkills,AD,UserNS;
    public JRadioButton A1,D1;
    Connection conn=null;
    public SkillsHR(){
        setTitle("Skills");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        panelSkills = new JPanel();
        setContentPane(panelSkills);
        panelSkills.setLayout(null);

        Jlb2=new JLabel("Enter username of Employee:");
        Jlb2.setBounds(90,10,200,50);
        panelSkills.add(Jlb2);

        usernameHR=new JTextField();
        usernameHR.setBounds(260,10,180,50);
        panelSkills.add(usernameHR);

        Jlb=new JLabel("Please select Employee skill");
        Jlb.setBounds(90,70,200,50);
        panelSkills.add(Jlb);

        Jcb=new JComboBox(skill);
        Jcb.setBounds(110,130,100,28);
        panelSkills.add(Jcb);

        JLabel ADSkills=new JLabel("Activate/Deactivate Skill:");
        ADSkills.setBounds(90,190,190,28);
        panelSkills.add(ADSkills);

        A1=new JRadioButton("Active");
        D1=new JRadioButton("Deactive");
        A1.setBounds(240,190,100,30);
        D1.setBounds(240,220,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(A1);bg.add(D1);
        panelSkills.add(A1);
        panelSkills.add(D1);

        Jb = new JButton("Done");
        Jb.setBounds(240, 270, 100, 30);
        panelSkills.add(Jb);
        setSize(700,700);

        Jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newSkills=String.valueOf(Jcb.getSelectedItem().toString());
                AD="";
                if(A1.isSelected()){
                    AD=A1.getText();
                }else{
                    AD=D1.getText();
                }
                UserNS=usernameHR.getText();

                try {
                    conn= JDBCConfiguration.getDBConnection();
                    PreparedStatement ps = conn.prepareStatement("UPDATE Skills SET StatusOfSkill=? where user_name=? and skill=?");

                    ps.setString(1, AD);
                    ps.setString(2,UserNS);
                    ps.setString(3,newSkills);

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null,

                                "Updated Successfully");
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,

                                "The information above is incorrect");
                    }
                    conn.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
