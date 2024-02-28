package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.EleitorModel;
import com.grupo04pj01.urna.models.EleitorPresenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleitorPresenteRepository extends JpaRepository<EleitorPresenteModel, Long> {

    Optional<EleitorPresenteModel> findByEleitorModel(EleitorModel eleitorModel);

}
