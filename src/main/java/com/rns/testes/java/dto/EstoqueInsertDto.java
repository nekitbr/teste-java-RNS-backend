package com.rns.testes.java.dto;

import lombok.Data;

@Data
public class EstoqueInsertDto {
    private Long id;
    private int quantidade;
    private String produtoId;
    private Long filialId;
}
