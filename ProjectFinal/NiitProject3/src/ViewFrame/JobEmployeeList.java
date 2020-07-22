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
import java.sql.Statement;
import java.util.Vector;



public class JobEmployeeList extends JFrame implements ActionListener {


    JFrame frame1;

    JLabel l0, l1, l2;

    JComboBox c1;

    JButton b1;

    Connection conn = null;

    ResultSet rs, rs1;

    Statement st, st1;

    PreparedStatement pst;

    String ids;

    static JTable table;

    String[] columnNames = {"firstname", "lastname", "username", "password","email","mobilenumber","gender","status","skills","jobs"};

    String from;


    JobEmployeeList() {


        l0 = new JLabel("Fatching Job Information");

        l0.setForeground(Color.red);

        l0.setFont(new Font("Serif", Font.BOLD, 20));

        l1 = new JLabel("Select Job");

        b1 = new JButton("View");


        l0.setBounds(100, 50, 350, 40);

        l1.setBounds(75, 110, 75, 20);

        b1.setBounds(150, 150, 150, 20);

        b1.addActionListener(this);


        setTitle("Fetching Info From DataBase");

        setLayout(null);

        setVisible(true);

        setSize(500, 500);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        add(l0);

        add(l1);

        add(b1);

        try {

            conn = JDBCConfiguration.getDBConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select JobTitle from Job");

            Vector v = new Vector();

            while (rs.next()) {

                ids = rs.getString(1);

                v.add(ids);

            }

            c1 = new JComboBox(v);

            c1.setBounds(150, 110, 150, 20);


            add(c1);

            st.close();

            rs.close();

        } catch (Exception e) {

        }

    }


    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1) {

            showTableData();

        }


    }


    public void showTableData() {


        frame1 = new JFrame("Database Search Result");

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

        from = (String) c1.getSelectedItem();



        try {

            pst = conn.prepareStatement("select * from EMP where Jobs='" + from + "'");

            ResultSet rs = pst.executeQuery();

            int i = 0;

            if (rs.next()) {

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

                model.addRow(new Object[]{firstname1, lastname1, username1, password1, email1, mobilenumber1, gender1, status1, skills1, jobs1});

                i++;

            }

            if (i < 1) {

                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);

            }

            if (i == 1) {

                System.out.println(i + " Record Found");

            } else {

                System.out.println(i + " Records Found");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        frame1.add(scroll);

        frame1.setVisible(true);

        frame1.setSize(400, 300);

    }
}