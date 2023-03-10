package com.rns.testes.java.service.impl;

import com.rns.testes.java.dao.IEstoqueDao;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService extends AbstractGenericServicePersistence<IEstoqueDao, Estoque, Long> implements IEstoqueService {

    @Autowired
    private IEstoqueDao estoqueDao;
    @Autowired
    private IFilialService filialService;
    @Autowired
    private IProdutoService produtoService;

    public void transferir(String produtoId, int quantidade, Long filialOrigemId, Long filialDestinoId) {
        Optional<Estoque> origem = estoqueDao.findByProdutoIdAndFilialId(produtoId, filialOrigemId);

        if (!origem.isPresent()) {
            throw new IllegalArgumentException("Não foi possível encontrar o estoque da origem.");
        }

        Estoque estoqueOrigem = origem.get();

        if (quantidade > origem.get().getQuantidade()) {
            throw new IllegalArgumentException("A quantidade a ser transferida é maior do que a quantidade disponível na origem.");
        }

        Estoque estoqueDestino = estoqueDao.findByProdutoIdAndFilialId(produtoId, filialDestinoId)
            .orElse(new Estoque(
                produtoService.findById(produtoId),
                filialService.findById(filialDestinoId),
                0
            ));

        estoqueOrigem.setQuantidade(estoqueOrigem.getQuantidade() - quantidade);
        estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + quantidade);

        this.save(estoqueOrigem);
        this.save(estoqueDestino);
    }

    public int getQuantidade(String produtoId) {
        List<Estoque> estoqueList = estoqueDao.findByProdutoId(produtoId);

        return estoqueList.stream()
                   .mapToInt(Estoque::getQuantidade)
                   .sum();
    }

    public int getQuantidade(String produtoId, Long filialId) {
        return estoqueDao.findByProdutoIdAndFilialId(produtoId, filialId)
                   .map(Estoque::getQuantidade)
                   .orElse(0);
    }
}
