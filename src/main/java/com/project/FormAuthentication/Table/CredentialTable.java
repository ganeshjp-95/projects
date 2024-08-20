package com.project.FormAuthentication.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CredentialTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer sno;
    String userid;
    String password;
    String name;
}
