package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LockedUsersResource extends RestQueries<SimpleEntity> {
    public LockedUsersResource() {
        super(RestUrlRepository.getLockedUsers(), SimpleEntity.class);
    }
}
