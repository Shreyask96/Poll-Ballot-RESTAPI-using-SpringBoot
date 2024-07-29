package com.jsd.pollballot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jsd.pollballot.dto.Candidate;
import com.jsd.pollballot.service.CandidateService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/entercandidate")
    public String enterCandidate(@RequestParam String name) {
        candidateService.addCandidate(name);
        return "Candidate added successfully.";
    }

    @PostMapping("/castvote")
    public String castVote(@RequestParam String name) {
        boolean success = candidateService.castVote(name);
        return success ? "Vote casted successfully." : "Candidate not found.";
    }

    @GetMapping("/countvote")
    public Integer countVote(@RequestParam String name) {
        Integer voteCount = candidateService.getVoteCount(name);
        return (voteCount != null) ? voteCount : -1;
    }

    @GetMapping("/listvote")
    public List<Candidate> listVote() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/getwinner")
    public String getWinner() {
        Candidate winner = candidateService.getWinner();
        return (winner != null) ? winner.getName() : "No candidates found.";
    }
}
