package br.com.boavizinhanca.cad.repositories;

import br.com.boavizinhanca.cad.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM dbboavizinhanca.tb_customer WHERE nr_documento = :document", nativeQuery = true)
    Optional<Customer> findByDocument(String document);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dbboavizinhanca.tb_customer WHERE nr_documento = :document", nativeQuery = true)
    void delete(@Param("document") String document);
}
