package ru.job4j.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final String API = "http://localhost:8080/person/";

    private static final String API_ID = "http://localhost:8080/person/{id}";

    private final EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate rest;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Person person, @RequestParam int id) {
        Employee employee = employeeRepository.findById(id);
        Person rsl = rest.postForObject(API, person, Person.class);
        employee.addPersons(rsl);
        employeeRepository.save(employee);
        return new ResponseEntity<>(
                employee,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idEm}/{idPer}")
    public ResponseEntity<Void> delete(@PathVariable int idEm, @PathVariable int idPer) {
        Employee employee = employeeRepository.findById(idEm);
        employee.deletePerson(idPer);
        employeeRepository.save(employee);
        rest.delete(API_ID, idPer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        return this.employeeRepository.findAll();
    }
}
