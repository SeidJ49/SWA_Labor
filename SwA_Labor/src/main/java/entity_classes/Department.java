package entity_classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "USER")
public class user {

    @Id
    @SequenceGenerator(name = "depSeq", sequenceName = "ZSEQ_DEP_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "depSeq")
    
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "login_name", unique=true)
    private String login_name;
    
    @Column(name = "password", length=50)
    private String password;

    @Column(name = "customer", length=64)
    private String customer;

    @Column(name = "is_admin")
    private bool is_admin;

    @Column(name = "phone", unique=true)
    private int phone;

    @Column(name = "mobile", unique=true)
    private int mobile;
    
    public user() {

    }

    public user(String first_name, String last_name, String login_name, String password, bool is_admin, int phone, int mobile) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login_name = login_name;
        this.password = password;
        this.is_admin = is_admin;
        this.phone = phone;
        this.mobile = mobile;
    }

}