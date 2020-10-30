package io.stackunderflow.flow.infrastructure.persistence.jdbc;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.vote.VoteQuery;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.answer.AnswerId;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
import io.stackunderflow.flow.domain.vote.Vote;
import io.stackunderflow.flow.domain.vote.VoteID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcVoteRepository")
public class JdbcVoteRepository extends JdbcRepository implements IVoteRepository {

    public JdbcVoteRepository(){}

    @Override
    public Collection<Vote> find(VoteQuery query) {
        /*Collection<Vote> votes = new LinkedList<>();

        if(query.getQuestionId() != null && query.getUsername() != null) {
            Optional<Vote> q = findById(query.getQuestionId());

            if(!q.isEmpty()) {
                votes.add(q.get());
            }
        }

        return votes;*/
        return null;
    }

    @Override
    public void save(Vote entity) throws RegistrationFailedException {

        try {
            String query = "INSERT INTO questionVote (questionId, userId, vote) VALUES(?, ?, ?)";
            super.executeInsertQuery(query, entity.getIdQuestion().asString(), entity.getUsername(), entity.getVote());

        }catch(RegistrationFailedException e){
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    @Override
    public void saveAnswerVote(Vote entity) throws RegistrationFailedException {
        try {
            String query = "INSERT INTO answerVote (userId, answerId, vote) VALUES(?, ?, ?)";
            super.executeInsertQuery(query, entity.getUsername(), entity.getIdQuestion().asString(), entity.getVote());

        }catch(RegistrationFailedException e){
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    @Override
    public boolean checkIfVoteExistQuestion(String idQuestion, String username) {
        try {
            ResultSet rs = super.fetchData("SELECT * FROM questionVote WHERE userId = ? AND questionId = ?", username, idQuestion);
            if(rs.next())
                return true;
            else
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean checkIfVoteExistAnswer(String idAnswer, String username) {
        try {
            ResultSet rs = super.fetchData("SELECT * FROM answerVote WHERE userId = ? AND answerId = ?", username, idAnswer);
            if(rs.next())
                return true;
            else
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public void remove(VoteID id) {

    }

    @Override
    public Optional<Vote> findById(VoteID id) {
        ResultSet rs = super.fetchData("SELECT * FROM questionVote WHERE id = ?", id.asString());
        Vote v = null;

        try {
            if(rs.next()) {
                String username = rs.getString(2);
                String questionID = rs.getString(3);
                int vote = rs.getInt(4);
                v = Vote.builder()
                        .username(username)
                        .idQuestion(new QuestionId(questionID))
                        .vote(vote)
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("error : " + e.getMessage());
        }

        if(v == null)
            return Optional.empty();
        else
            return Optional.of(v);
    }

    @Override
    public Collection<Vote> findAll() {
        return null;
    }
}
