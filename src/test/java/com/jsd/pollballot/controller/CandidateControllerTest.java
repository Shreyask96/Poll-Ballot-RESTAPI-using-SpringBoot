package com.jsd.pollballot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEnterCandidate() throws Exception {
        mockMvc.perform(post("/api/entercandidate")
                .param("name", "Akash"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Candidate added successfully."));
    }

    @Test
    public void testCastVote() throws Exception {
        mockMvc.perform(post("/api/entercandidate")
                .param("name", "Akash"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/castvote")
                .param("name", "Akash"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vote casted successfully."));
    }

    @Test
    public void testCountVote() throws Exception {
        mockMvc.perform(post("/api/entercandidate")
                .param("name", "Akash"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/castvote")
                .param("name", "Akash"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/countvote")
                .param("name", "Akash"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    public void testListVote() throws Exception {
        mockMvc.perform(post("/api/entercandidate")
                .param("name", "Akash"))
                .andExpect(status().isOk());
        
//        mockMvc.perform(post("/api/entercandidate")
//                .param("name", "Bhaskar"))
//                .andExpect(status().isOk());
        
//        mockMvc.perform(post("/api/entercandidate")
//                .param("name", "Akash"))
//                .andExpect(status().isOk());

        mockMvc.perform(get("/api/listvote"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.Akash.voteCount").value(3));
 //               .andExpect(MockMvcResultMatchers.jsonPath("$.Bhaskar.voteCount").value(0));
    }

    @Test
    public void testGetWinner() throws Exception {
        mockMvc.perform(post("/api/entercandidate")
                .param("name", "Akash"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/entercandidate")
                .param("name", "Bhaskar"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/castvote")
                .param("name", "Akash"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/getwinner"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Akash"));
    }
}
