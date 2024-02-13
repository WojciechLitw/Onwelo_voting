package com.example.wli_vote_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WliVoteTestApplication {

	public static void main(String[] args) {
		FakeDB.INSTANCE.addCandidate("Tim Break");
		FakeDB.INSTANCE.addCandidate("Nicolas Noname");

		FakeDB.INSTANCE.addVoter("Mick");
		FakeDB.INSTANCE.addVoter("Jeff");
		FakeDB.INSTANCE.addVoter("Dom");

		FakeDB.INSTANCE.castVote(1,0);

		SpringApplication.run(WliVoteTestApplication.class, args);
	}

}
