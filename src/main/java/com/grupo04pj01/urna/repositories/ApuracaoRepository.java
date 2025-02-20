package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.ApuracaoModel;
import com.grupo04pj01.urna.models.UrnaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuracaoRepository extends JpaRepository<ApuracaoModel, Long> {



}
