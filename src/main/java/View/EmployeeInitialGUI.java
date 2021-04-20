package View;

import Controller.EmployeeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeInitialGUI {
    private JPanel panel1;
    private JTextField emplUser;
    private JTextField emplPassword;
    private JButton emplLogIn;

    EmployeeController employeeController = new EmployeeController();

    public EmployeeInitialGUI() {
        employeeController.setEmployeeInitialGUI(this);
        emplLogIn.addActionListener(employeeController);
    }

    public JTextField getEmplUser(){
        return emplUser;
    }

    public JTextField getEmplPassword(){
        return emplPassword;
    }

    public JButton getEmplLogIn(){
        return emplLogIn;
    }

    public JPanel getPanel1(){
        return panel1;
    }
}
