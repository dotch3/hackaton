package br.com.boavizinhanca.users.services;

import br.com.boavizinhanca.users.models.Partner;
import br.com.boavizinhanca.users.repositories.PartnerRepository;
import br.com.boavizinhanca.users.responses.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    public StandardResponse findAll() {
        return new StandardResponse(repository.findAll());
    }

    public StandardResponse findPartnerByDocument(String document) {
        return new StandardResponse(repository.findById(document));
    }

    public StandardResponse save(Partner partner) {
        return new StandardResponse(repository.save(partner));
    }

    public StandardResponse deletePartnerByDocument(String document) {
        repository.deleteById(document);
        return new StandardResponse("Successfully deleted partner");
    }
}
