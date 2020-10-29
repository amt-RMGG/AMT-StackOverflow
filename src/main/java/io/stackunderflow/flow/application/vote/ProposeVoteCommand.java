package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.domain.person.PersonId;
import io.stackunderflow.flow.domain.question.QuestionId;
import io.stackunderflow.flow.domain.vote.VoteType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeVoteCommand {
    @Builder.Default
    private String username = "Anonymous";

    @Builder.Default
    private QuestionId idQuestion = null;

    @Builder.Default
    private VoteType type = VoteType.DEFAULT;
}
