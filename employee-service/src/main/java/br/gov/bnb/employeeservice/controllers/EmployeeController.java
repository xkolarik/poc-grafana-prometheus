package br.gov.bnb.employeeservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.gov.bnb.employeeservice.exceptions.EmployeeNotFoundException;
import br.gov.bnb.employeeservice.models.Employee;
import br.gov.bnb.employeeservice.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> findPaged(@RequestParam(required = false, defaultValue = "0") int page) {
        return ResponseEntity.ok(this.employeeService.findPagedEmployees(page));
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id) {
        try {
            return this.employeeService.findById(id);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
