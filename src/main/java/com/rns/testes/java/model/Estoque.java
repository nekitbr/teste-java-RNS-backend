package com.rns.testes.java.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ESTOQUE")
@Data
public class Estoque extends GenericEntity<Long> {

    public Estoque() {}
    public Estoque(Produto produto, Filial filial, int quantidade) {
        this.produto = produto;
        this.filial = filial;
        this.quantidade = quantidade;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantidade;

    @ManyToOne
    @JoinColumn
    private Produto produto;

    @ManyToOne
    @JoinColumn
    private Filial filial;
}
