package com.jsd.pollballot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsd.pollballot.dto.Candidate;
import com.jsd.pollballot.repository.CandidateRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CandidateService {
	private final ConcurrentHashMap<String, Candidate> candidates = new ConcurrentHashMap<>();


	@Autowired
	private CandidateRepository candidateRepository;

	public Candidate addCandidate(String name) {
		Candidate candidate = new Candidate(name);
		candidate.setName(name);
		candidate.setVoteCount(0);
		return candidateRepository.save(candidate);
	}

	public boolean castVote(String name) {
		 Candidate candidate = candidateRepository.findByName(name);
	        if (candidate != null) {
	            candidate.incrementVoteCount();
	            candidateRepository.save(candidate);
	            return true;
		}
		return false;
	}

	
	public Integer getVoteCount(String name) {
		Candidate candidate = candidateRepository.findByName(name);
		return (candidate != null) ? candidate.getVoteCount() : null;
	}

	public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
	
	public Candidate getWinner() {
		return candidateRepository.findAll().stream()
				.max((c1, c2) -> Integer
				.compare(c1.getVoteCount(), c2.getVoteCount()))
				.orElse(null);
	}
}