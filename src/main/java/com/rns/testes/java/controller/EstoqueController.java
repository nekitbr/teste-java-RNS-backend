package com.rns.testes.java.controller;

import com.rns.testes.java.dto.EstoqueDto;
import com.rns.testes.java.dto.EstoqueInsertDto;
import com.rns.testes.java.dto.EstoqueTransferRequestDto;
import com.rns.testes.java.dto.mapper.EstoqueMapper;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    IEstoqueService estoqueService;
    @Autowired
    IProdutoService produtoService;
    @Autowired
    IFilialService filialService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Estoque>> findAll() {
        return ResponseEntity.ok(
            estoqueService.findAll()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Estoque> findById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(
            estoqueService.findById(id)
        );
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Estoque> update(
        @RequestBody EstoqueDto dto
    ) {
        return ResponseEntity.ok(
            estoqueService.update(
                EstoqueMapper.INSTANCE.dtoToEntity(dto)
            )
        );
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Estoque> insert(
        @RequestBody EstoqueInsertDto dto
    ) {
        Produto produto = produtoService.findById(dto.getProdutoId());
        Filial filial = filialService.findById(dto.getFilialId());

        Estoque estoque = new Estoque(produto, filial, dto.getQuantidade());

        return ResponseEntity.ok(
            estoqueService.save(estoque)
        );
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(
        @PathVariable Long id
    ) {
        estoqueService.delete(id);
    }

    @PostMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void transfer(
        @RequestBody EstoqueTransferRequestDto dto
    ) {
        estoqueService.transferir(
            dto.getProdutoId(),
            dto.getQuantidade(),
            dto.getFilialOrigemId(),
            dto.getFilialDestinoId()
        );
    }
}
