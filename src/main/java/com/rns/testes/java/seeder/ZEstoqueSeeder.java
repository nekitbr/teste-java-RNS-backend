package com.rns.testes.java.seeder;

import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class ZEstoqueSeeder {

    @Autowired
    IEstoqueService estoqueService;
    @Autowired
    IProdutoService produtoService;
    @Autowired
    IFilialService filialService;

    @EventListener
    public void seedEstoque(ContextRefreshedEvent event) {
        try {
            log.info("Criando estoques....");
            criandoEstoques();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void criandoEstoques() {
        List<Produto> produtoList = produtoService.findAll();
        List<Filial> filialList = filialService.findAll();

        for (Produto produto : produtoList) {
            Random random = new Random();

            estoqueService.save(new Estoque(
                produto,
                filialList.get(random.nextInt(filialList.size())),
                random.nextInt(21) + 1
            ));

            // repetir produtos com mesmo id porém em diferentes filiais e quantidades
            if(random.nextInt(100) > 80) {
                estoqueService.save(new Estoque(
                    produto,
                    filialList.get(random.nextInt(filialList.size())),
                    random.nextInt(21) + 1
                ));
            }
        }
    }
}
