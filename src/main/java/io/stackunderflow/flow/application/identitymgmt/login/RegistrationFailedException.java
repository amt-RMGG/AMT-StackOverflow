package io.stackunderflow.flow.application.identitymgmt.login;

import io.stackunderflow.flow.application.BusinessException;
import lombok.Value;

@Value
public class RegistrationFailedException extends BusinessException {
    public RegistrationFailedException(String message) {
        super(message);
    }
}
