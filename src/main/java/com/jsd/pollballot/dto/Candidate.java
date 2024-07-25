package com.jsd.pollballot.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Candidate {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
    private int voteCount;

    // Constructor
    public Candidate(String name) {
        this.name = name;
        this.voteCount = 0;
    }

    // Getters and Setters
    public Long getId() {
 		return id;
 	}

 	public void setId(Long id) {
 		this.id = id;
 	}
    
    public void setName(String name) {
		this.name = name;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

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
