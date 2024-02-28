package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.CandidatoModel;
import com.grupo04pj01.urna.models.EleitorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoModel, Long> {


}
