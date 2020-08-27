package br.com.boavizinhanca.cad.services;

import br.com.boavizinhanca.cad.dto.UserDTO;
import br.com.boavizinhanca.cad.entities.Customer;
import br.com.boavizinhanca.cad.entities.Partner;
import br.com.boavizinhanca.cad.entities.User;
import br.com.boavizinhanca.cad.repositories.CustomerRepository;
import br.com.boavizinhanca.cad.repositories.PartnerRepository;
import br.com.boavizinhanca.cad.repositories.UserRepository;
import br.com.boavizinhanca.cad.responses.StandardResponseAuthenticateCustomer;
import br.com.boavizinhanca.cad.responses.StandardResponseAuthenticatePartner;
import br.com.boavizinhanca.cad.utils.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PartnerRepository partnerRepository;

    public ResponseEntity<StandardResponseAuthenticateCustomer> authenticateCustomer(UserDTO userInput) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String login = userInput.getLogin();
        String password = userInput.getPassword();

        if(login == null || login.isBlank() || password == null || password.isBlank())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = userRepository.findUser(login);

        if(user.isPresent()) {
            if (CryptoUtils.encryptSHA256(user.get().getPassword()).equals(password)) {
                Optional<Customer> customer = customerRepository.findByIdUser(user.get().getId());

                if(customer.isPresent())
                    return ResponseEntity.ok(new StandardResponseAuthenticateCustomer(customer.get()));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            logger.error("Credentials aren't equal!");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        logger.error("Customer not found!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<StandardResponseAuthenticatePartner> authenticatePartner(UserDTO userInput) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String login = userInput.getLogin();
        String password = userInput.getPassword();

        if(login == null || login.isBlank() || password == null || password.isBlank())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = userRepository.findUser(login);

        if(user.isPresent()) {
            if (CryptoUtils.encryptSHA256(user.get().getPassword()).equals(password)) {
                Optional<Partner> partner = partnerRepository.findByIdUser(user.get().getId());
                if(partner.isPresent())
                    return ResponseEntity.ok(new StandardResponseAuthenticatePartner(partner.get()));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            logger.error("Credentials aren't equal!");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        logger.error("Partner not found!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
