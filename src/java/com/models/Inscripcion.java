package com.models;

import java.util.Date;

public class Inscripcion {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cellphone;
    private Integer price;
    private Date registrationDate;
    
    // course id
    private Curso curso;

    public Inscripcion(String firstName, String lastName, String cellphone, Integer price, Date registrationDate, Curso curso) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellphone = cellphone;
        this.price = price;
        this.registrationDate = registrationDate;
        this.curso = curso;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public String getFullname() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", cellphone=" + cellphone + ", price=" + price + ", registrationDate=" + registrationDate + ", curso=" + curso + '}';
    }
    
}
