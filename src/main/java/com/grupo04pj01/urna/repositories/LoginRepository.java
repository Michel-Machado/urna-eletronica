package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long> {

    Optional<LoginModel> findLoginModelByUsuario(String usuario);
}
