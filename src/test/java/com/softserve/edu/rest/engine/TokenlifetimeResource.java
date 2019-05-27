package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class TokenlifetimeResource extends RestQueries<SimpleEntity> {

    public TokenlifetimeResource() {
        super(RestUrlRepository.getTokenLifetime(), SimpleEntity.class);
    }

}
