package com.epam.task.spring_two.controller;

import com.epam.task.spring_two.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser(username = "testBook", authorities = {"SCOPE_read:book"})
    public void testGetBookByIdReturns200WhenAuthenticated() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/books/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Shadow Hunters City Of Bones"))
                .andExpect(jsonPath("$.author").value("Cassandra Clare"));
    }

    @Test
    public void testGetAllBooksReturns200WithNoAuthentication() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/books/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(7));
    }

    @Test
    public void testGetBookByIdReturns401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/books/getById/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "testBook", authorities = {"SCOPE_update:book"})
    public void testUpdateBookByIdReturns200WhenAuthenticated() throws Exception {
        String bookJson = "{\"title\": \"Lord Of The Rings\", \"author\": \"J Tolkien\", \"yearOfPublish\": \"1954\", \"price\": \"15.99\", \"classification\": \"Fiction\", \"isAvailable\": true }";
        mockMvc.perform(put("http://localhost:8081/api/books/updateBookById/3")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Lord Of The Rings"))
                .andExpect(jsonPath("$.yearOfPublish").value("1954"));
    }

    @Test
    @WithMockUser(username = "testBook", authorities = {"SCOPE_read:book"})
    public void testGetBookByIdReturns403WhenAuthenticatedButNotAuthorized() throws Exception {
        String bookJson = "{\"title\": \"Lord Of The Rings\", \"author\": \"J Tolkien\", \"yearOfPublish\": \"1954\", \"price\": \"15.99\", \"classification\": \"Fiction\", \"isAvailable\": true }";
        mockMvc.perform(put("http://localhost:8081/api/books/updateBookById/3")
                        .contentType("application/json")
                        .content(bookJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "testBook", authorities = {"SCOPE_update:book"})
    public void testDeleteBookByIdReturns403WhenAuthenticatedButNotAuthorized() throws Exception {
        mockMvc.perform(delete("http://localhost:8081/api/books/updateBookById/5")
                        .contentType("application/json")
                ).andExpect(status().isForbidden());
    }
}
