package com.acadsrvfilatransplante.DTO;

public class StatusResponseDTO {
    public String mensagem;
    public String status;

    public StatusResponseDTO(String mensagem, String status)
    {
        this.mensagem = mensagem;
        this.status = status;
    }
}
