package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LoginResource extends RestQueries<SimpleEntity> {

    public LoginResource() {
        super(RestUrlRepository.getLogin(), SimpleEntity.class);
    }

}
