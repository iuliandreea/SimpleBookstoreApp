package Reports;

import Model.Entities.Book;
import Repository.BookRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFReport implements Report {

    private static int reportNo = 1;
    private BookRepository bookRepository;

    public PDFReport() throws JAXBException, IOException {
        this.bookRepository = new BookRepository();
    }

    @Override
    public void generateReport() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("PDFReport" + reportNo + ".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(5);
            table.addCell("ID");
            table.addCell("TITLE");
            table.addCell("AUTHOR");
            table.addCell("GENRE");
            table.addCell("PRICE");

            writeBooks(table);
            document.add(table);
            document.close();

            reportNo++;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeBooks(PdfPTable table){
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()){
            for(Book book : books){
                if(book.getQuantity() == 0){
                    table.addCell(Integer.toString(book.getId()));
                    table.addCell(book.getTitle());
                    table.addCell(book.getAuthor());
                    table.addCell(book.getGenre().toString());
                    table.addCell(Double.toString(book.getPrice()));
                }
            }
        }
    }
}
