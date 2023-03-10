package com.rns.testes.java.dto;

import lombok.Data;

@Data
public class EstoqueTransferRequestDto {
    private String produtoId;
    private Integer quantidade;
    private Long filialOrigemId;
    private Long filialDestinoId;
}
