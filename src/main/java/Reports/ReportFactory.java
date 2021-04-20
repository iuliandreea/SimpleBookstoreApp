package Reports;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ReportFactory {

    public Report createReport(String reportType)  {
        try {
            if(reportType.equalsIgnoreCase("PDF")) {
                return new PDFReport();
            }
            if(reportType.equalsIgnoreCase("CSV")){
                return new CSVReport();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
