package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static ViewFrame.LoginFrameHR.text1;

public class HRFrame extends JFrame{


    static JTable table;

    String[] columnNames = {"firstname", "lastname", "username", "password","email","mobilenumber","gender","status","skills","jobs"};
    String[] columnNameSkill = {"All skill"};

    public JPanel panel4;
    public JLabel Hii;
    public JButton HView,HUpdate,HDelete,HSkillView,HSView,LogOut,HActivateEmp;
    public static String nameUpdate,nameView;
    Connection conn=null;
    public HRFrame(){
        setTitle("HR Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,500);
        setResizable(false);
        panel4 = new JPanel();
        setContentPane(panel4);
        panel4.setLayout(null);

        Hii=new JLabel("Hii "+text1.getText()+",");
        Hii.setBounds(190,10,200,50);
        panel4.add(Hii);

        HView=new JButton("View All Employee");
        HView.setBounds(60,60,300,30);
        panel4.add(HView);

        HView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame1 = new JFrame("Database Search Result");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLayout(new BorderLayout());

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNames);

                table = new JTable();
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                String firstname1 ="";
                String lastname1 = "";
                String username1 ="";
                String password1 = "";
                String email1 ="";
                String mobilenumber1 ="";
                String gender1 ="";
                String status1 ="";
                String skills1 ="";
                String jobs1 ="";

                try
                {
                    conn= JDBCConfiguration.getDBConnection();
                    String sql = "select * from EMP";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i =0;
                    while(rs.next())
                    {
                        firstname1 = rs.getString("first_name");
                        lastname1 = rs.getString("last_name");
                        username1 = rs.getString("user_name");
                        password1 = rs.getString("password");
                        email1 = rs.getString("email_id");
                        mobilenumber1 = rs.getString("mobile_number");
                        gender1 = rs.getString("Gender");
                        status1 = rs.getString("Status");
                        skills1 = rs.getString("Skills");
                        jobs1 = rs.getString("Jobs");
                       model.addRow(new Object[]{firstname1, lastname1, username1, password1, email1, mobilenumber1, gender1, status1, skills1, jobs1});
                        i++;
                    }
                    if(i <1)
                    {
                        JOptionPane.showMessageDialog(null, "No Record Found","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if(i ==1)
                    {
                        System.out.println(i+" Record Found");

                    }
                    else
                    {
                        System.out.println(i+" Records Found");

                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(400,300);
            }

        });





        HUpdate=new JButton("Deactivate Employee");
        HUpdate.setBounds(60,110,300,30);
        panel4.add(HUpdate);

        HUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ADEmployee ade=new ADEmployee();
                ade.setVisible(true);
            }
        });

        HDelete=new JButton("Activate/Deactivate skills of Employee");
        HDelete.setBounds(60,160,300,30);
        panel4.add(HDelete);
        HDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillsHR shr=new SkillsHR();
                shr.setVisible(true);
            }
        });


        HSView=new JButton("View Selected Employee");
        HSView.setBounds(60,210,300,30);
        panel4.add(HSView);

        HSView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMPTable et=new EMPTable();
                et.setVisible(true);

            }
        });


        HSkillView=new JButton("View All Skills");
        HSkillView.setBounds(60,260,300,30);
        panel4.add(HSkillView);

        HSkillView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Database Search Result");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLayout(new BorderLayout());

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNameSkill);

                table = new JTable();
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                String skill ="";


                try
                {
                    conn= JDBCConfiguration.getDBConnection();
                    String sql = "select DISTINCT skill from Skills";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i =0;
                    while(rs.next())
                    {
                        skill = rs.getString("skill");

                        model.addRow(new Object[]{skill});
                        i++;
                    }
                    if(i <1)
                    {
                        JOptionPane.showMessageDialog(null, "No Record Found","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if(i ==1)
                    {
                        System.out.println(i+" Record Found");
                    }
                    else
                    {
                        System.out.println(i+" Records Found");
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(400,300);

            }
        });

        HActivateEmp=new JButton("Activate Employee");
        HActivateEmp.setBounds(60,310,300,30);
        panel4.add(HActivateEmp);
        HActivateEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ActivateEmployee ace=new ActivateEmployee();
               ace.setVisible(true);
            }
        });

        LogOut=new JButton("Logout");
        LogOut.setBounds(60,360,300,30);
        panel4.add(LogOut);
        LogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Logout lg=new Logout();
               lg.setVisible(true);
               dispose();
            }
        });
    }
}
