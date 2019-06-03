package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LockUnlockUserResource extends RestQueries<SimpleEntity> {
    public LockUnlockUserResource() {
        super(RestUrlRepository.lockUnlockUser(), SimpleEntity.class);
    }
}
