package Model.Entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
@XmlType(propOrder = { "id", "username", "password" })
public class Employee {

    private static int count = 1;
    private int id;

    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message="Username accepts only letters and numbers")
    @NotNull
    private String username;

    @Size(min = 3, max = 25, message = "Password must be between 3 and 25 characters")
    @NotNull
    private String password;

    public Employee() {
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Employee.count = count;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
