package com.grupo04pj01.urna.DTO;

import com.grupo04pj01.urna.models.CandidatoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidatoVotosRecebidosDTO {

    private String nome;
    private Integer totalVotos;

    public CandidatoVotosRecebidosDTO(CandidatoModel candidatoModel, Integer votos) {
        nome = candidatoModel.getNome();
        totalVotos = votos;
    }
}
