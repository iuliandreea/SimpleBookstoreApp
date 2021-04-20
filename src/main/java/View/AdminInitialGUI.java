package View;

import Controller.AdminController;
import Controller.EmployeeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminInitialGUI {


    private JPanel panel1;
    private JTextField adminUser;
    private JTextField adminPassword;
    private JButton adminLogIn;

    AdminController adminController = new AdminController();

    public AdminInitialGUI() {
        adminController.setAdminInitialGUI(this);
        adminLogIn.addActionListener(adminController);
    }

    public JTextField getAdminUser(){
        return adminUser;
    }

    public JTextField getAdminPassword(){
        return adminPassword;
    }

    public JButton getAdminLogIn(){
        return adminLogIn;
    }

    public JPanel getPanel1(){
        return panel1;
    }
}
