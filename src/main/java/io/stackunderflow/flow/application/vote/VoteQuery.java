package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.domain.question.QuestionId;
import io.stackunderflow.flow.domain.vote.VoteType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class VoteQuery {
    private String username;
    private QuestionId questionId;
    private VoteType type;
}
