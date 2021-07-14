package ru.job4j.auth.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String surname;
    private int inn;
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Person> person = new HashSet<>();

    public static Employee of(String firstname, String surname, int inn) {
        Employee employee = new Employee();
        employee.setFirstname(firstname);
        employee.setSurname(surname);
        employee.setInn(inn);
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Set<Person> getPersons() {
        return person;
    }

    public void addPersons(Person persons) {
        this.person.add(persons);
    }

    public void deletePerson(int id) {
        Person del = new Person();
        del.setId(id);
        person.remove(del);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && inn == employee.inn && Objects.equals(firstname, employee.firstname)
                && Objects.equals(surname, employee.surname) && Objects.equals(created, employee.created)
                && Objects.equals(person, employee.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, inn, created, person);
    }
}
