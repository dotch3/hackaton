package br.com.boavizinhanca.cad.controllers;

import br.com.boavizinhanca.cad.dto.UserDTO;
import br.com.boavizinhanca.cad.responses.StandardResponseAuthenticateCustomer;
import br.com.boavizinhanca.cad.responses.StandardResponseAuthenticatePartner;
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

@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8081","http://localhost:8888","http://54.163.66.128:8081"},maxAge=4800, allowCredentials="false")
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

    @ApiOperation("Authenticate Customer")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseAuthenticateCustomer.class)
    @PostMapping(value = "/authenticate/customer", produces = "application/json")
    public ResponseEntity<StandardResponseAuthenticateCustomer> authenticateCustomer(@RequestBody UserDTO user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return service.authenticateCustomer(user);
    }

    @ApiOperation("Authenticate Partner")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseAuthenticatePartner.class)
    @PostMapping(value = "/authenticate/partner", produces = "application/json")
    public ResponseEntity<StandardResponseAuthenticatePartner> authenticatePartner(@RequestBody UserDTO user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return service.authenticatePartner(user);
    }
}
