package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LogoutResource extends RestQueries<SimpleEntity> {

    public LogoutResource() {
        super(RestUrlRepository.getLogout(), SimpleEntity.class);
    }

}
