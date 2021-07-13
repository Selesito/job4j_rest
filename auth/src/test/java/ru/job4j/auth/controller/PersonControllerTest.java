package ru.job4j.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.AuthApplication;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {
    @MockBean
    private PersonRepository personsRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetByIdThenNotFound() throws Exception {
        when(personsRepo.findById(1)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/person/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetByIdThenTrue() throws Exception {
        Person person = Person.of(1, "Vova", "123");
        when(personsRepo.findById(1)).thenReturn(Optional.of(person));
        this.mockMvc.perform(get("/person/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("login", is(person.getLogin())));
    }

    @Test
    public void whenFindAllThenTrue() throws Exception {
        Person person1 = Person.of(1, "Vova", "123");
        Person person2 = Person.of(2, "admin", "123");
        when(personsRepo.findAll()).thenReturn(List.of(person1, person2));
        this.mockMvc.perform(get("/person/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login", is(person1.getLogin())))
                .andExpect(jsonPath("$[1].login", is(person2.getLogin())));
    }

    @Test
    public void whenDeleteThenTrue() throws Exception {
        mockMvc.perform(delete("/person/{id}", anyInt()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenPostThenTrue() throws Exception {
        Person person = Person.of(1, "Vova", "123");
        when(personsRepo.save(person)).thenReturn(person);
        String json = "{\"id\":\"1\",\"login\":\"login\",\"password\":\"password\"}";
        this.mockMvc.perform(post("/person/")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenPutThenTrue() throws Exception {
        Person person = Person.of(1, "Vova", "123");
        when(personsRepo.save(person)).thenReturn(person);
        String json = "{\"id\":\"1\",\"login\":\"login\",\"password\":\"password\"}";
        this.mockMvc.perform(put("/person/")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }
}