package com.acadsrvfilatransplante.controller;

import com.acadsrvfilatransplante.DTO.RequestDTO;
import com.acadsrvfilatransplante.DTO.ResponseDTO;
import com.acadsrvfilatransplante.service.FilaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.acadsrvfilatransplante.model.FilaTransplante;
@RestController
@RequestMapping("/fila-transplante")
public class FilaController {

    private final FilaService filaService;

    public FilaController(FilaService filaService) {
        this.filaService = filaService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO adicionar(@RequestBody RequestDTO request) {
        return filaService.adicionarFila(request);
    }

    @GetMapping("/list")
    public List<FilaTransplante> listarTodos() {
        return filaService.listarTodos();
    }
}
