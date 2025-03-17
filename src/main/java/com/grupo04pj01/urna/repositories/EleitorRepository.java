package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.EleitorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleitorRepository extends JpaRepository<EleitorModel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM eleitores_tb e WHERE (:nome IS NULL OR LOWER(e.nome) LIKE LOWER(:nome || '%')) AND (:ra IS NULL OR e.ra LIKE :ra || '%')")
    List<EleitorModel> search(@Param("nome") String nome, @Param("ra") String ra);

    Optional<EleitorModel> findEleitorModelByRa(String ra);

    Optional<EleitorModel> findEleitorModelByRaIgnoreCase(String ra);

    @Query(nativeQuery = true, value = "SELECT MAX(numero)  FROM eleitores_tb  where classe =:sala")
    Integer buscaMaiorNumero(@Param("sala") String sala);

}
