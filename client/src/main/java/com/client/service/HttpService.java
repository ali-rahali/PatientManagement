package com.client.service;

import com.client.domain.Patient;
import com.oracle.javafx.jmx.json.JSONException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by z00382545 on 10/11/16.
 */
public interface HttpService {
    List<Patient> getResource() throws IOException, JSONException;

    List<Patient> responseParser(CloseableHttpResponse response) throws IOException, JSONException;
}
