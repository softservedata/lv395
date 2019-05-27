package com.softserve.edu.rest.data;

// Resource
public class RestUrl {

    private String urlServer;
    private String uriGet;
    private String uriPost;
    private String uriPut;
    private String uriDelete;

    // TODO USe Builder
    public RestUrl(String urlServer, String uriGet, String uriPost,
            String uriPut, String uriDelete) {
        this.urlServer = urlServer;
        this.uriGet = uriGet;
        this.uriPost = uriPost;
        this.uriPut = uriPut;
        this.uriDelete = uriDelete;
    }

    public RestUrl() {
        urlServer = null;
        uriGet = null;
        uriPost = null;
        uriPut = null;
        uriDelete = null;
    }

    public RestUrl addUrlServer(String urlServer) {
        this.urlServer = urlServer;
        return this;
    }

    public RestUrl addUriGet(String uriGet) {
        this.uriGet = uriGet;
        return this;
    }

    public RestUrl addUriPost(String uriPost) {
        this.uriPost = uriPost;
        return this;
    }

    public RestUrl addUriPut(String uriPut) {
        this.uriPut = uriPut;
        return this;
    }

    public RestUrl addUriDelete(String uriDelete) {
        this.uriDelete = uriDelete;
        return this;
    }

    // getters

    public String getUrlServer() {
        return urlServer;
    }

    public String getUriGet() {
        return uriGet;
    }

    public String getUriPost() {
        return uriPost;
    }

    public String getUriPut() {
        return uriPut;
    }

    public String getUriDelete() {
        return uriDelete;
    }

    public String getUrlGet() {
        return getUrlServer() + getUriGet();
    }

    public String getUrlPost() {
        return getUrlServer() + getUriPost();
    }

    public String getUrlPut() {
        return getUrlServer() + getUriPut();
    }

    public String getUrlDelete() {
        return getUrlServer() + getUriDelete();
    }

}
