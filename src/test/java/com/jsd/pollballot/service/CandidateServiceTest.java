package com.jsd.pollballot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;

    @Test
    public void testAddCandidate() {
        candidateService.addCandidate("Akash");
        assertNotNull(candidateService.getVoteCount("Akash"));
    }

    @Test
    public void testCastVote() {
        candidateService.addCandidate("Bhaskar");
        boolean success = candidateService.castVote("Bhaskar");
        assertTrue(success);
    }

    @Test
    public void testInvalidCandidate() {
        boolean success = candidateService.castVote("Chetan");
        assertFalse(success);
    }

    @Test
    public void testGetVoteCount() {
        candidateService.addCandidate("Akash");
        assertEquals(0, candidateService.getVoteCount("Akash"));
        candidateService.castVote("Akash");
        assertEquals(1, candidateService.getVoteCount("Akash"));
    }

    @Test
    public void testGetWinner() {
        candidateService.addCandidate("Akash");
        candidateService.addCandidate("Bhaskar");
        candidateService.castVote("Akash");
        candidateService.castVote("Akash");
        candidateService.castVote("Bhaskar");
        assertEquals("Akash", candidateService.getWinner().getName());
    }
}
