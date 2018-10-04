package com.models;

import java.text.DecimalFormat;
import java.util.Date;

public class Inscripcion {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cellphone;
    private Integer courseId;
    private Double price;
    private Date registrationDate;

    public Inscripcion(String firstName, String lastName, String cellphone, Integer courseId, Double price, Date registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellphone = cellphone;
        this.courseId = courseId;
        this.price = price;
        this.registrationDate = registrationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public String getFullname() {
        return firstName + " " + lastName;
    }
    
    public String getFormatPrice() {
        DecimalFormat formatNumber = new DecimalFormat("S/ #,###.00");

        return formatNumber.format(price);
    }
}
