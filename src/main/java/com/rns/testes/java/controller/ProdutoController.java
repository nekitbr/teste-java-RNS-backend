package com.rns.testes.java.controller;

import com.rns.testes.java.dto.ProdutoDto;
import com.rns.testes.java.dto.mapper.ProdutoMapper;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    IProdutoService produtoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(
            produtoService.findAll()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Produto> findById(
        @PathVariable String id
    ) {
        return ResponseEntity.ok(
            produtoService.findById(id)
        );
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Produto> update(
        @RequestBody ProdutoDto dto
    ) {
        return ResponseEntity.ok(
            produtoService.update(
                ProdutoMapper.INSTANCE.dtoToEntity(dto)
            )
        );
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Produto> insert(
        @RequestBody ProdutoDto dto
    ) {
        return ResponseEntity.ok(
            produtoService.save(
                ProdutoMapper.INSTANCE.dtoToEntity(dto)
            )
        );
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(
        @PathVariable String id
    ) {
        produtoService.delete(id);
    }

}
