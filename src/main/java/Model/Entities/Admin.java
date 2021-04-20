package Model.Entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "admin")
@XmlType(propOrder = { "id", "username", "password" })
public class Admin {

    private static final int id = 1;

    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z]*$")
    @NotNull
    private static final String username = "admin";

    @Size(min = 3, max = 25, message = "Password must be between 3 and 25 characters")
    @NotNull
    private static final String password = "password";

    public Admin() {
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

}
