package com.softserve.edu.rest.engine;

import com.google.gson.Gson;
import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.entity.RestParameters;
import org.apache.log4j.Logger;

public abstract class RestQueries<T> extends RestCRUD {
    protected final Logger log = Logger.getLogger(this.getClass());
    private Class<T> clazz;
    private Gson gson;


    protected RestQueries(RestUrl restUrl, Class<T> clazz) {
        super(restUrl);
        this.clazz = clazz;  // TODO Get Class<T> from <T>
        gson = new Gson();
    }

    private T ConvertToEntity(String json) {
        log.info("json: " + json + " clazz: " + clazz.getName());
        return gson.fromJson(json, clazz);
    }

    public T httpGetAsEntity(RestParameters pathVariables, RestParameters urlParameters) {
        return ConvertToEntity(httpGetAsText(pathVariables, urlParameters));
    }

    public T httpPostAsEntity(RestParameters pathVariables, RestParameters urlParameters,
                              RestParameters bodyParameters) {
        return ConvertToEntity(httpPostAsText(pathVariables, urlParameters, bodyParameters));
    }

    public T httpPutAsEntity(RestParameters pathVariables, RestParameters urlParameters,
                             RestParameters bodyParameters) {
        return ConvertToEntity(httpPutAsText(pathVariables, urlParameters, bodyParameters));
    }

    public T httpDeleteAsEntity(RestParameters pathVariables, RestParameters urlParameters,
                                RestParameters bodyParameters) {
        return ConvertToEntity(httpDeleteAsText(pathVariables, urlParameters, bodyParameters));
    }

}
