package com.teamtreehouse.techdegree.overboard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    Board board;
    private User user;
    private User dummy;

    @BeforeEach
    void setUp() {
        board = new Board("Java");
        user = new User(board, "User");
        dummy = new User(board, "Dummy");
    }

    @Test
    void upvotingQuestionGetsProperReputation() {
        Question question = new Question(user, "question");
        board.addQuestion(question);

        dummy.upVote(question);

        assertEquals(5, user.getReputation());
    }
}