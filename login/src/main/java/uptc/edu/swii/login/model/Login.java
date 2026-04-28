package uptc.edu.swii.login.model;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerid")
    private String customerid;

    @Column(name = "password")
    private String password;

    public Login(){}

    public Login(Long id, String customerid, String password) {
        this.id = id;
        this.customerid = customerid;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerid() { return customerid; }
    public void setCustomerid(String customerid) { this.customerid = customerid; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}