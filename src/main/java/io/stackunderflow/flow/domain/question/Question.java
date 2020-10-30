package io.stackunderflow.flow.domain.question;

import io.stackunderflow.flow.domain.IEntity;
import io.stackunderflow.flow.domain.answer.Answer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedList;

@Data // bundles of @ToString, @EqualsAndHashCode, @Getter
@Builder(toBuilder = true) //https://projectlombok.org/features/Builder
public class Question implements IEntity<Question, QuestionId> {

    @Setter(AccessLevel.NONE)
    private QuestionId id = new QuestionId();
    private String author;
    private String text;
    private String title;
    private Collection<Answer> answers;
    private String date;

    @Setter(AccessLevel.NONE)
    private QuestionType questionType;

    public void categorizeAs(QuestionType type){
        this.questionType = type;
    }


    @Override
    public Question deepClone() {
        return this.toBuilder()
                .id(new QuestionId(id.asString()))
                .build();
    }

    //Override lombok build method to : enforce the presence of an id for the entity
    //and assign a default question type if none is provided
    public static class QuestionBuilder {
        public Question build(){
            if(id == null)
                id = new QuestionId();

            if(text == null)
                text = "";

            if(title == null)
                title = "";

            if(questionType == null){
                questionType = QuestionType.DEFAULT;
            }
            if(answers == null)
                answers = new LinkedList<>();

            if(date == null)
                date = "";


            return new Question(id, author, text, title, answers, date, questionType);
        }
    }

}
