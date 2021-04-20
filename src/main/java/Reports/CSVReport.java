package Reports;

import Model.Entities.Book;
import Repository.BookRepository;
import com.opencsv.CSVWriter;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReport implements Report {

    private static int reportNo = 1;
    private BookRepository bookRepository;

    public CSVReport() throws JAXBException, IOException {
        this.bookRepository = new BookRepository();
    }

    @Override
    public void generateReport() {
        try{
            File file = new File("CSVReport" + reportNo + ".csv");
            FileWriter out = new FileWriter(file);
            CSVWriter csv = new CSVWriter(out);

            String[] header = { "ID", "TITLE", "AUTHOR", "GENRE", "PRICE" };
            csv.writeNext(header);

            writeBooks(csv);
            csv.close();

            reportNo++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBooks(CSVWriter csv){
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()){
            List<String[]> data =new ArrayList<String[]>();

            for(Book book : books){
                if(book.getQuantity() == 0){
                    String[] bookData = { Integer.toString(book.getId()), book.getTitle(), book.getAuthor(),
                            book.getGenre().toString(), Double.toString(book.getPrice()) };
                    data.add(bookData);
                }
            }

            csv.writeAll(data);
        }
    }
}
