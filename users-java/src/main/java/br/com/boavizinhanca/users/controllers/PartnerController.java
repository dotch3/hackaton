package br.com.boavizinhanca.users.controllers;

import br.com.boavizinhanca.users.models.Partner;
import br.com.boavizinhanca.users.responses.StandardResponse;
import br.com.boavizinhanca.users.services.PartnerService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Partner's API",tags = { "Partners" })
@RequestMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
@AllArgsConstructor
@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
public class PartnerController {

    @Autowired
    private PartnerService service;

    @ApiOperation("Search partners")
    @GetMapping(value = "/partner", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponse> findAllPartners() {
        return ResponseEntity.ok(service.findAll());
    }

    @ApiOperation("Save partner")
    @PostMapping(value = "/partner", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponse> savePartner(@RequestBody Partner partner) {
        return new ResponseEntity<>(service.save(partner), HttpStatus.CREATED);
    }

    @ApiOperation("Update partner")
    @PutMapping(value = "/partner", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponse> updatePartner(@RequestBody Partner partner) {
        return ResponseEntity.ok(service.save(partner));
    }

    @ApiOperation("Find partner by document")
    @PutMapping(value = "/partner/{document}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponse> findByDocument(@PathVariable @ApiParam(value = "Documento", required = true) String document) {
        return ResponseEntity.ok(service.findPartnerByDocument(document));
    }

    @ApiOperation("Delete partner")
    @DeleteMapping(value = "/partner/{document}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StandardResponse> deletePartner(@PathVariable @ApiParam(value = "Documento", required = true) String document) {
        return ResponseEntity.ok(service.deletePartnerByDocument(document));
    }
}