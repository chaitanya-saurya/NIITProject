package ViewFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logout extends JFrame{
    public JButton ReLogin,Close;
    public JPanel panel;
    public JLabel label1;
    public Logout()
    {
        label1 = new JLabel();
        label1.setText("You have been Logged out suceessfully! ");
        label1.setBounds(10,10,300,30);



        ReLogin=new JButton("Re-Login");
        ReLogin.setBounds(10,60,100,30);
        Close=new JButton("Close");
        Close.setBounds(120,60,100,30);
        panel=new JPanel(null);
        panel.add(label1);

        panel.add(Close);
        panel.add(ReLogin);
        add(panel,BorderLayout.CENTER);
        setTitle("Logout Window");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ReLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PreLogin pl=new PreLogin();
                pl.setVisible(true);
                dispose();
            }
        });
        Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

    }
}

