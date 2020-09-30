package io.stackunderflow.flow.application.identitymgmt.authenticate;

import io.stackunderflow.flow.application.BusinessException;

public class AuthenticationFailedException extends BusinessException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
