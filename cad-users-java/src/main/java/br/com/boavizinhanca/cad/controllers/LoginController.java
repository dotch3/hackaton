package br.com.boavizinhanca.cad.controllers;

import br.com.boavizinhanca.cad.dto.UserDTO;
import br.com.boavizinhanca.cad.responses.StandardResponseAuthenticate;
import br.com.boavizinhanca.cad.services.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController()
@Api(value = "Login API",tags = { "Login" })
@RequestMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
@AllArgsConstructor
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
public class LoginController {

    @Autowired
    LoginService service;

    @ApiOperation("Authenticate")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseAuthenticate.class)
    @PostMapping(value = "/authenticate", produces = "application/json")
    public ResponseEntity<StandardResponseAuthenticate> authenticate(@RequestBody UserDTO user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return service.authenticate(user);
    }
}