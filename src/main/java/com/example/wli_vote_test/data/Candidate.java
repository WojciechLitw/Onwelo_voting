package com.example.wli_vote_test.data;

import lombok.Getter;

import java.util.Map;

/**
*    Candidate data
*    Here and for Voter, lombok library was used (cuts down on boiler-plate code).
*/
@Getter
public class Candidate {

    private final long id;
    private final String name;
    private long voteCount;

    public Candidate(String name, Map<Long, Candidate> storage){
        this.name = name;
        this.voteCount = 0;
        this.id = storage.size();

        storage.put(this.id, this);
    }

    public void getVote(){
        voteCount++;
    }
}
