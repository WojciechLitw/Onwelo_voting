package com.example.wli_vote_test;

import com.example.wli_vote_test.data.Candidate;
import com.example.wli_vote_test.data.Voter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
*    Data repository.
*    In a typical sytuation this would exist only as a intermediary between logic and database.
*    However, to simplify process, this in-time storage in a form of a Singleton works just fine.
*/
public enum FakeDB {

    INSTANCE;

    // Used thread-safe maps, so that it would be possible to access it over several copies of client (not sure of the speed though).
    private final Map<Long, Voter> voterById = new ConcurrentHashMap<>();
    private final Comparator<Voter> voterSorter = (Voter v1, Voter v2) -> v1.getName().compareTo(v2.getName());
    private final Map<Long, Candidate> candidateById = new ConcurrentHashMap<>();
    private final Comparator<Candidate> candidateSorter = (Candidate v1, Candidate v2) -> v1.getName().compareTo(v2.getName());

    // Get list of available voters, sorted alphabeticaly by name.
    public List<Voter> votersSorted(){
        return voterById.values().stream().sorted(voterSorter).toList();
    }

    // Get list of available candidates, sorted alphabeticaly by name.
    public List<Candidate> candidatesSorted(){
        return candidateById.values().stream().sorted(candidateSorter).toList();
    }

    // Add new voter.
    // For simplicity, if name is empty, procedure will do nothing and reload client.
    // In a normal situation, an exception should be thrown and handled.
    public void addVoter(String name){
        if(name == null || name.size() == 0){
            return;
        }
            
        new Voter(name, voterById);
    }

    // Add new candidate.
    // For simplicity, if name is empty, procedure will do nothing and reload client.
    // In a normal situation, an exception should be thrown and handled.
    public void addCandidate(String name){
        if(name == null || name.size() == 0){
            return;
        }
        
        new Candidate(name, candidateById);
    }

    // Cast a vote
    // If requested voter-candidate pair doesn't exist, process will do nothing.
    public void castVote(long voterId, long candidateId){
        Voter caster = voterById.get(voterId);
        Candidate chosen = candidateById.get(candidateId);

        if(caster == null || chosen == null){
            return;
        }

        caster.castVote(chosen);
    }
}
