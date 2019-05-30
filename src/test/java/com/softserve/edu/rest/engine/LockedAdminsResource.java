package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LockedAdminsResource extends RestQueries<SimpleEntity> {
    public LockedAdminsResource() {
        super(RestUrlRepository.getLockedAdmins(),SimpleEntity.class);
    }
}
