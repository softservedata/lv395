package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class LoggedAdminsResource extends RestQueries<SimpleEntity> {
    public LoggedAdminsResource() {
        super(RestUrlRepository.getLoginAdmins(), SimpleEntity.class);
    }
}
