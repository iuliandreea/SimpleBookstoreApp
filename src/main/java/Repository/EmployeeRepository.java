package Repository;

import Model.Entities.Book;
import Model.Entities.Employee;
import Model.Lists.EmployeeList;

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

public class EmployeeRepository implements Repository<Employee> {

    private static EmployeeList employeeList;

    public EmployeeRepository() {
        try {
            employeeList = unmarshal();
            if(!employeeList.getEmployees().isEmpty()){
                List<Integer> ids = employeeList.getEmployees().stream().map(x -> x.getId()).collect(Collectors.toList());
                Employee.setCount(Collections.max(ids) + 1);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee create(Employee employee) {
        try {
            if(findByUsername(employee.getUsername()) == null){
                List<Employee> employees = employeeList.getEmployees();

                employee.setId(Employee.getCount());
                Employee.setCount(Employee.getCount() + 1);
                employees.add(employee);
                employeeList.setEmployees(employees);
                marshal();

                return employee;
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Employee> findAll() {
        return employeeList.getEmployees();
    }

    public Employee findById(int id) {
        for(Employee employee : employeeList.getEmployees()){
            if(employee.getId() == id){
                return employee;
            }
        }
        return null;
    }

    public Employee findByUsername(String username){
        for(Employee e: employeeList.getEmployees()){
            if(e.getUsername().equalsIgnoreCase(username)){
                return e;
            }
        }
        return null;
    }

    public Employee update(Employee employee) {
        try {
            Employee foundEmployee = this.findById(employee.getId());

            if(foundEmployee != null){
                List<Employee> employees = employeeList.getEmployees();
                employees.remove(foundEmployee);
                employees.add(employee);
                employeeList.setEmployees(employees);
                marshal();

                return employee;
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Employee delete(Employee employee) {
        try {
            Employee foundEmployee = findById(employee.getId());

            if(foundEmployee != null){
                List<Employee> employees = employeeList.getEmployees();
                employees.remove(foundEmployee);
                employeeList.setEmployees(employees);
                marshal();

                return employee;
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void marshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EmployeeList.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(employeeList, new File("./employees.xml"));
    }

    public EmployeeList unmarshal() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(EmployeeList.class);
        if(new File("./employees.xml").exists()){
            return (EmployeeList) context.createUnmarshaller()
                    .unmarshal(new FileReader("./employees.xml"));
        }
        return new EmployeeList();
    }

    public boolean validateLogIn(String username, String password){
        Employee empl = findByUsername(username);
        if(empl != null && password.equalsIgnoreCase(empl.getPassword())){
            return true;
        }
        return false;
    }

    public String validateEmployee(Employee employee) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        String violationMessages = "";
        for (ConstraintViolation<Employee> v : violations) {
            violationMessages += v.getMessage();
            violationMessages += '\n';
        }

        return violationMessages;
    }
}
