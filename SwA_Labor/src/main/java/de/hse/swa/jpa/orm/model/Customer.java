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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.ArrayList;
import java.util.List;
//import de.hse.swa.jpa.orm.model.*;

@Entity
@Table(name = "Tcustomer")
public class Customer {

    @Id
    @SequenceGenerator(name = "tcustomerSeq", sequenceName = "ZSEQ_TCUSTOMER_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "tcustomerSeq")

    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "username", length = 64, unique = true)
    private String username;

    @Basic(optional = false)
    @Column(name = "password", length = 64, unique = true)
    private String password;

    @Basic(optional = false)
    @Column(name = "role", length = 64, unique = true)
    private String role;

    @Basic(optional = true)
    @Column(name = "firstname", length = 64)
    private String firstname;

    @Basic(optional = true)
    @Column(name = "lastname", length = 64)
    private String lastname;

    @Basic(optional = true)
    @Column(name = "email", length = 64)
    private String email;

    @Basic(optional = true)
    @Column(name = "phone", length = 64)
    private String phone;

    @Basic(optional = true)
    @Column(name = "mobile", length = 64)
    private String mobile;

    @Basic(optional = false)
    @Column(name = "departmentId", length = 64)
    private Long departmentId;

    @Transient
    private List<Service_contract> allContracts;

    public Customer() {
        this.allContracts = new ArrayList<>();
    }

    public Customer(String username, String password, String role, String firstname, String lastname, String email,
            String phone, String mobile, Long departmentId, List<Service_contract> allContracts) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.departmentId = departmentId;
        this.allContracts = allContracts;
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

    public String getPassword() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String name) {
        this.lastname = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long id) {
        this.departmentId = id;
    }

    public List<Service_contract> getAllContracts() {
        return allContracts;
    }

    public void setAllContracts(List<Service_contract> allContracts) {
        this.allContracts = allContracts;
    }
}
