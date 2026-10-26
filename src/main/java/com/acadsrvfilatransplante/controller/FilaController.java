package com.acadsrvfilatransplante.controller;

import com.acadsrvfilatransplante.DTO.RequestDTO;
import com.acadsrvfilatransplante.DTO.ResponseDTO;
import com.acadsrvfilatransplante.service.FilaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.acadsrvfilatransplante.model.FilaTransplante;
import com.acadsrvfilatransplante.DTO.StatusRequestDTO;
import com.acadsrvfilatransplante.DTO.StatusResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/fila-transplante")
@Tag(name = "Fila de Transplante", description = "Operações da fila  transplante")

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

    @PatchMapping("/{id}/status")
    public StatusResponseDTO atualizarStatus(
            @PathVariable String id,
            @RequestBody StatusRequestDTO request
    ) {
        return filaService.atualizarStatus(id, request);
    }

}
