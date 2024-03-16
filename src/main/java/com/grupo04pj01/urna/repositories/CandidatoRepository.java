package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.CandidatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoModel, Long> {

    Optional<CandidatoModel> findByChapa(String chapa);


}
