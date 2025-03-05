package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.ApuracaoModel;
import com.grupo04pj01.urna.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUserName(String userName);
}
