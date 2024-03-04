package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.EleitorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleitorRepository extends JpaRepository<EleitorModel, Long> {

    Optional<EleitorModel> findEleitorModelByRa(String ra);
}
