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
import com.acadsrvfilatransplante.DTO.StatusRequestDTO;
import com.acadsrvfilatransplante.DTO.StatusResponseDTO;
import com.mongodb.client.result.UpdateResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.List;


@Service
public class FilaService {
    private final FilaRepository repository;
    private final MongoTemplate mongoTemplate;

    public FilaService(FilaRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<FilaTransplante> listarTodos() {
        return repository.findAll();
    }

    public ResponseDTO adicionarFila(RequestDTO dto) {
        FilaTransplante transplante = new FilaTransplante();

        transplante.setId(gerarId());
        transplante.setNome(dto.nome);
        transplante.setCpf(dto.cpf);
        transplante.setTelefone(dto.telefone);
        transplante.setOrgao(dto.orgao);
        transplante.setData(gerarData());
        transplante.setPosicao(gerarPosicao());
        transplante.setStatus(StatusFila.PENDENTE);

        repository.save(transplante);

        ResponseDTO resposta = new ResponseDTO();
        resposta.id = transplante.getId();
        resposta.messagem = "Criado com sucesso";
        return resposta;
    }

    public StatusResponseDTO atualizarStatus(String id, StatusRequestDTO dto) {
        if (dto == null || dto.status == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " O Status è Obrigatório "
            );
        }

        StatusFila status;

        try{
            status = StatusFila.valueOf(dto.status);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status inválido.");
        }

        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update().set("status", status);
        UpdateResult resultado = mongoTemplate.updateFirst(query, update, FilaTransplante.class);

       if (resultado.getMatchedCount() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado.");
       }

      return new StatusResponseDTO("Status atualizado com sucesso,", status.name());
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

        if (contador != null) {
            return contador.getValor();
        }
        return 0;
    }

}
