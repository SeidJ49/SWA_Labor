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


@Entity
@Table(name = "Tdepartment")
public class Department {
    @Id
    @SequenceGenerator(name = "tdepSeq", sequenceName = "ZSEQ_TDEP_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "tdepSeq")
    
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "departmentname", length=64, unique = true)
    private String departmentname;

    @Basic(optional = true)
    @Column(name = "address", length = 64)
    private String address;

    @Basic(optional = true)
    @Column(name = "addressDetails", length = 64)
    private String addressDetails;

    public Department() {
    }

    public Department(String departmentname, String address, String addressDetails) {
        this.departmentname = departmentname;
        this.address = address;
        this.addressDetails = addressDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepname() {
        return departmentname;
    }

    public void setDepname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }
}