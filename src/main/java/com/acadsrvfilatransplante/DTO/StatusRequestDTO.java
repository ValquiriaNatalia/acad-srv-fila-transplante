package com.acadsrvfilatransplante.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class StatusRequestDTO {
   @Schema(description = "Status da fila de transplante",
           allowableValues = {"PENDENTE", "CONCLUIDO", "CANCELADO"},
           example = "CONCLUIDO"
   )
    public String status;
}
