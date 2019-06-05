package com.softserve.edu.rest.engine;

import java.io.IOException;
import java.util.regex.Pattern;

import com.softserve.edu.rest.data.RestUrl;
import com.softserve.edu.rest.entity.RestParameters;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.log4j.Logger;

public abstract class RestCRUD {

    private RestUrl restUrl;
    private OkHttpClient httpClient;
    protected final Logger log = Logger.getLogger(this.getClass());

    protected RestCRUD(RestUrl restUrl) {
        this.restUrl = restUrl;
        httpClient = new OkHttpClient();
    }

    protected void throwException(String message) {
        // TODO Develop Custom Exception
        throw new RuntimeException(
                String.format("Method %s not Support for Resource %s", message, this.getClass().getName()));
    }

    private String prepareUrlParameters(String urlTemplate, RestParameters urlParameters) {
        String url = urlTemplate;
       //System.out.println(urlParameters.getAllParameters().size());
        log.info(urlParameters==null);
        if (urlParameters != null) {
            boolean isFirstParameter = true;
            for (String currentKey : urlParameters.getAllParameters().keySet()) {
                url = url + (isFirstParameter ? "?" : "&");
                url = url + currentKey + "=" + urlParameters.getParameter(currentKey);
                isFirstParameter = false;
            }
        }
        return url;
    }

    private String preparePathVariables(String urlTemplate, RestParameters pathVariables) {
        String url = urlTemplate;
        String searchVariable;
        if (pathVariables != null) {
            for (String currentKey : pathVariables.getAllParameters().keySet()) {
                searchVariable = "{" + currentKey + "}";
                if (url.contains(searchVariable)) {
                    url = url.replaceFirst(Pattern.quote(searchVariable), pathVariables.getParameter(currentKey));
                }
            }
        }
        return url;
    }

    private RequestBody prepareRequestBody(RestParameters bodyParameters) {
        RequestBody requestBody = null;
        if (bodyParameters != null) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            for (String currentKey : bodyParameters.getAllParameters().keySet()) {
                formBodyBuilder.add(currentKey, bodyParameters.getParameter(currentKey));
            }
            requestBody = formBodyBuilder.build();
        }
        return requestBody;
    }

    private Request.Builder prepareRequestBuilder(String requestUrl, RestParameters pathVariables,
            RestParameters urlParameters) {
        if ((requestUrl == null) || (requestUrl.isEmpty())) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String methodName = stackTrace[2].getMethodName();
            // TODO User Logger
            log.info("MethodName = " + methodName);
            log.info("Method = " + methodName.substring(4).replace("AsResponse", ""));
            //System.out.println("MethodName = " + methodName);
            //System.out.println("Method = " + methodName.substring(4).replace("AsResponse", ""));
            throwException(methodName.substring(4).replace("AsResponse", ""));
        }
        String url = preparePathVariables(requestUrl, pathVariables);
        url = prepareUrlParameters(url, urlParameters);
        log.info("URL: " + url);
        return new Request.Builder().url(url);
    }

    private Response executeRequest(Request request) {
        Response result = null;
        try {
            result = httpClient.newCall(request).execute();
        } catch (IOException e) {
            // TODO Develop Custom Exception + Log
            throw new RuntimeException("Request Error " + e.toString());
        }
        return result;
    }

    private String responseBodyAsText(ResponseBody responseBody) {
        String responseText = null;
        try {
            responseText = responseBody.string();
        } catch (IOException e) {
            // TODO Develop Custom Exception + Log
            // e.printStackTrace();
            throw new RuntimeException("Error to get text from ResponseBody." + e.toString());
        }
        return responseText;
    }

    // Http Get - - - - - - - - - - - - - - - - - - - -

    public Response httpGetAsResponse(RestParameters pathVariables, RestParameters urlParameters) {
        return executeRequest(prepareRequestBuilder(restUrl.getUrlGet(), pathVariables, urlParameters).get().build());
    }

    public String httpGetAsText(RestParameters pathVariables, RestParameters urlParameters) {
        return responseBodyAsText(httpGetAsResponse(pathVariables, urlParameters).body());
    }

    // Http Post - - - - - - - - - - - - - - - - - - - -

    public Response httpPostAsResponse(RestParameters pathVariables, RestParameters urlParameters,
            RestParameters bodyParameters) {
        return executeRequest(prepareRequestBuilder(restUrl.getUrlPost(), pathVariables, urlParameters)
                .post(prepareRequestBody(bodyParameters)).build());
    }

    public String httpPostAsText(RestParameters pathVariables, RestParameters urlParameters,
            RestParameters bodyParameters) {
        return responseBodyAsText(httpPostAsResponse(pathVariables, urlParameters, bodyParameters).body());
    }

    // Http Put - - - - - - - - - - - - - - - - - - - -

    public Response httpPutAsResponse(RestParameters pathVariables, RestParameters urlParameters,
            RestParameters bodyParameters) {
        return executeRequest(prepareRequestBuilder(restUrl.getUrlPut(), pathVariables, urlParameters)
                .put(prepareRequestBody(bodyParameters)).build());
    }

    public String httpPutAsText(RestParameters pathVariables, RestParameters urlParameters,
            RestParameters bodyParameters) {
        return responseBodyAsText(httpPutAsResponse(pathVariables, urlParameters, bodyParameters).body());
    }

    // Http Delete - - - - - - - - - - - - - - - - - - - -

    public Response httpDeleteAsResponse(RestParameters pathVariables, RestParameters urlParameters,
            RestParameters bodyParameters) {
        return executeRequest(prepareRequestBuilder(restUrl.getUrlDelete(), pathVariables, urlParameters)
                .delete(prepareRequestBody(bodyParameters)).build());
    }

    public String httpDeleteAsText(RestParameters pathVariables, RestParameters urlParameters,
            RestParameters bodyParameters) {
        return responseBodyAsText(httpDeleteAsResponse(pathVariables, urlParameters, bodyParameters).body());
    }

}
