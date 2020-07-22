package ViewFrame;

import Config.JDBCConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class AddJobs extends JFrame {
    public JPanel panel2;
    public JTextField lastname;
    public JTextField email;
    public JTextField username;
    public JTextField pass;
    public JButton RButton;
    public int firstName;
    public String lastName;
    public String emailId;
    public String userName;
    public String mobileNumber;
    public String password;
    public JRadioButton r1,r2;
    Connection conn = null;

    public AddJobs() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        panel2 = new JPanel();
        setContentPane(panel2);
        panel2.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Add Job");
        lblNewUserRegister.setBounds(430, 52, 325, 50);
        panel2.add(lblNewUserRegister);

        JLabel lblNewLabel = new JLabel("Job Title");
        lblNewLabel.setBounds(58, 243, 110, 29);
        panel2.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Location");
        lblEmailAddress.setBounds(542, 159, 50, 36);
        panel2.add(lblEmailAddress);


        lastname = new JTextField();
        lastname.setBounds(214, 235, 228, 50);
        panel2.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();
        email.setBounds(707, 151, 228, 50);
        panel2.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setBounds(214, 151, 228, 50);
        panel2.add(username);
        username.setColumns(10);


        JLabel lblUsername = new JLabel("KeySkill");
        lblUsername.setBounds(58, 159, 99, 29);
        panel2.add(lblUsername);

        JLabel lblPassword = new JLabel("Salary");
        lblPassword.setBounds(542, 245, 99, 24);
        panel2.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Status");
        lblMobileNumber.setBounds(58, 329, 139, 26);
        panel2.add(lblMobileNumber);

        r1=new JRadioButton("Active");
        r2=new JRadioButton("Deactive");
        r1.setBounds(214,329,100,30);
        r2.setBounds(214,369,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);bg.add(r2);
        panel2.add(r1);
        panel2.add(r2);

        pass = new JTextField();
        pass.setBounds(707, 235, 228, 50);
        panel2.add(pass);


        RButton = new JButton("Add");
        RButton.setBounds(350, 410, 259, 74);
        panel2.add(RButton);
        setSize(1000, 1000);

        RButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastName = lastname.getText();
                emailId = email.getText();
                userName = username.getText();
                mobileNumber ="";
                password = pass.getText();
                if(r1.isSelected()){
                    mobileNumber=r1.getText();
                }else{
                    mobileNumber=r2.getText();
                }


                try {
                    conn = JDBCConfiguration.getDBConnection();

                    String query = "INSERT INTO Job(JobTitle,Location,KeySkill,Salary,Status) values('" + lastName + "','" + emailId + "','" +
                            userName + "','" + password + "','" + mobileNumber  + "')";

                    Statement sta = conn.createStatement();
                    sta.executeUpdate(query);
                    JOptionPane.showMessageDialog(RButton, "Added Successfully");
                    conn.close();
                    dispose();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
