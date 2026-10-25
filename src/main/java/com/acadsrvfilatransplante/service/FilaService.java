package com.acadsrvfilatransplante.service;

import com.acadsrvfilatransplante.DTO.RequestDTO;
import com.acadsrvfilatransplante.DTO.ResponseDTO;
import com.acadsrvfilatransplante.model.FilaTransplante;
import com.acadsrvfilatransplante.model.SequenceCounter;
import com.acadsrvfilatransplante.model.StatusFila;
import com.acadsrvfilatransplante.repository.FilaRepository;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.List;


@Service
public class FilaService {
    private FilaTransplante transplante;
    private FilaRepository repository;
    private final MongoTemplate mongoTemplate;

    public FilaService(FilaRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<FilaTransplante> listarTodos() {
        return repository.findAll();
    }

    public ResponseDTO adicionarFila(RequestDTO dto) {
        this.transplante = new FilaTransplante();

        this.transplante.setId(gerarId());
        this.transplante.setNome(dto.nome);
        this.transplante.setCpf(dto.cpf);
        this.transplante.setTelefone(dto.telefone);
        this.transplante.setOrgao(dto.orgao);
        this.transplante.setData(gerarData());
        this.transplante.setPosicao(gerarPosicao());
        this.transplante.setStatus(StatusFila.PENDENTE);

        repository.save(transplante);

        ResponseDTO resposta = new ResponseDTO();
        resposta.id = this.transplante.getId();
        resposta.messagem = "Criado com sucesso";
        return resposta;
    }

    private String gerarId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private String gerarData() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime data = LocalDateTime.now();
        return data.format(formato);
    }

    private long gerarPosicao() {
        SequenceCounter contador = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is("fila_transplante")),
                new Update().inc("valor", 1),
                FindAndModifyOptions.options().upsert(true).returnNew(true),
                SequenceCounter.class
        );
        return contador.getValor();
    }

}
