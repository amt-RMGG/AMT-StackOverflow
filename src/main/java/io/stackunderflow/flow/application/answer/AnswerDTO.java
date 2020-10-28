package io.stackunderflow.flow.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AnswerDTO {
    private String id;
    private String text;
    private String author;
}

