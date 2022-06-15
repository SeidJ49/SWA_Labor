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

import java.util.List;
import java.util.ArrayList;
import de.hse.swa.jpa.orm.model.*;

@Entity
@Table(name = "Tcontract")
public class Service_contract {

    @Id
    @SequenceGenerator(name = "tcontractSeq", sequenceName = "ZSEQ_TCONTRACT_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "tcontractSeq")

    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = true)
    @Column(name = "departmentID", length = 64)
    private Long departmentID;

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    @Basic(optional = true)
    @Column(name = "holderID", length = 64)
    private Long holderID;

    public Long getHolderID() {
        return holderID;
    }

    public void setHolderID(Long holderID) {
        this.holderID = holderID;
    }

    @Basic(optional = true)
    @Column(name = "customerID", length = 64)
    private Long customerID;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    @Basic(optional = true)
    @Column(name = "secCustomerID", length = 64)
    private Long secCustomerID;

    public Long getSecCustomerID() {
        return secCustomerID;
    }

    public void setSecCustomerID(Long secCustomerID) {
        this.secCustomerID = secCustomerID;
    }

    @Basic(optional = true)
    @Column(name = "version", length = 64)
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic(optional = true)
    @Column(name = "startDate", length = 64)
    private String startDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic(optional = true)
    @Column(name = "endDate", length = 64)
    private String endDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Basic(optional = true)
    @Column(name = "firstIP", length = 64)
    private String firstIP;

    public String getFirstIP() {
        return firstIP;
    }

    public void setFirstIP(String firstIP) {
        this.firstIP = firstIP;
    }

    @Basic(optional = true)
    @Column(name = "secondIP", length = 64)
    private String secondIP;

    public String getSecondIP() {
        return secondIP;
    }

    public void setSecondIP(String secondIP) {
        this.secondIP = secondIP;
    }

    @Basic(optional = true)
    @Column(name = "ipSech", length = 64)
    private String ipSech;

    public String getIpSech() {
        return ipSech;
    }

    public void setIpSech(String ipSech) {
        this.ipSech = ipSech;
    }

    @Basic(optional = true)
    @Column(name = "responsable", length = 64)
    private String responsable;

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Basic(optional = true)
    @Column(name = "secondResponsable", length = 64)
    private String secondResponsable;

    public String getSecondResponsable() {
        return secondResponsable;
    }

    public void setSecondResponsable(String secondResponsable) {
        this.secondResponsable = secondResponsable;
    }

    @javax.persistence.Transient
    private List<String> allUsers;

    public List<String> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<String> allUsers) {
        this.allUsers = allUsers;
    }

    public Service_contract() {
        this.version = "1.0.0";
        this.allUsers = new ArrayList<>();
    }

    public Service_contract(Long depID, Long holderID, Long customerID, Long secCustomerID, String version,
            String startDate, String endDate, String firstIP, String secondIP, String ipSech, String responsable,
            String secResponsable, List<String> allUsers) {
        this.departmentID = depID;
        this.holderID = holderID;
        this.customerID = customerID;
        this.secCustomerID = secCustomerID;
        this.version = version;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstIP = firstIP;
        this.secondIP = secondIP;
        this.ipSech = ipSech;
        this.responsable = responsable;
        this.secondResponsable = secResponsable;
        this.allUsers = allUsers;
    }
}