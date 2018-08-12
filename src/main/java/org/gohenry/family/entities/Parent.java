package org.gohenry.family.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by c0234499 on 09/08/2018.
 */


@Getter
@Setter
@Entity
public class Parent  {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    @OneToMany(mappedBy="parent", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Children> childrens;

    public Parent(){

        childrens = new ArrayList<Children>();
    }
    public Parent(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        childrens = new ArrayList<Children>();
    }

    public Parent(String name, String surname, Integer age, List<Children> childrens) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.childrens = childrens;
    }

    public List<Children> getChildrens() {
        return childrens;
    }

}
