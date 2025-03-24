package com.epam.task.spring_two.controller;

import com.epam.task.spring_two.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

   String jwt = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlJod3lSaDE0WERub3RCWnRMd2w3UyJ9.eyJpc3MiOiJodHRwczovL2Rldi13MWNzZjF3YzhibG1temkyLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiI5S3lRV1h1MUlNNHBYamNzZmR2cWlBVzRRQkZ6c0RKZkBjbGllbnRzIiwiYXVkIjoiaHR0cHMvL3NwcmluZ2FwaSIsImlhdCI6MTc0Mjc3NDMzMSwiZXhwIjoxNzQyODYwNzMxLCJzY29wZSI6InJlYWRib29rIHVwZGF0ZWJvb2siLCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMiLCJhenAiOiI5S3lRV1h1MUlNNHBYamNzZmR2cWlBVzRRQkZ6c0RKZiJ9.P5IRQ1r4Z0gMNxhLTEGjKW-6eFIpWmqxj2yD67_-4e2v_HuSvDrLHRSQsi0yOFEbKlDccKwUYobZRhXjnx1xkqIx_Ka6A1NASeF24M0NPU6OrKpgOJnzbMQLVFGVRZ0CdzCKRlKWspZbrln2Ix2shSk-w-GMdjzs-j9kdzcJC3NYw34aRCFQueLU-5wB-WVfQgA0fKBRNY05VxkRY95BpGzPxZ_HWEXQMyp15OcPAJfKKgYiwJjE1UX8EL3HZcySCJXjmWbxyEclgelm6OaW1aTPbJeo0NVrw-XxZQlwR-uaTpGZ5KQscGNjMV0MFRe1QfZ4qx4H04lRFOmDT3zlfQ";
    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/books/1").header("Authorization", jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Shadow Hunters City Of Bones"))
                .andExpect(jsonPath("$.author").value("Cassandra Clare"));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/books").header("Authorization", jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(7));
    }

}
