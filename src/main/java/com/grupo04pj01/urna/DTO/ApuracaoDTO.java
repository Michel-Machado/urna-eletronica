package com.grupo04pj01.urna.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApuracaoDTO {

    private int totalGeralDeVotos;
    private List<CandidatoVotosRecebidosDTO> apuracao;
}
