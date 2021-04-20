package Repository;

import Model.Entities.Book;
import Model.Entities.Genre;
import Model.Lists.BookList;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository implements Repository<Book>{

    private static BookList bookList;

    public BookRepository() {
        try {
            this.bookList = this.unmarshal();
            if(!bookList.getBooks().isEmpty()){
                List<Integer> ids = bookList.getBooks().stream().map(x -> x.getId()).collect(Collectors.toList());
                Book.setCount(Collections.max(ids) + 1);
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Book create(Book book) {
        try {
            List<Book> books = bookList.getBooks();

            book.setId(Book.getCount());
            Book.setCount(Book.getCount() + 1);
            books.add(book);
            bookList.setBooks(books);
            marshal();

            return book;
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Book> findAll(){
        return bookList.getBooks();
    }

    public Book findById(int id) {
        for(Book book : bookList.getBooks()){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    public List<Book> findByGenre(String genre){
        List<Book> genreBooks = new ArrayList<Book>();
        for(Book book : bookList.getBooks()){
            if(book.getGenre().equalsIgnoreCase(genre)){
                genreBooks.add(book);
            }
        }
        return genreBooks;
    }

    public List<Book> findByTitle(String title){
        List<Book> titleBooks = new ArrayList<Book>();
        for(Book book : bookList.getBooks()){
            if(book.getTitle().equalsIgnoreCase(title)){
                titleBooks.add(book);
            }
        }
        return titleBooks;
    }

    public List<Book> findByAuthor(String author){
        List<Book> authorBooks = new ArrayList<Book>();
        for(Book book : bookList.getBooks()){
            if(book.getAuthor().equalsIgnoreCase(author)){
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public Book update(Book book) {
        try {
            Book foundBook = this.findById(book.getId());

            if(foundBook != null){
                List<Book> books = bookList.getBooks();
                books.remove(foundBook);
                books.add(book);
                bookList.setBooks(books);
                marshal();

                return book;
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Book delete(Book book) {
        try {
            Book foundBook = findById(book.getId());
            if(foundBook != null){
                List<Book> books = bookList.getBooks();
                books.remove(foundBook);
                bookList.setBooks(books);
                marshal();

                return book;
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void marshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(BookList.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(bookList, new File("./books.xml"));
    }

    public BookList unmarshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(BookList.class);
        if(new File("./books.xml").exists()){
            return (BookList) context.createUnmarshaller()
                    .unmarshal(new FileReader("./books.xml"));
        }
        return new BookList();
    }

    public String validateBook(Book book){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        String violationMessages = "";
        for(ConstraintViolation<Book> v : violations){
            violationMessages += (v.getMessage());
            violationMessages += '\n';
        }

        return violationMessages;
    }
}
