package io.stackunderflow.flow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import java.util.List;


//Questions Data Transfer Object
@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private String text;
        private String author;
        private String title;
    }

    @Singular //generate an "add()" method
    private List<QuestionDTO> questions;

}
