package com.jsd.pollballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsd.pollballot.dto.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByName(String name);
}
