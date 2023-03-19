package com.example.PetProject.repository;

import com.example.PetProject.models.ChoiceVoteCount;
import com.example.PetProject.models.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "SELECT NEW com.example.PetProject.models.ChoiceVoteCount(v.choice.id, count (v.id)) FROM Vote v WHERE v.poll.id in :pollIds GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);

    @Query(value = "SELECT NEW com.example.PetProject.models.ChoiceVoteCount(v.choice.id, count (v.id)) FROM Vote v WHERE v.poll.id = :pollId GROUP BY v.choice.id")
    List<ChoiceVoteCount> countByPollIdGroupByChoiceId(@Param("pollId") Long pollId);

    @Query(value = "SELECT v FROM Vote v WHERE v.user.id = :userId and v.poll.id IN :pollIds")
    List<Vote> findByUserIdAndPollIdIn(@Param("userId") Long userId, @Param("pollId") Long pollId);

    @Query(value = "SELECT v FROM Vote v WHERE v.user.id = :userId and v.poll.id = :pollId")
    Vote findByUserIdAndPollId(@Param("userid") Long userId, @Param("pollId") Long pollId);

    @Query(value = "SELECT count (v.id) FROM Vote v where v.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT v.poll.id FROM Vote v where v.user.id = :userId")
    Page<Long> findVotedPollIdsByUserId(@Param("userId") Long userId, Pageable pageable);
}