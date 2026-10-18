package com.acadsrvfilatransplante.repository;


import com.acadsrvfilatransplante.model.FilaTransplante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilaRepository extends MongoRepository<FilaTransplante, String> {
}
