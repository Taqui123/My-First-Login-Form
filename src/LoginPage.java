import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage extends JDialog {
    private JPanel loginPanel;
    private JTextField tfEmail;             
    private JPasswordField tfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JLabel helloLabel;
    Connection con;
    Statement st;
    ResultSet rs;
    String uname,pass;

    public LoginPage(JFrame parent){

        super(parent);

        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(650,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
        } catch (SQLException ignored) {}

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = tfEmail.getText();
                String password = String.valueOf(tfPassword.getPassword());

                try {
                    st = con.createStatement();
                    rs = st.executeQuery("select * from admin;");

                    rs.next();
                    {
                        uname = rs.getString("User_name");
                        pass = rs.getString("password");

                        if (email.equals(uname) && password.equals(pass))
                        {
                            JOptionPane.showMessageDialog(null, "Welcome Back dear USER:)>>>");
                        } else
                            JOptionPane.showMessageDialog(null, "Invalid Authentication!!!\nplease Enter " +
                                    "correct credentials...");

                    }
                } catch (SQLException ignored){}


            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        LoginPage lfg = new LoginPage(null);
    }

}
