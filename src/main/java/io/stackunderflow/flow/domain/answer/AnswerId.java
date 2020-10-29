package io.stackunderflow.flow.domain.answer;

import io.stackunderflow.flow.domain.Id;

import java.util.UUID;

public class AnswerId extends Id{
    public AnswerId(){
        super();
    }

    public AnswerId(String id){
        super(id);
    }

    public AnswerId(UUID id){
        super(id);
    }
}
