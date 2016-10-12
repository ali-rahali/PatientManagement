package com.client.service.impl;

import com.client.domain.Patient;
import com.client.service.HttpService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by z00382545 on 10/11/16.
 */

@Service
public class HttpServiceImpl implements HttpService{

    public List<Patient> getResource() throws IOException, JSONException {
        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://localhost:8080/auth/realms/demo/protocol/openid-connect/token");
//        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("client_id", "admin-cli"));
//        params.add(new BasicNameValuePair("client_secret", "2a05c119-6254-481a-9b0d-a3f6993c85cc"));
//
//        params.add(new BasicNameValuePair("username", "ldeng"));
//        params.add(new BasicNameValuePair("password", "password"));
//        params.add(new BasicNameValuePair("grant_type", "password"));

//        httpPost.setEntity(new UrlEncodedFormEntity(params));

//        CloseableHttpResponse response = client.execute(httpPost);

        HttpGet httpGet = new HttpGet("http://localhost:8082/resource/patient/all");
        CloseableHttpResponse response = client.execute(httpGet);

        return responseParser(response);

    }


    public List<Patient> responseParser(CloseableHttpResponse response) throws IOException, JSONException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        List<Patient> patientList=new ArrayList<>();

        JSONArray jsonarray = new JSONArray(result.toString());

        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Patient patient = mapper.readValue(jsonobject.toString(), Patient.class);
            patientList.add(patient);
        }

        return patientList;

    }
}
