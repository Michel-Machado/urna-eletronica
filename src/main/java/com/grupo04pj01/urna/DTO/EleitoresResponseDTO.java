package com.grupo04pj01.urna.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class EleitoresResponseDTO {

    public Long id;
    public String nome;
    public String ra;

}
