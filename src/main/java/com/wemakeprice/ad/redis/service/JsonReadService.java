package com.wemakeprice.ad.redis.service;









import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;


@Service
public class JsonReadService {


    String filePath="/Users/we/work/work/wonder.json";
//    String filePath="/Users/we/work/work/wonder_sample.json";


    String sURL = "https://adcenter-api-dev.wemakeprice.com/ad/active/wonder";


    public JSONArray readJson() {
        JSONArray ads=null;

        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);

            ads = (JSONArray) jsonObject.get("ads");
/*
            for (int i=0; i<ads.size(); i++) {
                System.out.println("ads_index : " + i + " / " + ads.get(i));
            }
*/

/*
            Iterator i = ads.iterator();

            while(i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                JSONArray deals = (JSONArray) innerObj.get("deals");
                System.out.println("keyword : " + innerObj.get("keyword"));


                for (int j=0; j<deals.size(); j++) {

                    JSONObject dealDetail = (JSONObject) deals.get(j);
                    System.out.println("deals index : " + j + " / order :  "+ dealDetail.get("order") + " / " + dealDetail);




                }
            }
*/


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ads;
    }



    public JSONArray readJsonUrl() throws IOException {
        JSONArray ads=null;

        URL url = new URL(sURL);

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(url.openStream()));
            ads = (JSONArray) jsonObject.get("ads");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ads;
    }


}
