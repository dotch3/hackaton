package br.com.boavizinhanca.cad.repositories;

import br.com.boavizinhanca.cad.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    @Query(value = "SELECT * FROM dbboavizinhanca.tb_partner WHERE nr_documento = :document", nativeQuery = true)
    Optional<Partner> findByDocument(@Param("document") String document);

    @Query(value = "SELECT * FROM dbboavizinhanca.tb_partner WHERE id_user = :idUser", nativeQuery = true)
    Optional<Partner> findByIdUser(@Param("idUser") int idUser);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbboavizinhanca.tb_partner WHERE nr_documento = :document", nativeQuery = true)
    void delete(@Param("document") String document);
}
