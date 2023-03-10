package com.rns.testes.java.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO")
@Data
public class Produto extends GenericEntity<String>{

    @Id
    private String id;

    @Column(nullable = false)
    private String nome;

}
