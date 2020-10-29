package io.stackunderflow.flow.application.identitymgmt;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class UserQuery {
    private String username;
}
