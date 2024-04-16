package br.gov.bnb.employeeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.bnb.employeeservice.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
