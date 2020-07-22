package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterPM extends JFrame {
    public JPanel panel2;
    public JTextField firstname;
    public JTextField lastname;
    public JTextField email;
    public JTextField username;
    public JTextField mob;
    public JPasswordField pass;
    public JButton RButton,avail;
    public JRadioButton r1,r2;
    public String firstName;
    public String lastName;
    public String emailId;
    public String userName;
    public String mobileNumber;
    public String password;
    public String radioText;
    public String msg;
    Connection conn=null;

    public RegisterPM() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        panel2 = new JPanel();
        setContentPane(panel2);
        panel2.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Register Form");
        lblNewUserRegister.setBounds(430, 52, 325, 50);
        panel2.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First name");
        lblName.setBounds(58, 152, 99, 43);
        panel2.add(lblName);

        avail = new JButton("Check Availability");
        avail.setBounds(707, 200, 150, 20);
        panel2.add(avail);
        avail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean usernameExists = false;
                userName = username.getText();
                try {
                    String username1="";
                    conn = JDBCConfiguration.getDBConnection();
                    PreparedStatement pstmt = conn.prepareStatement("SELECT user_name FROM PM where user_name=?");
                    pstmt.setString(1, userName);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        usernameExists=true;
                        JOptionPane.showMessageDialog(null, "This username is already taken. Please choose another", "Error!", JOptionPane.ERROR_MESSAGE);
                        username.setText("");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This username is available", "", JOptionPane.INFORMATION_MESSAGE);
                    }

                    rs.close();
                    conn.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setBounds(58, 243, 110, 29);
        panel2.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email:");
        lblEmailAddress.setBounds(542, 420, 50, 36);
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

        email.setBounds(707, 420, 228, 50);
        panel2.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setBounds(707, 151, 228, 50);
        panel2.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(542, 159, 99, 29);
        panel2.add(lblUsername);

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

        JLabel Gender=new JLabel("Gender:");
        Gender.setBounds(58,329,50,28);
        panel2.add(Gender);

        r1=new JRadioButton("Male");
        r2=new JRadioButton("Female");
        r1.setBounds(214,329,100,30);
        r2.setBounds(214,360,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);bg.add(r2);
        panel2.add(r1);
        panel2.add(r2);

        RButton = new JButton("Register");
        RButton.setBounds(350, 600, 259, 74);
        panel2.add(RButton);
        setSize(1000,1000);

        RButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstName =firstname.getText();
                lastName =lastname.getText();
                emailId = email.getText();
                userName = username.getText();
                mobileNumber = mob.getText();
                password = pass.getText();
                radioText="";
                msg = "" + firstName+"\n";

                if(r1.isSelected()){
                    radioText=r1.getText();
                }else{
                    radioText=r2.getText();
                }
                try {
                    conn= JDBCConfiguration.getDBConnection();

                    String query = "INSERT INTO PM values('" + firstName + "','" + lastName + "','" + userName + "','" +
                            password + "','" + emailId + "','" + mobileNumber +"','" + radioText  + "')";

                    Statement sta = conn.createStatement();
                    sta.executeUpdate(query);
                    JOptionPane.showMessageDialog(RButton, "Welcome " + msg + "Your account is successfully created");
                    conn.close();
                    dispose();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
