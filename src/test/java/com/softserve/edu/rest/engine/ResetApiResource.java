package com.softserve.edu.rest.engine;

import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleEntity;

public class ResetApiResource extends RestQueries<SimpleEntity> {
    public ResetApiResource() {
        super(RestUrlRepository.resetServiceToInitialState(), SimpleEntity.class);
    }

}