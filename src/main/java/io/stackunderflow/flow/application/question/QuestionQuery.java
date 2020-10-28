package io.stackunderflow.flow.application.question;

import io.stackunderflow.flow.domain.question.QuestionId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionQuery {
    private QuestionId id;
    private String author;
}
