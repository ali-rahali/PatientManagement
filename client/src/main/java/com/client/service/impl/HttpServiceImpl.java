package com.client.service.impl;

import com.client.domain.Patient;
import com.client.service.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by z00382545 on 10/11/16.
 */

@Service
public class HttpServiceImpl implements HttpService{

    String credential = "Basic "+ Base64.getEncoder().encodeToString("acme:acmesecret".getBytes());

    public JSONObject getToken(String authCode) throws IOException, JSONException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8081/oauth/token");

        httpPost.setHeader("Authorization", credential);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id", "acme"));
        params.add(new BasicNameValuePair("redirect_uri", "http://127.0.0.1:8080/authCode"));
        params.add(new BasicNameValuePair("code", authCode));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = client.execute(httpPost);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

        JSONObject jsonObject = new JSONObject(result.toString());

        client.close();

        return jsonObject;
    }

    public List<Patient> getResource(String token) throws IOException, JSONException {
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
        httpGet.setHeader("Authorization", "bearer "+token);
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
