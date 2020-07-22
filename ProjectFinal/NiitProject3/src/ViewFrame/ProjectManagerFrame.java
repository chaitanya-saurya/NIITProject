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

import static ViewFrame.LoginFramePM.text1;

public class ProjectManagerFrame extends JFrame {
    public JPanel panel4;
    public JLabel Hii;
    public JButton HView, HUpdate,PLogOut,PAddJob,PViewJob,PDeactivateJob,PViewEMP;
    public static String nameUpdateP;
    String[] columnNameskill = {"All Skills"};
    String[] columnNamesJob = {"All Jobs"};
    String[] columnNamesJobSkillWise = {"Skill","Job"};
    String[] columnNames = {"firstname", "lastname", "username", "password","email","mobilenumber","gender","status","skills","jobs"};
    Connection conn=null;

    public ProjectManagerFrame() {
        setTitle("Project Manager Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,500);
        setResizable(false);
        panel4 = new JPanel();
        setContentPane(panel4);
        panel4.setLayout(null);

        Hii = new JLabel("Hii "+text1.getText()+",");
        Hii.setBounds(190,10,200,50);
        panel4.add(Hii);

        HView = new JButton("View Employee of a particular skill");
        HView.setBounds(60,60,300,30);
        panel4.add(HView);

        HView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEmployeeSkill ves=new ViewEmployeeSkill();
                ves.setVisible(true);
          }
        });

        HUpdate = new JButton("View All skills");
        HUpdate.setBounds(60,110,300,30);
        panel4.add(HUpdate);

        HUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("Database Search Result");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setLayout(new BorderLayout());

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNameskill);

                JTable table = new JTable();
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
                    String sql = "select distinct skill from Skills";
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
                frame2.add(scroll);
                frame2.setVisible(true);
                frame2.setSize(400,300);
            }
        });

        PAddJob=new JButton("Add Job");
        PAddJob.setBounds(60,160,300,30);
        panel4.add(PAddJob);
        PAddJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               AddJobs aj=new AddJobs();
               aj.setVisible(true);
            }
        });

        PViewJob=new JButton("View All Job");
        PViewJob.setBounds(60,210,300,30);
        panel4.add(PViewJob);
        PViewJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Database Search Result");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLayout(new BorderLayout());

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNamesJob);

                JTable table = new JTable();
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                String JobName ="";

                try
                {
                    conn= JDBCConfiguration.getDBConnection();
                    String sql = "select distinct JobTitle from Job";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i =0;
                    while(rs.next())
                    {
                        JobName = rs.getString("JobTitle");
                        model.addRow(new Object[]{JobName});
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

        PViewJob=new JButton("View Skill-wise Job");
        PViewJob.setBounds(60,260,300,30);
        panel4.add(PViewJob);
        PViewJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Database Search Result");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLayout(new BorderLayout());

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNamesJobSkillWise);

                JTable table = new JTable();
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                String JobName ="";
                String JobSkill="";

                try
                {
                    conn= JDBCConfiguration.getDBConnection();
                    String sql = "select distinct KeySkill,JobTitle from Job";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    int i =0;
                    while(rs.next())
                    {
                        JobSkill=rs.getString("KeySkill");
                        JobName = rs.getString("JobTitle");
                        model.addRow(new Object[]{JobSkill,JobName});
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

        PDeactivateJob=new JButton("Deactivate Job");
        PDeactivateJob.setBounds(60,310,300,30);
        panel4.add(PDeactivateJob);
        PDeactivateJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              DeactivateJob dj=new DeactivateJob();
              dj.setVisible(true);
            }
        });

        PLogOut=new JButton("View Employee list who applied for Job");
        PLogOut.setBounds(60,360,300,30);
        panel4.add(PLogOut);
        PLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobEmployeeList jel=new JobEmployeeList();
                jel.setVisible(true);
            }
        });

        PLogOut=new JButton("Logout");
        PLogOut.setBounds(60,410,300,30);
        panel4.add(PLogOut);
        PLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logout lg=new Logout();
                lg.setVisible(true);
                dispose();
            }
        });
    }
}