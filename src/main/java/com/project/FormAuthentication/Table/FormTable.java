package com.project.FormAuthentication.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Setter
@Entity
public class FormTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer sno;
    String name;
    int employeeid;
    String role;
    String organisation;
    double salary;
    int age;
    String userid;

public String toString(){
    return "Sno"+"-"+sno+", "+
            "Name"+"-"+name+", "+
            "Employee ID"+"-"+employeeid+", "+
            "Role"+"-"+role+", "+
            "Organisation"+"-"+organisation+", "+
            "Salary"+"-"+(int)salary+", "+
            "Age"+"-"+age;
}
}
