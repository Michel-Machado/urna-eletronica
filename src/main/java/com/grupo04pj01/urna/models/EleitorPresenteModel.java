package com.grupo04pj01.urna.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "eleitores_presente_tb")
public class EleitorPresenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "eleitor_id")
    public EleitorModel eleitorModel;

    public EleitorPresenteModel(EleitorModel eleitorModel) {
        this.eleitorModel = eleitorModel;
    }
}
