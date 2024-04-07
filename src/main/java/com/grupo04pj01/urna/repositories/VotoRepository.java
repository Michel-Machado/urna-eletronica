package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.VotosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.naming.InterruptedNamingException;

@Repository
public interface VotoRepository extends JpaRepository<VotosModel, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM votos_tb WHERE candidato_id = :candidatoId")
    Integer contarVotosByCandidatoId(Long candidatoId);

}
