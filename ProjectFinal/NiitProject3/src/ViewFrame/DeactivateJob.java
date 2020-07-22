package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;



public class DeactivateJob extends JFrame implements ActionListener {


    JFrame frame1;

    JLabel l0, l1, l2;

    JComboBox c1;

    JButton b1;

    Connection conn = null;

    ResultSet rs, rs1;

    Statement st, st1;

    PreparedStatement pst;

    public String ids;

    static JTable table;



    DeactivateJob() {


        l0 = new JLabel("Fatching Job Information");

        l0.setForeground(Color.red);

        l0.setFont(new Font("Serif", Font.BOLD, 20));

        l1 = new JLabel("Select Job");

        b1 = new JButton("Deactivate Job");


        l0.setBounds(100, 50, 350, 40);

        l1.setBounds(75, 110, 75, 20);

        b1.setBounds(150, 150, 180, 20);

        b1.addActionListener(this);


        setTitle("Fetching Job Info From DataBase");

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
            System.out.println(ids);

            c1 = new JComboBox(v);
            System.out.println(v);

            c1.setBounds(150, 110, 150, 20);


            add(c1);

            st.close();

            rs.close();

        } catch (Exception e) {

        }

    }


    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1) {

            try {
                conn = JDBCConfiguration.getDBConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE Job SET Status=? where JobTitle=?");

                ps.setString(1, "Deactive");
                ps.setString(2, (String) c1.getSelectedItem());


                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null,

                            "Updated Successfully");
                    dispose();
                }
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        }

}






