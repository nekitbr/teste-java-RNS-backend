package com.rns.testes.java.controller;

import com.rns.testes.java.dto.FilialDto;
import com.rns.testes.java.dto.mapper.FilialMapper;
import com.rns.testes.java.enums.EnumTipoFilial;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.IFilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/filiais")
public class FilialController {

    @Autowired
    IFilialService filialService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Filial>> findAll() {
        return ResponseEntity.ok(
            filialService.findAll()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Filial> findById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(
            filialService.findById(id)
        );
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Filial> update(
        @RequestBody FilialDto dto
    ) {
        return ResponseEntity.ok(
            filialService.update(
                FilialMapper.INSTANCE.dtoToEntity(dto)
            )
        );
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Filial> insert(
        @RequestBody FilialDto dto
    ) {
        return ResponseEntity.ok(
            filialService.save(
                FilialMapper.INSTANCE.dtoToEntity(dto)
            )
        );
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(
        @PathVariable Long id
    ) {
        filialService.delete(id);
    }

    @GetMapping(value = "/tipo/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<EnumTipoFilial>> findAllEnumTipoFilial() {
        return ResponseEntity.ok(
            EnumTipoFilial.getAll()
        );
    }
}
