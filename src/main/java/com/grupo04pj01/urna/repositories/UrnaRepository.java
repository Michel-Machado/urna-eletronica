package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.UrnaModel;
import com.grupo04pj01.urna.models.VotosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrnaRepository extends JpaRepository<UrnaModel, Long> {

}
