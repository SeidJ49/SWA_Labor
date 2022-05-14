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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "ZSEQ_PERSON_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "personSeq")
    
    @Column(name = "id")
    private Long id;


    @Column(name = "username", length=64, unique = true)
    private String username;
    
    @Column(name = "props")
    @Lob
    private String properties;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "dep", referencedColumnName="id")
    private Department department;

	
    @ManyToMany(mappedBy = "persons")
	private Set<Project> projects = new HashSet<>();
	
    public Person() {
    }

    public Person(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
