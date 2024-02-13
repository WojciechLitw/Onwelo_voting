package com.example.wli_vote_test.data;

import lombok.Getter;

import java.util.Map;

/**
*    Voter data.
*/
@Getter
public class Voter {

    private final long id;
    private final String name;
    private boolean voteCast;

    public Voter(String name, Map<Long, Voter> storage){
        this.name = name;
        this.voteCast = false;
        this.id = storage.size() + 1;

        storage.put(this.id, this);
    }

    // This was added out of haste, never used in code.
    public Voter(long id, String name, boolean voteCast){
        this.id = id;
        this.name = name;
        this.voteCast = voteCast;
    }

    public void castVote(Candidate chosen){
        if(voteCast) {
            return;
        }

        chosen.getVote();
        this.voteCast = true;
    }
}
