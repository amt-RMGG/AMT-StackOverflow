package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.domain.person.PersonId;
import io.stackunderflow.flow.domain.question.QuestionId;
import io.stackunderflow.flow.domain.vote.VoteType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class VoteDTO {
    @Builder
    @Getter
    @EqualsAndHashCode
    public static class ProposedVoteDTO {
        private String username;
        private QuestionId questionID;
        private VoteType type;
    }

    @Singular
    private List<ProposedVoteDTO> votes;
}
