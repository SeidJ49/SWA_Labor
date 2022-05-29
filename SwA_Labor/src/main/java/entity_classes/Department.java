package entity_classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @SequenceGenerator(name = "depSeq", sequenceName = "ZSEQ_DEP_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "depSeq")
    
    @Column(name = "id")
    private Long id;


    @Column(name = "depname", length=64, unique = true)
    private String depname;
    
// 	The following two lines are optional
//  @OneToMany(mappedBy="department") // department: Attribute in Person class
//  private List<Person> persons;

    public Department() {
    }

    public Department(String depname) {
        this.depname = depname;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }
    
//    public List<Person> getPersons() {
//    	return persons;
//    }

}