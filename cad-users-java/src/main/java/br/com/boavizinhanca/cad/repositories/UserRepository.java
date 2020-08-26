package br.com.boavizinhanca.cad.repositories;

import br.com.boavizinhanca.cad.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM dbboavizinhanca.tb_user WHERE email = :username", nativeQuery = true)
    Optional<User> findUser(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbboavizinhanca.tb_user WHERE id_user = :id", nativeQuery = true)
    void delete(@Param("id") int id);
}
