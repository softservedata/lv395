package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LockedUserResource extends RestQueries<SimpleEntity> {
    public LockedUserResource() {
        super(RestUrlRepository.lockUnlockUser(), SimpleEntity.class);
    }
}
