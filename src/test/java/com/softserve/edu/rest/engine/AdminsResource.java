package com.softserve.edu.rest.engine;

import com.gargoylesoftware.htmlunit.javascript.host.SimpleArray;
import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.data.RestUrlRepository;
import com.softserve.edu.rest.entity.SimpleArrayEntity;
import com.softserve.edu.rest.entity.SimpleEntity;

public class AdminsResource extends RestQueries<SimpleEntity> {
    public AdminsResource() {
        super(RestUrlRepository.getAdmins(), SimpleEntity.class);
    }

}
