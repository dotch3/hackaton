package br.com.boavizinhanca.cad.controllers;

import br.com.boavizinhanca.cad.dto.CustomerSaveDTO;
import br.com.boavizinhanca.cad.dto.CustomerUpdateDTO;
import br.com.boavizinhanca.cad.responses.*;
import br.com.boavizinhanca.cad.services.CustomerService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8081","http://localhost:8888","http://54.163.66.128:8081"},maxAge=4800, allowCredentials="false")
@RestController()
@Api(value = "Customer's API",tags = { "Customers" })
@RequestMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
@AllArgsConstructor
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 422, message = "Unprocessable Entity"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
public class CustomerController {

    @Autowired
    CustomerService service;

    @ApiOperation("Search customers")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseListCustomer.class)
    @GetMapping(value = "/customer", produces = "application/json")
    public ResponseEntity<StandardResponseListCustomer> findAllCustomers() {
        return service.findAll();
    }

    @ApiOperation("Save customer")
    @ApiResponse(code = 201, message = "OK", response = StandardResponseCustomer.class)
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponseCustomer> saveCustomer(@RequestBody @ApiParam(name = "Customer", value = "Customer") CustomerSaveDTO customerSaveDTO) {
        return service.save(customerSaveDTO);
    }

    @ApiOperation("Update customer")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseCustomer.class)
    @PutMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponseCustomer> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return service.update(customerUpdateDTO);
    }

    @ApiOperation("Find customer by document")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseCustomer.class)
    @GetMapping(value = "/customer/{document}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponseCustomer> findByDocument(@PathVariable @ApiParam(required = true) String document) {
        return service.findCustomerByDocument(document);
    }

    @ApiOperation("Delete customer")
    @ApiResponse(code = 204, message = "OK")
    @DeleteMapping(value = "/customer/{document}")
    public ResponseEntity deletePartner(@PathVariable @ApiParam(required = true) String document) {
        return service.deleteCustomerByDocument(document);
    }
}
