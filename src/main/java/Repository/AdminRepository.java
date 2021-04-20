package Repository;

import Model.Entities.Admin;
import Model.Entities.Book;
import Model.Lists.BookList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdminRepository {

    private Admin admin;

    public AdminRepository(){
        admin = new Admin();
        try {
            marshal();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void marshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Admin.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(admin, new File("./admin.xml"));
    }

    public Admin unmarshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Admin.class);
        return (Admin) context.createUnmarshaller()
                .unmarshal(new FileReader("./admin.xml"));
    }

    public boolean validateLogIn(String username, String password){
        if(username.equalsIgnoreCase(admin.getUsername()) && password.equalsIgnoreCase(admin.getPassword())){
            return true;
        }
        return false;
    }
}
