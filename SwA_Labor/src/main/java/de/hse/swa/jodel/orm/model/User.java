/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package de.hse.swa.jodel.orm.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the TUSER database table.
 * 
 */
@Entity
@Table(name = "Tuser")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @SequenceGenerator(name = "tuserSeq", sequenceName = "ZSEQ_TUSER_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "tuserSeq")
    
    @Column(name = "ID")
    private Long id;

    @Basic(optional=false)
    @Column(name = "username", length=64, unique = true)
    private String username;
    
    @Basic(optional=true)
    @Column(name = "password", length=64)
    private String password;

	public User() {
	}

	public User(String name) {
		this.username = name;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String name) {
		this.username = name;
	}
	

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}