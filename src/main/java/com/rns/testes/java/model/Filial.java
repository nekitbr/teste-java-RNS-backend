package com.rns.testes.java.model;

import com.rns.testes.java.enums.EnumTipoFilial;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;

@Entity
@Table(name = "FILIAL")
@SequenceGenerator(name = "FILIAL_SEQ", sequenceName = "FILIAL_SEQ", allocationSize = 1)
@Data
public class Filial extends GenericEntity<Long> {

    @Id
    @GeneratedValue(generator = "FILIAL_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String razaoSocial;

    @CNPJ
    @Column(nullable = false)
    private String cnpj;

    @Column
    private String endereco;

    @Column(nullable = false)
    private EnumTipoFilial tipoFilial;

}
