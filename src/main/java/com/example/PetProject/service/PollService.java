package com.example.PetProject.service;

import com.example.PetProject.exceptions.BadRequestException;
import com.example.PetProject.exceptions.ResourceNotFoundException;
import com.example.PetProject.models.ChoiceVoteCount;
import com.example.PetProject.models.Poll;
import com.example.PetProject.models.User;
import com.example.PetProject.models.Vote;
import com.example.PetProject.payload.PagedResponse;
import com.example.PetProject.payload.PollResponse;
import com.example.PetProject.repository.PollRepository;
import com.example.PetProject.repository.UserRepository;
import com.example.PetProject.repository.VoteRepository;
import com.example.PetProject.security.UserPrincipal;
import com.example.PetProject.utils.AppConstants;
import com.example.PetProject.utils.ModelMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PollService {
    private final PollRepository pollRepository;

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(PollService.class);

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page cannot be less than 0");
        }

        if (size < 0) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    private Map<Long, Long> getChoiceVoteCountMap(List<Long> pollIds) {
        List<ChoiceVoteCount> votes =  voteRepository.countByPollIdInGroupByChoiceId(pollIds);

        Map<Long, Long> choiceVotesMap = votes.stream()
                .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

        return choiceVotesMap;
    }

    private Map<Long, Long> getPollUserVoteMap(UserPrincipal currentUser, List<Long> pollIds) {
        Map<Long, Long> pollUserVoteMap = null;

        if (currentUser != null) {
            List<Vote> userVotes = voteRepository.findByUserIdAndPollIdIn(currentUser.getId(), pollIds);

            pollUserVoteMap = userVotes.stream()
                    .collect(Collectors.toMap(vote -> vote.getPoll().getId(), vote -> vote.getChoice().getId()));

        }
        return pollUserVoteMap;
    }

    private Map<Long, User> getPollCreatorMap(List<Poll> polls) {
        List<Long> creatorIds = polls.stream()
                .map(Poll::getCreatedBy)
                .distinct()
                .toList();

        List<User> creators = userRepository.findByIdIn(creatorIds);
        Map<Long, User> creatorMap = creators.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        return creatorMap;
    }
    public PagedResponse<PollResponse> getAllPolls(UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        //Retrieve polls
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Poll> polls = pollRepository.findAll(pageable);

        if (polls.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), polls.getNumber(),
                    polls.getSize(), polls.getTotalElements(), polls.getTotalPages(), polls.isLast());
        }

        //Map polls to Polls Response
        List<Long> pollIds = polls.map(Poll::getId).getContent();
        Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
        Map<Long, Long> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);
        Map<Long, User> creatorMap = getPollCreatorMap(polls.getContent());

        List<PollResponse> pollResponses = polls.map(poll -> {
            return ModelMapper.mapPollToPollResponse(poll,
                    choiceVoteCountMap,
                    creatorMap.get(poll.getCreatedBy()),
                    pollUserVoteMap == null ? null :pollUserVoteMap.getOrDefault(poll.getId(), null));
        }).getContent();

        return new PagedResponse<>(pollResponses, polls.getNumber(),
                polls.getSize(), polls.getTotalElements(), polls.getTotalPages(), polls.isLast());
    }

    public PagedResponse<PollResponse> getPollsCreatedBy(String username, UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Poll> polls = pollRepository.findByCreatedBy(user.getId(), pageable);

        if (polls.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), polls.getNumber(),
                    polls.getSize(), polls.getTotalElements(), polls.getTotalPages(), polls.isLast());
        }
        //TODO Finish Poll mapping and other merhods
        List<Long> pollIds = polls.map(Poll::getId).getContent();
        return null;
    }
}
