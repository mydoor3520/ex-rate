package com.exrate.wirebarley.service;

import com.exrate.wirebarley.common.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ExRateServiceTest {
    @Test
    @DisplayName("API 호출 테스트")
    void GetExRateTest() throws IOException {
        String source = "KRW";
        String currencies = "USD";

        Map<String, Object> result = new HashMap<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url(Constants.API_CURRENCY_URL + "?source=" + source + "&currencies=" + currencies)
                    .addHeader("apikey", Constants.API_KEY_VALUE)
                    .method("GET", null)
                    .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        System.out.println(jsonData);
        result = jsonToMap(jsonData);
        Assertions.assertTrue((boolean)result.get("success") == true);
    }

    public Map<String, Object> jsonToMap(String jsonData) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, Map.class);
    }

    @Test
    @DisplayName("숫자 format 테스트")
    public void formNumberTest(){
        String num = "55324.3443";
        DecimalFormat numForm = new DecimalFormat("###,###.##");
        String result = "";
        result = numForm.format( Float.parseFloat(num) );
        System.out.println("format :" + result);
    }
}