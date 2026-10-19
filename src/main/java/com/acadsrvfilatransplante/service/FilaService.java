package com.acadsrvfilatransplante.service;

import com.acadsrvfilatransplante.DTO.RequestDTO;
import com.acadsrvfilatransplante.DTO.ResponseDTO;
import com.acadsrvfilatransplante.model.FilaTransplante;
import com.acadsrvfilatransplante.repository.FilaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.List;


@Service
public class FilaService {
    private FilaTransplante transplante;
    private FilaRepository repository;

    public FilaService(FilaRepository repository) {
        this.repository = repository;

    }
    public List<FilaTransplante> listarTodos() {
        return repository.findAll();
    }
    public ResponseDTO adicionarFila(RequestDTO dto){
        this.transplante = new FilaTransplante();

        this.transplante.setId(gerarId());
        this.transplante.setNome(dto.nome);
        this.transplante.setCpf(dto.cpf);
        this.transplante.setTelefone(dto.telefone);
        this.transplante.setOrgao(dto.orgao);
        this.transplante.setData(gerarData());

        repository.save(transplante);

        ResponseDTO resposta = new ResponseDTO();
        resposta.id = this.transplante.getId();
        resposta.messagem = "Criado com sucesso";
        return resposta;
    }

    private String gerarId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private String gerarData(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime data = LocalDateTime.now();
        return data.format(formato);
    }

}
