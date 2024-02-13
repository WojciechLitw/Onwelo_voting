package com.example.wli_vote_test.controlers;

import com.example.wli_vote_test.FakeDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotersController {

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/voters")
    public String greeting(Model model) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(FakeDB.INSTANCE.votersSorted());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/voters/add")
    public void add(@RequestParam(name="name") String name, Model model){
        FakeDB.INSTANCE.addVoter(name);
    }

    @PutMapping("/vote")
    public void vote(@RequestParam(name="id") Long id, @RequestParam(name="candidateId") Long candidateId, Model model){
        FakeDB.INSTANCE.castVote(id, candidateId);
    }
}