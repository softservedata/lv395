package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class UserResource extends RestQueries<SimpleEntity> {

    public UserResource() {
        super(RestUrlRepository.getUser(), SimpleEntity.class);
    }

}