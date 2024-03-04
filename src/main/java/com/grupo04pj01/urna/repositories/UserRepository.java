package com.grupo04pj01.urna.repositories;

import com.grupo04pj01.urna.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserDetails findByUsuario(String usuario);


}
