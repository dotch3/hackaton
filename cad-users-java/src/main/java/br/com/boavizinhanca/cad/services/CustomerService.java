package br.com.boavizinhanca.cad.services;

import br.com.boavizinhanca.cad.dto.CustomerSaveDTO;
import br.com.boavizinhanca.cad.dto.CustomerUpdateDTO;
import br.com.boavizinhanca.cad.entities.Customer;
import br.com.boavizinhanca.cad.entities.User;
import br.com.boavizinhanca.cad.repositories.CustomerRepository;
import br.com.boavizinhanca.cad.repositories.UserRepository;
import br.com.boavizinhanca.cad.responses.StandardResponseCustomer;
import br.com.boavizinhanca.cad.responses.StandardResponseListCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger("CustomerService");

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<StandardResponseListCustomer> findAll() {
        logger.info("Listing customers...");
        return ResponseEntity.ok(new StandardResponseListCustomer(customerRepository.findAll()));
    }

    public ResponseEntity<StandardResponseCustomer> findCustomerByDocument(String document) {
        Optional<Customer> customer = customerRepository.findByDocument(document);

        if(!customer.isPresent()) {
            logger.error("Customer not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new StandardResponseCustomer(customer.get()));
    }

    public ResponseEntity<StandardResponseCustomer> save(CustomerSaveDTO customerSaveDTO) {
        if (userRepository.findUser(customerSaveDTO.getLogin()).isPresent() || customerRepository.findByDocument(customerSaveDTO.getDocument()).isPresent()) {
            logger.error("User or customer already registered!");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = new User();

        try {
            logger.info("Creating user...");
            user.setLogin(customerSaveDTO.getLogin());
            user.setPassword(customerSaveDTO.getPassword());
            user = userRepository.save(user);
            logger.info(user.getLogin() + " user created successfully!");
        } catch (Exception e) {
            logger.error("Oops, something happened while creating the user, cause:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        try {
            logger.info("Creating and associating customer with a user...");
            Customer customer = new Customer();
            customer.setIdUser(user.getId());
            customer.setDocument(customerSaveDTO.getDocument());
            customer.setName(customerSaveDTO.getName());
            customer.setGender(customerSaveDTO.getGender());
            customer.setAddress(customerSaveDTO.getAddress());
            customer.setAddress2(customerSaveDTO.getAddress2());
            customer.setZipCode(customerSaveDTO.getZipCode());
            customer.setPhone(customerSaveDTO.getPhone());
            customer = customerRepository.save(customer);
            logger.info(customer.getName() + " customer created successfully!");

            return new ResponseEntity<>(new StandardResponseCustomer(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            userRepository.delete(user.getId());
            logger.error("Oops, something happened while creating the customer, cause:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<StandardResponseCustomer> update(CustomerUpdateDTO customerUpdateDTO) {
        Optional<Customer> customer = customerRepository.findById(customerUpdateDTO.getId());
        if(!customer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            logger.info("Creating and associating customer with a user...");
            Customer customerUpdate = new Customer();
            customerUpdate.setId(customerUpdateDTO.getId());
            customerUpdate.setIdUser(customer.get().getIdUser());
            customerUpdate.setDocument(customerUpdateDTO.getDocument());
            customerUpdate.setName(customerUpdateDTO.getName());
            customerUpdate.setGender(customerUpdateDTO.getGender());
            customerUpdate.setAddress(customerUpdateDTO.getAddress());
            customerUpdate.setAddress2(customerUpdateDTO.getAddress2());
            customerUpdate.setZipCode(customerUpdateDTO.getZipCode());
            customerUpdate.setPhone(customerUpdateDTO.getPhone());
            customerUpdate = customerRepository.save(customerUpdate);
            logger.info(customerUpdate.getName() + " customer created successfully!");

            return new ResponseEntity<>(new StandardResponseCustomer(customerUpdate), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Oops, something happened while updating the customer, cause:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity deleteCustomerByDocument(String document) {
        Optional<Customer> customer = customerRepository.findByDocument(document);

        if(!customer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {
            logger.info("Deleting user and customer register...");
            customerRepository.delete(document);
            userRepository.delete(customer.get().getIdUser());
            logger.info("Records successfully deleted!");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Oops, something happened while deleting the user and partner account, cause:" + e.getMessage());
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
