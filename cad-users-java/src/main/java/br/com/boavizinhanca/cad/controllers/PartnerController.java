package br.com.boavizinhanca.cad.controllers;

import br.com.boavizinhanca.cad.dto.PartnerSaveDTO;
import br.com.boavizinhanca.cad.dto.PartnerUpdateDTO;
import br.com.boavizinhanca.cad.responses.StandardResponseListPartner;
import br.com.boavizinhanca.cad.responses.StandardResponsePartner;
import br.com.boavizinhanca.cad.services.PartnerService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8081","http://localhost:8888","http://54.163.66.128:8081"},maxAge=4800, allowCredentials="false")
@RestController()
@Api(value = "Partner's API",tags = { "Partners" })
@RequestMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
@AllArgsConstructor
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 422, message = "Unprocessable Entity"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
public class PartnerController {

    @Autowired
    PartnerService service;

    @ApiOperation("Search partners")
    @ApiResponse(code = 200, message = "OK", response = StandardResponseListPartner.class)
    @GetMapping(value = "/partner", produces = "application/json")
    public ResponseEntity<StandardResponseListPartner> findAllPartners() {
        return service.findAll();
    }

    @ApiOperation("Save partner")
    @ApiResponse(code = 201, message = "OK", response = StandardResponsePartner.class)
    @PostMapping(value = "/partner", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponsePartner> savePartner(@RequestBody @ApiParam(name = "Partner", value = "Partner") PartnerSaveDTO partnerSaveDTO) {
        return service.save(partnerSaveDTO);
    }

    @ApiOperation("Update partner")
    @ApiResponse(code = 200, message = "OK", response = StandardResponsePartner.class)
    @PutMapping(value = "/partner", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponsePartner> updatePartner(@RequestBody @ApiParam(name = "Partner", value = "Partner") PartnerUpdateDTO partnerUpdateDTO) {
        return service.update(partnerUpdateDTO);
    }

    @ApiOperation("Find partner by document")
    @ApiResponse(code = 200, message = "OK", response = StandardResponsePartner.class)
    @GetMapping(value = "/partner/{document}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponsePartner> findByDocument(@PathVariable @ApiParam(required = true) String document) {
        return service.findPartnerByDocument(document);
    }

    @ApiOperation("Delete partner")
    @ApiResponse(code = 204, message = "OK")
    @DeleteMapping(value = "/partner/{document}")
    @ResponseBody
    public ResponseEntity deletePartner(@PathVariable @ApiParam(required = true) String document) {
        return service.deletePartnerByDocument(document);
    }
}
