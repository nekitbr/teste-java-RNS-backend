package com.rns.testes.java.service;

import com.rns.testes.java.model.Estoque;

public interface IEstoqueService extends IGenericService<Estoque, Long> {

    void transferir(String produtoId, int quantidade, Long filialOrigemId, Long filialDestinoId);
    int getQuantidade(String produtoId);
    int getQuantidade(String produtoId, Long filialId);

}
