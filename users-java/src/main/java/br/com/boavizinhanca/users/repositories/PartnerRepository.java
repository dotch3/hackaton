package br.com.boavizinhanca.users.repositories;

import br.com.boavizinhanca.users.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, String> {
}
