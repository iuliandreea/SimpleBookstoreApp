package View;

import Controller.AdminController;
import Controller.EmployeeController;
import Model.Entities.Book;
import Model.Entities.Employee;
import Repository.BookRepository;
import Repository.EmployeeRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;

public class AdminGUI {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTable bookTable;
    private JTable emplTable;
    private JButton csvButton;
    private JButton pdfButton;
    private JTextField addBookTitle;
    private JTextField addBookAuthor;
    private JTextField addBookGenre;
    private JTextField addBookQuantity;
    private JTextField addBookPrice;
    private JButton addBookButton;
    private JTextField editEmplUser;
    private JTextField editEmplPass;
    private JComboBox employeeCombo;
    private JTextField addEmplUser;
    private JTextField addEmplPass;
    private JButton addEmpl;
    private JTextField editBookTitle;
    private JTextField editBookAuthor;
    private JTextField editBookGenre;
    private JTextField editBookQuantity;
    private JTextField editBookPrice;
    private JButton editEmpl;
    private JButton deleteEmpl;
    private JComboBox bookCombo;
    private JButton editBook;
    private JButton deleteBook;

    AdminController adminController = new AdminController();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    BookRepository bookRepository = new BookRepository();

    private int employeeComboID;
    private int bookComboID;

    public AdminGUI() {
        adminController.setAdminMainGUI(this);

        updateBookTable();
        updateEmployeeTable();

        viewBookCombo();
        viewEmployeeCombo();

        addBookButton.addActionListener(adminController);
        bookCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(bookCombo.getSelectedIndex() != -1){
                    bookComboID = Integer.parseInt(bookCombo.getSelectedItem().toString());
                    Book book = bookRepository.findById(bookComboID);
                    editBookTitle.setText(book.getTitle());
                    editBookAuthor.setText(book.getAuthor());
                    editBookGenre.setText(book.getGenre());
                    editBookQuantity.setText(String.valueOf(book.getQuantity()));
                    editBookPrice.setText(String.valueOf(book.getPrice()));
                }
            }
        });
        editBook.addActionListener(adminController);
        deleteBook.addActionListener(adminController);
        addEmpl.addActionListener(adminController);
        employeeCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(employeeCombo.getSelectedIndex() != -1){
                    employeeComboID = Integer.parseInt(employeeCombo.getSelectedItem().toString());
                    Employee empl = employeeRepository.findById(employeeComboID);
                    editEmplUser.setText(empl.getUsername());
                    editEmplPass.setText(empl.getPassword());
                }
            }
        });
        editEmpl.addActionListener(adminController);
        deleteEmpl.addActionListener(adminController);
        csvButton.addActionListener(adminController);
        pdfButton.addActionListener(adminController);
    }

    public void updateBookTable(){
        List<Book> books = bookRepository.findAll();

        if(books != null && !books.isEmpty()){
            DefaultTableModel model = new DefaultTableModel(null, new String[] {"ID", "Title", "Author", "Genre", "Quantity", "Price"});
            String[] tableContent = new String[6];
            Iterator<Book> it = books.iterator();

            while(it.hasNext()){
                Book book = it.next();

                tableContent[0] = String.valueOf(book.getId());
                tableContent[1] = book.getTitle();
                tableContent[2] = book.getAuthor();
                tableContent[3] = book.getGenre();
                tableContent[4] = String.valueOf(book.getQuantity());
                tableContent[5] = String.valueOf(book.getPrice());
                model.addRow(tableContent);
            }

            bookTable.setModel(model);
        }
    }

    public void updateEmployeeTable(){
        List<Employee> employees = employeeRepository.findAll();
        if(employees != null && !employees.isEmpty()){
            DefaultTableModel model = new DefaultTableModel(null, new String[] {"ID", "Username", "Password"});
            String[] tableContent = new String[3];
            Iterator<Employee> it = employees.iterator();

            while(it.hasNext()){
                Employee employee = it.next();
                tableContent[0] = String.valueOf(employee.getId());
                tableContent[1] = employee.getUsername();
                tableContent[2] = employee.getPassword();
                model.addRow(tableContent);
            }

            emplTable.setModel(model);
        }
    }

    public void viewBookCombo(){
        bookCombo.removeAllItems();
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()){
            Iterator<Book> it = books.iterator();
            while (it.hasNext()) {
                Book book = it.next();
                bookCombo.addItem(book.getId());
            }
        }
    }

    public void viewEmployeeCombo(){
        employeeCombo.removeAllItems();
        List<Employee> employees = employeeRepository.findAll();
        if(!employees.isEmpty()){
            Iterator<Employee> it = employees.iterator();
            while (it.hasNext()) {
                Employee employee = it.next();
                employeeCombo.addItem(employee.getId());
            }
        }
    }

    public JTextField getAddBookTitle(){
        return addBookTitle;
    }

    public JTextField getAddBookAuthor(){
        return addBookAuthor;
    }

    public JTextField getAddBookGenre(){
        return addBookGenre;
    }

    public JTextField getAddBookQuantity(){
        return addBookQuantity;
    }

    public JTextField getAddBookPrice(){
        return addBookPrice;
    }

    public JTextField getEditEmplUser(){
        return editEmplUser;
    }

    public JTextField getEditEmplPass(){
        return editEmplPass;
    }

    public JTextField getAddEmplUser(){
        return addEmplUser;
    }

    public JTextField getAddEmplPass(){
        return addEmplPass;
    }

    public JTextField getEditBookTitle(){
        return editBookTitle;
    }

    public JTextField getEditBookAuthor(){
        return editBookAuthor;
    }

    public JTextField getEditBookGenre(){
        return editBookGenre;
    }

    public JTextField getEditBookQuantity(){
        return editBookQuantity;
    }

    public JTextField getEditBookPrice(){
        return editBookPrice;
    }

    public JButton getCsvButton(){
        return csvButton;
    }

    public JButton getPdfButton(){
        return pdfButton;
    }

    public JButton getAddBookButton(){
        return addBookButton;
    }

    public JButton getAddEmpl(){
        return addEmpl;
    }

    public JButton getEditEmpl(){
        return editEmpl;
    }

    public JButton getDeleteEmpl(){
        return deleteEmpl;
    }

    public JButton getEditBook(){
        return editBook;
    }

    public JButton getDeleteBook(){
        return deleteBook;
    }

    public JPanel getPanel1(){
        return panel1;
    }

    public int getEmployeeComboID(){
        return employeeComboID;
    }

    public int getBookComboID(){
        return bookComboID;
    }
}
