/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package de.hse.swa.jpa.orm.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "PROJECT")
public class Service_contract {

    @Id
    @SequenceGenerator(name = "projectSeq", sequenceName = "ZSEQ_PROJECT_ID", 
                       allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "projectSeq")
    
    @Column(name = "id")
    private Long id;


    @Column(name = "projectname", length=64)
    private String projectname;
    
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "PROJECT_PERSON", 
	          joinColumns = @JoinColumn(name="PROJECT_ID"), 
	          inverseJoinColumns = @JoinColumn(name="PERSON_ID"))
	
	private Set<Customer> persons = new HashSet<>();
	
    public Service_contract() {
    }

    public Service_contract(String projectname) {
        this.projectname = projectname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
    
    public void addPerson(Customer person) {
    	persons.add(person);
        person.getProjects().add(this);
    }
 
    public void removePerson(Customer person) {
//        Iterator<Person> it = persons.iterator();
//        while(it.hasNext()){
//        	Person p = it.next();
//           System.out.println(p.getId() + ": " + p.getUsername());
//        }
        
        persons.remove(person);
        person.getProjects().remove(this);
        
//        it = persons.iterator();
//        while(it.hasNext()){
//        	Person p = it.next();
//           System.out.println(p.getId() + ": " + p.getUsername());
//        }
    }
    
    public Set<Customer> getPersons() {
    	return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service_contract project = (Service_contract) o;
        return Objects.equals(id, project.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}