package io.stackunderflow.flow.domain.vote;

import io.stackunderflow.flow.domain.Id;

import java.util.UUID;

public class VoteID extends Id {

    public VoteID(){
        super();
    }

    public VoteID(String id){
        super(id);
    }

    public VoteID(UUID id){
        super(id);
    }
}
