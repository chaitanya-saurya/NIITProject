package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static ViewFrame.LoginFrameEMP.text1;


public class EmployeeUpdateFrame extends JFrame {
    public JPanel panel2;
    public JTextField firstname;
    public JTextField lastname;
    public JTextField email;
    public JTextField username;
    public JTextField mob;
    public JPasswordField pass;
    public JButton UButton;
    public JComboBox cb1;
    public JRadioButton r1, r2;
    public String Role[] = {"C++","Java","HTML","SQL","Python"};
    public String firstNameU;
    public String lastNameU;
    public String emailIdU;
    public String mobileNumberU;
    public String passwordU;
    public String radioTextU;
    public String newRoleU;
    Connection conn=null;

    public EmployeeUpdateFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        panel2 = new JPanel();
        setContentPane(panel2);
        panel2.setLayout(null);
        setTitle("Employee Update");
        JLabel lblNewUserRegister = new JLabel("Update Form(Employee)");
        lblNewUserRegister.setBounds(430, 52, 325, 50);
        panel2.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First name");
        lblName.setBounds(58, 152, 99, 43);
        panel2.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setBounds(58, 243, 110, 29);
        panel2.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email:");
        lblEmailAddress.setBounds(542, 159, 99, 29);
        panel2.add(lblEmailAddress);

        firstname = new JTextField();
        firstname.setBounds(214, 151, 228, 50);
        panel2.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setBounds(214, 235, 228, 50);
        panel2.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();

        email.setBounds(707, 151, 228, 50);
        panel2.add(email);
        email.setColumns(10);


        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(542, 245, 99, 24);
        panel2.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile number");
        lblMobileNumber.setBounds(542, 329, 139, 26);
        panel2.add(lblMobileNumber);

        mob = new JTextField();
        mob.setBounds(707, 320, 228, 50);
        panel2.add(mob);
        mob.setColumns(10);

        pass = new JPasswordField();
        pass.setBounds(707, 235, 228, 50);
        panel2.add(pass);

        JLabel RoleLabel = new JLabel("Skills:");
        RoleLabel.setBounds(58, 329, 50, 28);
        panel2.add(RoleLabel);


        cb1 = new JComboBox(Role);
        cb1.setBounds(214, 329, 100, 28);
        panel2.add(cb1);

        JLabel Gender = new JLabel("Gender:");
        Gender.setBounds(58, 420, 50, 28);
        panel2.add(Gender);

        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r1.setBounds(214, 420, 100, 30);
        r2.setBounds(214, 450, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        panel2.add(r1);
        panel2.add(r2);

        UButton = new JButton("Update");
        UButton.setBounds(450, 500, 100, 50);
        panel2.add(UButton);
        setSize(1000,1000);

        UButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNameU = firstname.getText();
                lastNameU = lastname.getText();
                emailIdU = email.getText();
                mobileNumberU = mob.getText();
                passwordU = pass.getText();
                radioTextU = "";
                newRoleU = String.valueOf(cb1.getSelectedItem().toString());
                String helloE=text1.getText();


                if (r1.isSelected()) {
                    radioTextU = r1.getText();
                } else {
                    radioTextU = r2.getText();
                }

                try {
                    conn= JDBCConfiguration.getDBConnection();
                    PreparedStatement ps = conn.prepareStatement("UPDATE EMP SET first_name=?, last_name=?, password=?, email_id=?, mobile_number=?, Gender=?, Skills=? where user_name=?");

                    ps.setString(1, firstNameU);
                    ps.setString(2, lastNameU);
                    ps.setString(3, passwordU);
                    ps.setString(4, emailIdU);
                    ps.setString(5, mobileNumberU);
                    ps.setString(6, radioTextU);
                    ps.setString(7, newRoleU);
                    ps.setString(8,helloE);

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
        });

    }
}



