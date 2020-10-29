package io.stackunderflow.flow.domain.vote;

import io.stackunderflow.flow.domain.Id;
import java.util.UUID;

public class VoteID extends Id {

    public void VoteId(){
        super();
    }

    public void VoteId(String id){
        super(id);
    }

    public void VoteId(UUID id){
        super(id);
    }
}
