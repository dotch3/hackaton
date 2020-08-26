package br.com.boavizinhanca.cad.services;

import br.com.boavizinhanca.cad.dto.PartnerSaveDTO;
import br.com.boavizinhanca.cad.dto.PartnerUpdateDTO;
import br.com.boavizinhanca.cad.entities.Partner;
import br.com.boavizinhanca.cad.entities.User;
import br.com.boavizinhanca.cad.repositories.PartnerRepository;
import br.com.boavizinhanca.cad.repositories.UserRepository;
import br.com.boavizinhanca.cad.responses.StandardResponseListPartner;
import br.com.boavizinhanca.cad.responses.StandardResponsePartner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerService {

    private final Logger logger = LoggerFactory.getLogger("PartnerService");

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<StandardResponseListPartner> findAll() {
        logger.info("Listing partners...");
        return ResponseEntity.ok(new StandardResponseListPartner(partnerRepository.findAll()));
    }

    public ResponseEntity<StandardResponsePartner> findPartnerByDocument(String document) {
        Optional<Partner> partner = partnerRepository.findByDocument(document);

        if(!partner.isPresent()) {
            logger.error("Partner not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new StandardResponsePartner(partner.get()));
    }

    public ResponseEntity<StandardResponsePartner> save(PartnerSaveDTO partnerSaveDTO) {
        if (userRepository.findUser(partnerSaveDTO.getLogin()).isPresent() || partnerRepository.findByDocument(partnerSaveDTO.getDocument()).isPresent()) {
            logger.error("User or company already registered!");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = new User();

        try {
            logger.info("Creating user...");
            user.setLogin(partnerSaveDTO.getLogin());
            user.setPassword(partnerSaveDTO.getPassword());
            user = userRepository.save(user);
            logger.info(user.getLogin() + " user created successfully!");
        } catch (Exception e) {
            logger.error("Oops, something happened while creating the user, cause:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        try {
            logger.info("Creating and associating partner with a user...");
            Partner partner = new Partner();
            partner.setIdUser(user.getId());
            partner.setDocument(partnerSaveDTO.getDocument());
            partner.setTrade(partnerSaveDTO.getTrade());
            partner.setCompany(partnerSaveDTO.getCompany());
            partner.setAddress(partnerSaveDTO.getAddress());
            partner.setAddress2(partnerSaveDTO.getAddress2());
            partner.setZipCode(partnerSaveDTO.getZipCode());
            partner.setPhone(partnerSaveDTO.getPhone());
            partner = partnerRepository.save(partner);
            logger.info(partner.getCompany() + " partner created successfully!");

            return new ResponseEntity<>(new StandardResponsePartner(partner), HttpStatus.CREATED);
        } catch (Exception e) {
            userRepository.delete(user.getId());
            logger.error("Oops, something happened while creating the partner, cause:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<StandardResponsePartner> update(PartnerUpdateDTO partnerUpdateDTO) {
        Optional<Partner> partner = partnerRepository.findById(partnerUpdateDTO.getId());
        if(!partner.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            logger.info("Creating and associating partner with a user...");
            Partner partnerUpdate = new Partner();
            partnerUpdate.setId(partnerUpdateDTO.getId());
            partnerUpdate.setIdUser(partner.get().getIdUser());
            partnerUpdate.setDocument(partnerUpdateDTO.getDocument());
            partnerUpdate.setTrade(partnerUpdateDTO.getTrade());
            partnerUpdate.setCompany(partnerUpdateDTO.getCompany());
            partnerUpdate.setAddress(partnerUpdateDTO.getAddress());
            partnerUpdate.setAddress2(partnerUpdateDTO.getAddress2());
            partnerUpdate.setZipCode(partnerUpdateDTO.getZipCode());
            partnerUpdate.setPhone(partnerUpdateDTO.getPhone());
            partnerUpdate = partnerRepository.save(partnerUpdate);
            logger.info(partnerUpdate.getCompany() + " partner created successfully!");

            return new ResponseEntity<>(new StandardResponsePartner(partnerUpdate), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Oops, something happened while updating the partner, cause:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity deletePartnerByDocument(String document) {
        Optional<Partner> partner = partnerRepository.findByDocument(document);

        if(!partner.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            logger.info("Deleting user and partner register...");
            partnerRepository.delete(document);
            userRepository.delete(partner.get().getIdUser());
            logger.info("Records successfully deleted!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Oops, something happened while deleting the user and partner account, cause:" + e.getMessage());
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
