package Controller;

import Model.Entities.Book;
import Repository.BookRepository;
import Repository.EmployeeRepository;
import View.EmployeeGUI;
import View.EmployeeInitialGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController implements ActionListener {

    private EmployeeInitialGUI employeeInitialGUI;
    private EmployeeGUI employeeGUI;

    private BookRepository bookRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeController(){
        bookRepository = new BookRepository();
        employeeRepository = new EmployeeRepository();
    }

    public void setEmployeeInitialGUI(EmployeeInitialGUI employeeInitialGUI){
        this.employeeInitialGUI = employeeInitialGUI;
    }

    public void setEmployeeGUI(EmployeeGUI employeeGUI){
        this.employeeGUI = employeeGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(employeeInitialGUI != null && source == employeeInitialGUI.getEmplLogIn()){
            String user = employeeInitialGUI.getEmplUser().getText();
            String password = employeeInitialGUI.getEmplPassword().getText();
            if(employeeRepository.validateLogIn(user, password)){
                openEmployeeGUI();
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect employee data\n", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        else{
            if(source == employeeGUI.getSellBookButton()){
                int id = employeeGUI.getBookComboID();
                String title = employeeGUI.getSellBookTitle().getText();
                String author = employeeGUI.getSellBookAuthor().getText();
                String genre = employeeGUI.getSellBookGenre().getText();
                int avQuantity = Integer.parseInt(employeeGUI.getSellBookAvQuantity().getText());
                double price = Double.parseDouble(employeeGUI.getSellBookPrice().getText());
                try{
                    int quantity = Integer.parseInt(employeeGUI.getSellBookQuantity().getText());
                    if(quantity > 0 && avQuantity - quantity >= 0){
                        bookRepository.update(new Book(id, title, author, genre, avQuantity - quantity, price));
                        employeeGUI.viewBookTable();
                        employeeGUI.viewBookCombo();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid quantity\n", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Invalid quantity\n", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public void openEmployeeGUI(){
        JFrame adminFrame = new JFrame("Employee");
        adminFrame.setContentPane(new EmployeeGUI().getPanel1());
        adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
        adminFrame.pack();
        adminFrame.setVisible(true);
    }
}
