package com.client.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.oracle.javafx.jmx.json.JSONException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

/**
 * Created by z00382545 on 10/11/16.
 */
public interface HttpService {
    void getResource() throws IOException, JSONException;

    JsonNode responseParser(CloseableHttpResponse response) throws IOException, JSONException;
}
