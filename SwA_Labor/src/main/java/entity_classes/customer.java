package entity_classes;

import javax.persistece.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class customer {

    @Id
    @SequenceGenerator(name = "depSeq", sequenceName = "ZSEQ_DEP_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "depSeq")


    @Column(name = "name", length=64)
    private String name;

    @Column(name = "department", length=64)
    private String department;

    @Column(name = "country", length=64)
    private String country;

    @Column(name = "address", length=64)
    private String address;

    @OneToMany(mappedBy="customer") // department: Attribute in Person class
    private List<user> users;
    
// 	The following two lines are optional
//    @OneToMany(mappedBy="department") // department: Attribute in Person class
//    private List<Person> persons;


    public customer() {
    }

    public customer(String name, String department, String country, String address) {
        this.name = name;
        this.department = department;
        this.country = country;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public List<Person> getPersons() {
    	return persons;
    }

}