package br.com.boavizinhanca.cad.services;

import br.com.boavizinhanca.cad.dto.UserDTO;
import br.com.boavizinhanca.cad.entities.User;
import br.com.boavizinhanca.cad.repositories.UserRepository;
import br.com.boavizinhanca.cad.responses.StandardResponseAuthenticate;
import br.com.boavizinhanca.cad.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;

    public ResponseEntity<StandardResponseAuthenticate> authenticate(UserDTO userInput) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String login = userInput.getLogin();
        String password = userInput.getPassword();

        if(login == null || login.isBlank() || password == null || password.isBlank())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = repository.findUser(login);

        if(user.isPresent()) {
            if (CryptoUtils.encryptSHA256(user.get().getPassword()).equals(password))
                return ResponseEntity.ok(new StandardResponseAuthenticate(user.get().getId()));
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
