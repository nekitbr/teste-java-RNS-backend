package com.rns.testes.java.dto;

import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import lombok.Data;

@Data
public class EstoqueDto {
    private Long id;
    private Integer quantidade;
    private Produto produto;
    private Filial filial;
}
