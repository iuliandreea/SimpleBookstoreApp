package View;

import Controller.EmployeeController;
import Model.Entities.Book;
import Repository.BookRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeGUI {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField sellBookTitle;
    private JTextField sellBookAuthor;
    private JTextField sellBookGenre;
    private JTextField sellBookAvQuantity;
    private JTextField sellBookPrice;
    private JTextField sellBookQuantity;
    private JButton sellBookButton;
    private JComboBox sellBookCombo;
    private JTextField searchTitle;
    private JButton searchButton;
    private JTable bookTable;
    private JTextField searchAuthor;
    private JTextField searchGenre;

    EmployeeController employeeController = new EmployeeController();
    BookRepository bookRepository = new BookRepository();

    private int bookComboID;

    public EmployeeGUI() {

        employeeController.setEmployeeGUI(this);
        viewBookTable();
        viewBookCombo();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBookTable();
            }
        });
        sellBookCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(sellBookCombo.getSelectedIndex() != -1){
                    bookComboID = Integer.parseInt(sellBookCombo.getSelectedItem().toString());
                    Book book = bookRepository.findById(bookComboID);
                    sellBookTitle.setText(book.getTitle());
                    sellBookAuthor.setText(book.getAuthor());
                    sellBookGenre.setText(book.getGenre());
                    sellBookAvQuantity.setText(String.valueOf(book.getQuantity()));
                    sellBookPrice.setText(String.valueOf(book.getPrice()));
                }
            }
        });
        sellBookButton.addActionListener(employeeController);
    }

    public void viewBookTable(){
        List<Book> books = bookRepository.findAll();

        if(searchTitle != null && !searchTitle.getText().equals("")){
            books = books.stream()
                    .filter(x -> x.getTitle().toLowerCase().contains(searchTitle.getText().toLowerCase()))
                    .collect(Collectors.toList());
        }
        if(searchAuthor != null && !searchAuthor.getText().equals("")){
            books = books.stream()
                    .filter(x -> x.getAuthor().toLowerCase().contains(searchAuthor.getText().toLowerCase()))
                    .collect(Collectors.toList());
        }
        if(searchGenre != null && !searchGenre.getText().equals("")){
            books = books.stream()
                    .filter(x -> x.getGenre().toLowerCase().contains(searchGenre.getText().toLowerCase()))
                    .collect(Collectors.toList());
        }

        if(!books.isEmpty()){
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
        else{
            DefaultTableModel model = new DefaultTableModel(null, new String[] {"ID", "Title", "Author", "Genre", "Quantity", "Price"});
            bookTable.setModel(model);
        }
    }

    public void viewBookCombo(){
        sellBookCombo.removeAllItems();
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()){
            Iterator<Book> it = books.iterator();
            while (it.hasNext()) {
                Book book = it.next();
                sellBookCombo.addItem(book.getId());
            }
        }
    }

    public JTextField getSellBookQuantity(){
        return sellBookQuantity;
    }

    public JTextField getSearchTitle(){
        return searchTitle;
    }

    public JTextField getSearchAuthor(){
        return searchAuthor;
    }

    public JTextField getSearchGenre(){
        return searchGenre;
    }

    public JTextField getSellBookTitle(){
        return sellBookTitle;
    }

    public JTextField getSellBookAuthor(){
        return sellBookAuthor;
    }

    public JTextField getSellBookGenre(){
        return sellBookGenre;
    }

    public JTextField getSellBookAvQuantity(){
        return sellBookAvQuantity;
    }

    public JTextField getSellBookPrice(){
        return sellBookPrice;
    }

    public JButton getSellBookButton(){
        return sellBookButton;
    }

    public JButton getSearchButton(){
        return searchButton;
    }

    public JPanel getPanel1(){
        return panel1;
    }

    public int getBookComboID(){
        return bookComboID;
    }
}
