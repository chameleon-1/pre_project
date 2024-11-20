package com.new2310.preproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@Data
@Table(name = "ClientsINFO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})          //исправляет ошибку 500, но почему?
public class ClientsINFO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;                                                     //не работает со стрингой

    @Column(name = "name")
    private String name;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private Date birthday;
}
