package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class UsersResourse extends RestQueries<SimpleEntity> {
    public UsersResourse() {
        super(RestUrlRepository.getUsers(),SimpleEntity.class);
    }
}
