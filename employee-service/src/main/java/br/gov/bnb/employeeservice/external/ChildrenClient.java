package br.gov.bnb.employeeservice.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "children-service", url = "${spring.cloud.openfeign.client.config.childrenClient.url}")
public interface ChildrenClient {

    @GetMapping("/employees/{id}/children")
    List<Child> findAll(@PathVariable long id);
}
