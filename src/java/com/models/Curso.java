package com.models;

public class Curso {
    private Integer id;
    private String description;

    public Curso(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", description=" + description + '}';
    }

    
}
