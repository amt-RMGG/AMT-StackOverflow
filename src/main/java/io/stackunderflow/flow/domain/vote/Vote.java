package io.stackunderflow.flow.domain.vote;

import io.stackunderflow.flow.domain.IEntity;
import io.stackunderflow.flow.domain.question.QuestionId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data // bundles of @ToString, @EqualsAndHashCode, @Getter
@Builder(toBuilder = true) //https://projectlombok.org/features/Builder
public class Vote implements IEntity {

    @Setter(AccessLevel.NONE)
    private String username;
    private QuestionId idQuestion = new QuestionId();
    private int vote = 0;
    private VoteType type;
    private String objectType;

    @Override
    public VoteID getId() {
        return null;
    }

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .username(username)
                .idQuestion(idQuestion)
                .build();
    }

    public static class VoteBuilder {
        public Vote build() {
            if(idQuestion == null)
                return null;

            if(type == VoteType.DEFAULT)
                vote = 0;
            else if(type == VoteType.DOWNVOTE)
                vote = -1;
            else if(type == VoteType.UPVOTE)
                vote = 1;
            else
                return null;

            return new Vote(username, idQuestion, vote, type, objectType);
        }
    }
}
