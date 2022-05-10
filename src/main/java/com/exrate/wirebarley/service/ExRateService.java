package com.exrate.wirebarley.service;

import com.exrate.wirebarley.common.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExRateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map<String, Object> getExRate(String source, String currencies) throws Exception {

        if(source == null || currencies == null || source+currencies == ""){
            throw new Exception();
        }
        Map<String, Object> result = new HashMap<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        try {
            Request request = new Request.Builder()
                    .url(Constants.API_CURRENCY_URL + "?source=" + source + "&currencies=" + currencies)
                    .addHeader("apikey", Constants.API_KEY_VALUE)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());

        } catch (Exception e) {
            logger.error("HttpClient Error", e);
            result.put("status", 999);
            result.put("success", false);
            result.put("detail",e);
            result.put("message",e);
        } finally{
        }

        return result;
    }

    public Map<String, Object> jsonToMap(String jsonData) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, Map.class);
    }
}
