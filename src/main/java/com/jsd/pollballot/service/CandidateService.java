package com.jsd.pollballot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsd.pollballot.dto.Candidate;
import com.jsd.pollballot.repository.CandidateRepository;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CandidateService {
	private final ConcurrentHashMap<String, Candidate> candidates = new ConcurrentHashMap<>();

//	public void addCandidate(String name) {
//		candidates.putIfAbsent(name, new Candidate(name));
//	}

	@Autowired
	private CandidateRepository candidateRepository;

	public Candidate addCandidate(String name) {
		Candidate candidate = new Candidate(name);
		candidate.setName(name);
		candidate.setVoteCount(0);
		return candidateRepository.save(candidate);
	}

	public String incrementVote(String name) {
		if (!candidates.containsKey(name)) {
			return "Candidate not found";
		}
		candidates.computeIfPresent(name, (k, v) -> v);
		return "Vote cast successfully";
	}

	public boolean castVote(String name) {
		Candidate candidate = candidates.get(name);
		if (candidate != null) {
			candidate.incrementVoteCount();
			return true;
		}
		return false;
	}

	public Integer getVoteCount(String name) {
		Candidate candidate = candidates.get(name);
		return (candidate != null) ? candidate.getVoteCount() : null;
	}

	public ConcurrentHashMap<String, Candidate> getAllCandidates() {
		return candidates;
	}

	public Candidate getWinner() {
		return candidates.values().stream().max((c1, c2) -> Integer.compare(c1.getVoteCount(), c2.getVoteCount()))
				.orElse(null);
	}
}