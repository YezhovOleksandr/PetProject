package com.example.PetProject.utils;

import com.example.PetProject.models.Poll;
import com.example.PetProject.models.User;
import com.example.PetProject.payload.ChoiceRequest;
import com.example.PetProject.payload.ChoiceResponse;
import com.example.PetProject.payload.PollResponse;
import com.example.PetProject.payload.UserSummary;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVoteMap, User creator, Long userVote ) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(pollResponse.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationTime());
        Instant now = Instant.now();
        pollResponse.setExpired(poll.getExpirationTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());

            if (choiceVoteMap.containsKey(choice.getId())) {
                choiceResponse.setVoteCount(choiceVoteMap.get(choice.getId()));
            } else {
                choiceResponse.setVoteCount(0);
            }
            return choiceResponse;
        }).toList();

        pollResponse.setChoices(choiceResponses);
        UserSummary userSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreatedBy(userSummary);

        if (userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }
}
