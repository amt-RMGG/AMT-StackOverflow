package io.stackunderflow.flow.domain.answer;

import io.stackunderflow.flow.domain.IEntity;
import lombok.*;

@Data // bundles of @ToString, @EqualsAndHashCode, @Getter
@Builder(toBuilder = true) //https://projectlombok.org/features/Builder
public class Answer implements IEntity<Answer, AnswerId> {

    @Setter(AccessLevel.NONE)
    private AnswerId id = new AnswerId();
    private String author;
    private String text;
    private String date;
    private String questionId;

    @Override
    public Answer deepClone() {
        return this.toBuilder()
                .id(new AnswerId(id.asString()))
                .build();
    }

    //Override lombok build method to : enforce the presence of an id for the entity
    public static class AnswerBuilder {
        public Answer build(){
            if(id == null)
                id = new AnswerId();

            if(text == null)
                text = "";

            if(date == null)
                date = "";

            if(questionId == null) {
                questionId = "";
            }


            return new Answer(id, author, text, date, questionId);
        }
    }

}
