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
@Table(name = "PROJECT")
public class Service_contract {

    @Id
    @SequenceGenerator(name = "contractSeq", sequenceName = "ZSEQ_CONTRACT_ID", 
                       allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "contractSeq")
    
    @Column(name = "ID")
    private Long id;

    @Basic(optional=true)
    @Column(name = "startDate", length=64)
    private String startDate;

    @Basic(optional=true)
    @Column(name = "endDate", length=64)
    private String endDate;

    @Basic(optional = true)
    @Column(name = "customerId", length = 64)
    private Long customerId;


    public Service_contract(String startDate, String endDate) {
        this.startDate= startDate;
        this.endDate = endDate;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getCustomerID() {
        return this.customerId;
    }

    public void setCustomerID(Long id) {
        this.customerId = id;
    }
}