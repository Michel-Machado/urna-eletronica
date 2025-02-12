package com.grupo04pj01.urna.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCadastroEleitor {

    public List<EleitoresCadastradosDTO> registrados;
    public List<EleitoresCadastradosDTO> naoRegistrados;

}
