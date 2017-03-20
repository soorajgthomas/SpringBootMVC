package com.sooraj.springboot.persistence.entities.impl;

import com.sooraj.springboot.persistence.entities.construct.*;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by SOORAJ on 04-03-2017.
 */
@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity<Long> {

    @Column(length = 24, nullable = false)
    private String firstName;

    @Column(length = 24, nullable = true)
    private String lastName;

    @JoinColumn(nullable = true, referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country nationality;

    @Column(length = 35, nullable = true)
    private String district;

    @Column(length = 35, nullable = true)
    private String gender;

    @Column(length = 35, nullable = true)
    private String emiratesId;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emiratesIdExpiry;

    @Column(length = 35, nullable = true)
    private String employeeStatus;

    @Column(length = 35, nullable = true)
    private String passportNo;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passportExpiry;

    @Column(length = 35, nullable = true)
    private String healthCard;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date healthCardExpiry;

    @Column(length = 500, nullable = true)
    private String address;

    @Column(length = 500, nullable = true)
    private String address2;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(length = 20, nullable = true)
    private String phone;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfJoin;

    @Column(length = 35, nullable = true)
    private String profession;

    @Column(length = 1, nullable = false)
    private Boolean status;

    @Column()
    private Date createdTime;

    @Column()
    private Date modifiedTime;

    public Employee() {
        super(Employee.class);
    }

    public Employee(Long id) {
        super(Employee.class);
        setId(id);
    }

    public Employee(Employee employee) {
        super(Employee.class);
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.nationality = employee.getNationality();
        this.district = employee.getDistrict();
        this.gender = employee.getGender();
        this.emiratesId = employee.getEmiratesId();
        this.emiratesIdExpiry = employee.getEmiratesIdExpiry();
        this.employeeStatus = employee.getEmployeeStatus();
        this.passportNo = employee.getPassportNo();
        this.passportExpiry = employee.getPassportExpiry();
        this.healthCard = employee.getHealthCard();
        this.healthCardExpiry = employee.getHealthCardExpiry();
        this.address = employee.getAddress();
        this.address2 = employee.getAddress2();
        this.dateOfBirth = employee.getDateOfBirth();
        this.phone = employee.getPhone();
        this.dateOfJoin = employee.getDateOfJoin();
        this.profession = employee.getProfession();
        this.status = employee.getStatus();
        this.createdTime = employee.getCreatedTime();
        this.modifiedTime = employee.getModifiedTime();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmiratesId() {
        return emiratesId;
    }

    public void setEmiratesId(String emiratesId) {
        this.emiratesId = emiratesId;
    }

    public Date getEmiratesIdExpiry() {
        return emiratesIdExpiry;
    }

    public void setEmiratesIdExpiry(Date emiratesIdExpiry) {
        this.emiratesIdExpiry = emiratesIdExpiry;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Date getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(Date passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public Date getHealthCardExpiry() {
        return healthCardExpiry;
    }

    public void setHealthCardExpiry(Date healthCardExpiry) {
        this.healthCardExpiry = healthCardExpiry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
