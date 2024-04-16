package br.gov.bnb.childservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.gov.bnb.childservice.services.ChildService;

@RestController
@RequestMapping("/employees/{id}/children")
public class ChildController {

    private final ChildService childservice;

    public ChildController(ChildService childservice) {
        this.childservice = childservice;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.childservice.findAll(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
