package br.gov.bnb.childservice.services;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.gov.bnb.childservice.exceptions.ChildNotFoundException;
import br.gov.bnb.childservice.models.Child;

@Service
public class ChildService {

    private final Logger logger = LoggerFactory.getLogger(ChildService.class);

    public List<Child> findAll(long id) {
        int numberOfChilds = ThreadLocalRandom.current().nextInt(0, 5 + 1);

        if (numberOfChilds == 0) {
            ChildNotFoundException childNotFoundException = new ChildNotFoundException(id);
            logger.error(childNotFoundException.getMessage(), childNotFoundException);

            throw childNotFoundException;
        }

        return IntStream.range(0, numberOfChilds).mapToObj(childId -> {
            int age = ThreadLocalRandom.current().nextInt(0, 17 + 1);
            return new Child(childId, "Child-" + childId, age);
        }).collect(Collectors.toUnmodifiableList());
    }
}
