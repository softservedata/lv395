package com.softserve.edu.rest.entity;

import java.util.Arrays;

public class SimpleArrayEntity {

    private String[] content;

    // TODO Return List
    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SimpleArrayEntity [content=" + Arrays.toString(content) + "]";
    }

}
