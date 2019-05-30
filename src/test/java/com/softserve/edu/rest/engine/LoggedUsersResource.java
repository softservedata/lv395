package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LoggedUsersResource extends RestQueries<SimpleEntity> {
    public LoggedUsersResource() {
        super(RestUrlRepository.getLoggedUsers(), SimpleEntity.class);
    }
}
