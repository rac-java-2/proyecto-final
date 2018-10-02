package com.models;

import java.time.LocalDate;

public class Inscripcion {
    private Integer id;
    private String firstName;
    private String lastName;
    private String cellphone;
    private Integer courseId;
    private Integer price;
    private LocalDate registrationDate;

    public Inscripcion(String firstName, String lastName, String cellphone, Integer courseId, Integer price, LocalDate registrationDate) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", cellphone=" + cellphone + ", courseId=" + courseId + ", price=" + price + ", registrationDate=" + registrationDate + '}';
    }
}
