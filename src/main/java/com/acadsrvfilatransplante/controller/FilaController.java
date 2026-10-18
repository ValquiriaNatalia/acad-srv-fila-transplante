package com.acadsrvfilatransplante.controller;

import com.acadsrvfilatransplante.DTO.RequestDTO;
import com.acadsrvfilatransplante.DTO.ResponseDTO;
import com.acadsrvfilatransplante.service.FilaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fila-transplante")
public class FilaController {

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO adicionar(@RequestBody RequestDTO request){
        FilaService servico = new FilaService();
        return servico.adicionarFila(request);
    }
}
