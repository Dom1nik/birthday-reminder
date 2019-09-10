package com.birthReminder;

import com.birthReminder.person.Person;
import com.birthReminder.person.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BirthReminderApplicationTests {

    private final static String SUBMIT_URL = "/submit";
    private final static String DELETE_URL = "/delete";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Before
    public void initTests() {
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void givenPerson_whenSubmitPerson_thenStatus201() throws Exception{
        invokeCreatePerson()
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void givenPersonId_whenDeletePerson_thenStatus200() throws Exception{
        Person person = createPerson();
        person.setTimestamp(LocalDateTime.now());
        personRepository.save(person);

        invokeDeletePerson()
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private ResultActions invokeCreatePerson() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(SUBMIT_URL)
                .contentType(MediaType.TEXT_HTML)
                .flashAttr("person", createPerson()));
    }

    private ResultActions invokeDeletePerson() throws Exception {
        Long personId = new Long(1);

        return mockMvc.perform(MockMvcRequestBuilders
                .get(DELETE_URL)
                .contentType(MediaType.TEXT_HTML)
                .sessionAttr("person", createPerson())
                .param("id", personId.toString()));
    }

    private Person createPerson() {
        Person person = new Person();
        person.setFirstName("Ivo");
        person.setLastName("Ivić");
        person.setBirthDate(LocalDate.of(1993, 2, 25));
        return person;
    }

}
