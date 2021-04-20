package Repository;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface Repository<T> {

    public T create(T t) throws JAXBException, IOException;
    public List<T> findAll();
    public T findById(int id);
    public T update(T t) throws JAXBException, IOException;
    public T delete(T t) throws JAXBException, IOException;
}
