package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
    private Board board;
    private User user;
    private User dummy;
    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        board = new Board("Java");
        user = new User(board, "User");
        dummy = new User(board, "Dummy");
        question = new Question(user, "question");
        board.addQuestion(question);
        answer = new Answer(question, dummy, "answer");
        board.addAnswer(answer);
    }

    @Test
    void upvotingQuestionGetsProperReputation() {
        dummy.upVote(question);

        assertEquals(5, user.getReputation());
    }

    @Test
    void upvotingAnswerGetsProperReputation() {
        user.upVote(answer);

        assertEquals(10, dummy.getReputation());
    }

    @Test
    void downvotingAnswerGetsProperReputation() {
        user.downVote(answer);

        assertEquals(-1, dummy.getReputation());
    }

    @Test
    void accceptingAnswerGetsProperReputation() {
        user.acceptAnswer(answer);

        assertEquals(15, dummy.getReputation());
    }


    @Test
    void upvotingQuestionByOriginalAuthorNotAllowed() {
        assertThrows(VotingException.class, () ->
                user.upVote(question)
        );
    }

    @Test
    void downvotingQuestionByOriginalAuthorNotAllowed() {
        assertThrows(VotingException.class, () ->
                user.downVote(question)
        );
    }

    @Test
    void upvotingAnswerByOriginalAuthorNotAllowed() {
        assertThrows(VotingException.class, () ->
                dummy.upVote(answer)
        );
    }

    @Test
    void downvotingAnswerByOriginalAuthorNotAllowed() {
        assertThrows(VotingException.class, () ->
                dummy.downVote(answer)
        );
    }

    @Test
    void onlyOriginalAuthorCanAcceptAnswer() {
        assertThrows(AnswerAcceptanceException.class, () ->
                dummy.acceptAnswer(answer)
        );
    }
}