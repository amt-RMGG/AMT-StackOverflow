package io.stackunderflow.flow.application.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Badge {
    private int id;
    private String name;
    private int experienceValue;
}
