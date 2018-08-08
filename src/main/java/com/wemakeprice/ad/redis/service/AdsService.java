package com.wemakeprice.ad.redis.service;

import com.wemakeprice.ad.redis.model.Ads;
import com.wemakeprice.ad.redis.repository.AdsRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

@Service
public class AdsService {


    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private JsonReadService jsonReadService;




    public void adsSave() {

        long startTime = (System.currentTimeMillis() / 1000L) * 1000L;
        long endTime=0;
        System.out.println("start_time : " + startTime);

        JSONArray ads = jsonReadService.readJson();

        Iterator i = ads.iterator();



        int x=0;

        while(i.hasNext()) {
            x+=1;

            JSONObject innerObj = (JSONObject) i.next();
            JSONArray deals = (JSONArray) innerObj.get("deals");

            String keyword = (innerObj.get("keyword") != null ? innerObj.get("keyword").toString() : "" ) ;
            keyword = keyword.trim();

            String valueDeals = deals.toJSONString();

            Ads redisAds  =  new Ads();
            redisAds.setKeyword(keyword);
            redisAds.setDesls(valueDeals);
//            System.out.println("keyword : " + keyword + " / valueDeals : " + valueDeals);

            adsRepository.save(redisAds);

        }

        endTime = (System.currentTimeMillis() / 1000L) * 1000L;
        System.out.println("end_time : " + endTime);

        System.out.println("count : " + x + "/  Time : " + (endTime-startTime));

    }




    public String adsFind(String keyword) {

        if (adsRepository.find(keyword) !=null) {
            System.out.println("adsRepository.find(keyword).toString() : " + adsRepository.find(keyword).toString());

            String result = adsRepository.find(keyword).toString();
            return result;
        } else {
            return "No Data";
        }

    }


    public void adsDelete(String keyword) {
        System.out.println("adsRepository.delete(keyword).toString() : " + adsRepository.find(keyword).toString());

        adsRepository.delete(keyword);


    }

    public void adsDeleteAll(String key) {

        adsRepository.deleteAll(key);

    }

    public String adsRename() {
        long startTime = (System.currentTimeMillis() / 1000L) * 1000L;
        long endTime=0;


        String result;
/*
        System.out.println("start_time : " + startTime);
        adsRepository.deleteAll("ADS");
        endTime = (System.currentTimeMillis() / 1000L) * 1000L;
        System.out.println("end_time : " + endTime);
        System.out.println("adsRepository.deleteAll()  Time : " + (endTime-startTime));

*/

        System.out.println("start_time : " + startTime);
        result = adsRepository.rename();
        System.out.println("result : " + result);
        endTime = (System.currentTimeMillis() / 1000L) * 1000L;
        System.out.println("end_time : " + endTime);
        System.out.println("adsRepository.rename()  Time : " + (endTime-startTime));

        return result;
    }

    public void adsSaveFromUrl()   {

        long startTime = (System.currentTimeMillis() / 1000L) * 1000L;
        long endTime=0;
        System.out.println("start_time : " + startTime);

        try {
            JSONArray ads = jsonReadService.readJsonUrl();
        } catch (IOException e) {
            System.out.println("e : " + e);
        }


        JSONArray ads = jsonReadService.readJson();

        Iterator i = ads.iterator();



        int x=0;

        while(i.hasNext()) {
            x+=1;

            JSONObject innerObj = (JSONObject) i.next();
            JSONArray deals = (JSONArray) innerObj.get("deals");

            String keyword = (innerObj.get("keyword") != null ? innerObj.get("keyword").toString() : "" ) ;
            keyword = keyword.trim();

            String valueDeals = deals.toJSONString();

            Ads redisAds  =  new Ads();
            redisAds.setKeyword(keyword);
            redisAds.setDesls(valueDeals);
//            System.out.println("keyword : " + keyword + " / valueDeals : " + valueDeals);

            adsRepository.save(redisAds);

        }

        endTime = (System.currentTimeMillis() / 1000L) * 1000L;
        System.out.println("end_time : " + endTime);

        System.out.println("count : " + x + "/  Time : " + (endTime-startTime));
    }
}
