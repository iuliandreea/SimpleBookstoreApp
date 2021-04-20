package Model.Entities;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "title", "author", "genre", "quantity", "price" })
public class Book {

    private static int count = 1;
    private int id;

    @Size(min = 3, max = 25, message = "Title must be between 3 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message="Title accepts only letters and whitespaces")
    @NotNull
    private String title;

    @Size(min = 3, max = 25, message = "Author must be between 3 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message="Author accepts only letters and whitespaces")
    @NotNull
    private String author;

    @Size(min = 3, max = 25, message = "Genre must be between 3 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message="Genre accepts only letters and whitespaces")
    @NotNull
    private String genre;

    @PositiveOrZero(message = "Quantity cannot be negative")
    @NotNull
    private int quantity;

    @Positive(message = "Price cannot be negative or 0")
    @NotNull
    private double price;

    public Book() {
    }

    public Book(String title, String author, String genre, int quantity, double price){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public Book(int id, String title, String author, String genre, int quantity, double price){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Book.count = count;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
