package org.gohenry.family.entities;


import javax.persistence.*;
import java.util.List;


@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    @OneToMany(mappedBy="parent", cascade = CascadeType.PERSIST)
    private List<Children> childrens;

    public Parent() {
    }


    public Parent(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Children> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Children> childrens) {
        this.childrens = childrens;
    }
}
