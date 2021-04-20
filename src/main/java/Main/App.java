package Main;

import View.AdminInitialGUI;
import View.EmployeeInitialGUI;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws JAXBException, IOException {
        JFrame adminFrame = new JFrame("Administrator");
        adminFrame.setContentPane(new AdminInitialGUI().getPanel1());
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setVisible(true);

        JFrame emplFrame = new JFrame("Employee");
        emplFrame.setContentPane(new EmployeeInitialGUI().getPanel1());
        emplFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emplFrame.pack();
        emplFrame.setVisible(true);
    }
}

