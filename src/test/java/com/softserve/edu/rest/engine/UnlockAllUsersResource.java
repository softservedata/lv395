package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class UnlockAllUsersResource extends RestQueries<SimpleEntity> {
    public UnlockAllUsersResource(){
        super(RestUrlRepository.unlockAllUsers(), SimpleEntity.class);
    }

}
