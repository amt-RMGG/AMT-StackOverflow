package io.stackunderflow.flow.application.gamification.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class User {
    final private String username;
    final private int experienceValue;
}