package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class CooldownResource extends RestQueries<SimpleEntity> {
    public CooldownResource() {
        super(RestUrlRepository.getCooldownTime(),SimpleEntity.class);
    }
}
