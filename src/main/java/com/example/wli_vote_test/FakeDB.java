package com.example.wli_vote_test;

import com.example.wli_vote_test.data.Candidate;
import com.example.wli_vote_test.data.Voter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum FakeDB {

    INSTANCE;

    private final Map<Long, Voter> voterById = new ConcurrentHashMap<>();
    private final Comparator<Voter> voterSorter = (Voter v1, Voter v2) -> v1.getName().compareTo(v2.getName());
    private final Map<Long, Candidate> candidateById = new ConcurrentHashMap<>();
    private final Comparator<Candidate> candidateSorter = (Candidate v1, Candidate v2) -> v1.getName().compareTo(v2.getName());

    public List<Voter> votersSorted(){
        return voterById.values().stream().sorted(voterSorter).toList();
    }

    public List<Candidate> candidatesSorted(){
        return candidateById.values().stream().sorted(candidateSorter).toList();
    }

    public void addVoter(String name){
        new Voter(name, voterById);
    }

    public void addCandidate(String name){
        new Candidate(name, candidateById);
    }

    public void castVote(long voterId, long candidateId){
        Voter caster = voterById.get(voterId);
        Candidate chosen = candidateById.get(candidateId);

        if(caster == null || chosen == null){
            return;
        }

        caster.castVote(chosen);
    }
}
