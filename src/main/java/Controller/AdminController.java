package Controller;

import Model.Entities.Book;
import Model.Entities.Employee;
import Repository.AdminRepository;
import Repository.BookRepository;
import Repository.EmployeeRepository;
import View.AdminGUI;
import View.AdminInitialGUI;
import Reports.ReportFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController implements ActionListener {

    private AdminInitialGUI adminInitialGUI;
    private AdminGUI adminGUI;

    private BookRepository bookRepository;
    private EmployeeRepository employeeRepository;
    private AdminRepository adminRepository;

    private ReportFactory reportFactory;

    public AdminController() {
        bookRepository = new BookRepository();
        employeeRepository = new EmployeeRepository();
        adminRepository = new AdminRepository();
        reportFactory = new ReportFactory();
    }

    public void setAdminInitialGUI(AdminInitialGUI adminInitialGUI){
        this.adminInitialGUI = adminInitialGUI;
    }

    public void setAdminMainGUI(AdminGUI adminMainGUI){
        this.adminGUI = adminMainGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String validator = "";

        if(adminInitialGUI != null && source == adminInitialGUI.getAdminLogIn()){
            String user = adminInitialGUI.getAdminUser().getText();
            String password = adminInitialGUI.getAdminPassword().getText();
            if(adminRepository.validateLogIn(user, password)){
                openAdminGUI();
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect admin data\n", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        else{
            if(source == adminGUI.getAddBookButton()){
                String title = adminGUI.getAddBookTitle().getText();
                String author =  adminGUI.getAddBookAuthor().getText();
                String genre = adminGUI.getAddBookGenre().getText();
                try{
                    int quantity = Integer.parseInt(adminGUI.getAddBookQuantity().getText());
                    double price = Double.parseDouble(adminGUI.getAddBookPrice().getText());
                    Book book = new Book(title, author, genre, quantity, price);
                    if((validator += bookRepository.validateBook(book)).equals("")){
                        bookRepository.create(book);
                        adminGUI.updateBookTable();
                        adminGUI.viewBookCombo();
                    }
                }catch(NumberFormatException nfe){
                    validator += "Invalid quantity or price\n";
                }
            }
            if(source == adminGUI.getEditBook()){
                int id = adminGUI.getBookComboID();
                String title = adminGUI.getEditBookTitle().getText();
                String author = adminGUI.getEditBookAuthor().getText();
                String genre = adminGUI.getEditBookGenre().getText();
                try{
                    int quantity = Integer.parseInt(adminGUI.getEditBookQuantity().getText());
                    double price = Double.parseDouble(adminGUI.getEditBookPrice().getText());
                    Book book = new Book(id, title, author, genre, quantity, price);
                    if((validator += bookRepository.validateBook(book)).equals("")){
                        bookRepository.update(book);
                        adminGUI.updateBookTable();
                        adminGUI.viewBookCombo();
                    }
                }catch(NumberFormatException nfe){
                   validator += "Invalid quantity or price\n";
                }
            }
            if(source == adminGUI.getDeleteBook()){
                int id = adminGUI.getBookComboID();
                String title = adminGUI.getEditBookTitle().getText();
                String author = adminGUI.getEditBookAuthor().getText();
                String genre = adminGUI.getEditBookGenre().getText();
                int quantity = Integer.parseInt(adminGUI.getEditBookQuantity().getText());
                double price = Double.parseDouble(adminGUI.getEditBookPrice().getText());
                bookRepository.delete(new Book(id, title, author, genre, quantity, price));
                adminGUI.updateBookTable();
                adminGUI.viewBookCombo();
            }
            if(source == adminGUI.getAddEmpl()){
                String username = adminGUI.getAddEmplUser().getText();
                String password = adminGUI.getAddEmplPass().getText();
                Employee empl = new Employee(username, password);
                if((validator += employeeRepository.validateEmployee(empl)).equals("")) {
                    if(employeeRepository.create(empl) == null){
                        validator += "user already exists";
                    }
                    adminGUI.updateEmployeeTable();
                    adminGUI.viewEmployeeCombo();
                }
            }
            if(source == adminGUI.getEditEmpl()){
                int id = adminGUI.getEmployeeComboID();
                String username = adminGUI.getEditEmplUser().getText();
                String password = adminGUI.getEditEmplPass().getText();
                Employee empl = new Employee(id, username, password);
                if((validator += employeeRepository.validateEmployee(empl)).equals("")) {
                    employeeRepository.update(empl);
                    adminGUI.updateEmployeeTable();
                    adminGUI.viewEmployeeCombo();
                }
            }
            if(source == adminGUI.getDeleteEmpl()){
                int id = adminGUI.getEmployeeComboID();
                String username = adminGUI.getEditEmplUser().getText();
                String password = adminGUI.getEditEmplPass().getText();
                employeeRepository.delete(new Employee(id, username, password));
                adminGUI.updateEmployeeTable();
                adminGUI.viewEmployeeCombo();
            }
            if(source == adminGUI.getCsvButton()) {
                reportFactory.createReport("CSV").generateReport();
            }
            if(source == adminGUI.getPdfButton()){
                reportFactory.createReport("PDF").generateReport();
            }

            if(!validator.equals("")){
                JOptionPane.showMessageDialog(null, validator, "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void openAdminGUI(){
        JFrame adminFrame = new JFrame("Administrator");
        adminFrame.setContentPane(new AdminGUI().getPanel1());
        adminFrame.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
        adminFrame.pack();
        adminFrame.setVisible(true);
    }
}
