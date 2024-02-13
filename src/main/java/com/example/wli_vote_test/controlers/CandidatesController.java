package com.example.wli_vote_test.controlers;

import com.example.wli_vote_test.FakeDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
*    Candidate enpoint handler
*/
@RestController
public class CandidatesController {

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/candidates")
    public String greeting(Model model) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(FakeDB.INSTANCE.candidatesSorted());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/candidates/add")
    public void add(@RequestParam(name="name") String name, Model model){
        FakeDB.INSTANCE.addCandidate(name);
    }
}
