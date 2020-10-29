package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.domain.vote.VoteType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeVoteCommand {
    @Builder.Default
    private String user = "Anonymous";

    @Builder.Default
    private String object = "No question or answer";

    @Builder.Default
    private VoteType type = VoteType.DEFAULT;
}
