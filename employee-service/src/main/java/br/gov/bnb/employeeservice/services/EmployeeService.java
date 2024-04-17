package br.gov.bnb.employeeservice.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.bnb.employeeservice.exceptions.EmployeeNotFoundException;
import br.gov.bnb.employeeservice.external.Child;
import br.gov.bnb.employeeservice.external.ChildrenClient;
import br.gov.bnb.employeeservice.models.Employee;
import br.gov.bnb.employeeservice.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    private final static int EMPLOYEE_PAGE_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final static String CLIENT_ERROR_MESSAGE = "There was an exception '%s' when calling children service";

    private final EmployeeRepository employeeRepository;
    private final ChildrenClient childrenClient;

    public EmployeeService(EmployeeRepository employeeRepository, ChildrenClient childrenClient) {
        this.employeeRepository = employeeRepository;
        this.childrenClient = childrenClient;
    }

    public List<Employee> findPagedEmployees(int page) {
        logger.info("findPagedEmployees");
        PageRequest pageRequest = PageRequest.of(page, EMPLOYEE_PAGE_SIZE, Sort.by("name").ascending());
        List<Employee> employees = employeeRepository.findAll(pageRequest).getContent();
        return employees.stream().map(this::enrichEmployee).collect(Collectors.toUnmodifiableList());
    }

    public Employee findById(long id) {
        return employeeRepository.findById(id).map(this::enrichEmployee)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private Employee enrichEmployee(Employee employee) {
        try {
            List<Child> children = this.childrenClient.findAll(employee.getId());
            employee.setChildren(children);
        } catch (Exception e) {
            logger.error(String.format(CLIENT_ERROR_MESSAGE, e.getMessage()), e);
            employee.setChildren(Collections.emptyList());
        }
        return employee;
    }
}
