package com.acadsrvfilatransplante.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "fila_transplante_counters")
public class SequenceCounter {
    @Id
    private String id;
    private long valor;

    public SequenceCounter() {
    }

    public long getValor() {
        return valor;
    }

}

