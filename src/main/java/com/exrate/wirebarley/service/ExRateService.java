package com.exrate.wirebarley.service;

import com.exrate.wirebarley.common.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

@Service
public class ExRateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 화폐간 환율을 가져옴
     * @param source        송금국가
     * @param currencies    수위국가
     * @return
     * @throws Exception
     */
    public ModelMap getExRate(String source, String currencies) throws Exception {

        if(source == null || currencies == null || source+currencies == ""){
            throw new Exception();
        }
        ModelMap result = new ModelMap();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        try {
            Request request = new Request.Builder()
                    .url(Constants.API_CURRENCY_URL + "?source=" + source + "&currencies=" + currencies)
                    .addHeader("apikey", Constants.API_KEY_VALUE)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();

            Map<String, Object> map = jsonToMap(response.body().string());

            //환율 문자열
            String rateStr = (map.get("quotes").toString().replaceAll("}","")).split("=")[1];

            result.put("success", map.get("success"));
            result.put("result", formNumber( rateStr ) );
            result.put("message",null);

        } catch (Exception e) {
            logger.error("HttpClient Error", e);
            result.put("success", false);
            result.put("result",null);
            result.put("message",Constants.SERVER_ERR);
        }
        return result;
    }

    /**
     * 화폐간 환율을 가져옴과 동시에 가격 계산
     * @param source        송금국가
     * @param currencies    수위국가
     * @return
     * @throws Exception
     */
    public ModelMap CalculatedExRate(String source, String currencies, Float amount) throws Exception {

        if(source == null || currencies == null || source+currencies == ""){
            throw new Exception();
        }
        ModelMap result = new ModelMap();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        try {
            Request request = new Request.Builder()
                    .url(Constants.API_CURRENCY_URL + "?source=" + source + "&currencies=" + currencies)
                    .addHeader("apikey", Constants.API_KEY_VALUE)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();

            Map<String, Object> map = jsonToMap(response.body().string());

            //환율 문자열
            String rateStr = (map.get("quotes").toString().replaceAll("}","")).split("=")[1];
            result.put("success", map.get("success"));
            result.put("rate", formNumber( rateStr) );
            result.put("result", formNumber( String.valueOf(Float.parseFloat(formNumberSimple(rateStr)) * amount) ));
            result.put("message",null);

        } catch (Exception e) {
            logger.error("HttpClient Error", e);
            result.put("success", false);
            result.put("result",null);
            result.put("message",Constants.SERVER_ERR);
        }

        return result;
    }

    /**
     * json String을 Map으로 변환
     * @param jsonData
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Map<String, Object> jsonToMap(String jsonData) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, Map.class);
    }

    /**
     * 받은 환율을 소수점 두자리로 정리하고 숫자 세자리마다 콤마
     * @param num
     * @return
     */
    public String formNumber(String num){
        DecimalFormat numForm = new DecimalFormat("###,###.##");
        String result = "";
        result = numForm.format( Float.parseFloat(num) );
        return result;
    }

    /**
     * 받은 환율을 소수점 두자리로 정리
     * @param num
     * @return
     */
    public String formNumberSimple(String num){
        DecimalFormat numForm = new DecimalFormat("######.##");
        String result = "";
        result = numForm.format( Float.parseFloat(num) );
        return result;
    }

}
