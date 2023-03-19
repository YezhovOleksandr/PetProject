package com.example.PetProject.controllers;

import com.example.PetProject.repository.PollRepository;
import com.example.PetProject.repository.UserRepository;
import com.example.PetProject.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls")
@AllArgsConstructor
public class PollController {

    private final PollRepository pollRepository;

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;


}
