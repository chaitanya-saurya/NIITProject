package ViewFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreLogin extends JFrame {
    public JPanel jp;
    public JLabel select,newUser;
    public JButton b1,b2,b3,b4;
    public JComboBox cb1;
    public String Role[]={"HR","PM","EMP"};
    public static String newRole;
   public PreLogin(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        jp = new JPanel();
        setContentPane(jp);
        jp.setLayout(null);

        select=new JLabel("Please select your Designation");
        select.setBounds(175, 5, 200, 40);
        jp.add(select);

        b1 = new JButton("HR");
        b1.setBounds(100, 40, 80, 30);
        jp.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrameHR lfh=new LoginFrameHR();
                lfh.setVisible(true);
                lfh.setSize(400,400);
                dispose();

            }
        });

        b2 = new JButton("PM");
        b2.setBounds(200, 40, 80, 30);
        jp.add(b2);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFramePM lfp=new LoginFramePM();
                lfp.setVisible(true);
                lfp.setSize(400,400);
                dispose();

            }
        });

        b3 = new JButton("EMP");
        b3.setBounds(300, 40, 80, 30);
        jp.add(b3);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrameEMP lfe=new LoginFrameEMP();
                lfe.setVisible(true);
                lfe.setSize(400,400);
                dispose();

            }
        });

        newUser=new JLabel("New User? Select Role:");
        newUser.setBounds(120, 100, 150, 30);
        jp.add(newUser);

        cb1=new JComboBox(Role);
        cb1.setBounds(120,140,100,30);
        jp.add(cb1);

        b4 = new JButton("Register");
        b4.setBounds(120, 200, 100, 30);
        jp.add(b4);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newRole=String.valueOf(cb1.getSelectedItem().toString());
                if(newRole=="HR"){
                    RegisterHR rfh=new RegisterHR();
                    rfh.setVisible(true);
                }
                else if(newRole=="PM"){
                    RegisterPM rfp=new RegisterPM();
                    rfp.setVisible(true);
                }
                else{
                    RegisterEMP rfe=new RegisterEMP();
                    rfe.setVisible(true);
                }

            }
        });
    }
}
