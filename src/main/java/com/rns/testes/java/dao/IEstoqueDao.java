package com.rns.testes.java.dao;

import com.rns.testes.java.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IEstoqueDao extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoIdAndFilialId(String produtoId, Long filialId);
    List<Estoque> findByProdutoId(String produtoId);

}
