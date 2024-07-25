package com.jsd.pollballot.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Candidate {
	@Id
    private String name;
    private int voteCount;

    // Constructor
    public Candidate(String name) {
        this.name = name;
        this.voteCount = 0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void incrementVoteCount() {
        this.voteCount++;
    }
}
