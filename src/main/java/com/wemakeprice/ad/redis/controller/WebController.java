package com.wemakeprice.ad.redis.controller;


import com.wemakeprice.ad.redis.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WebController {


    @Autowired
    private AdsService adsService;



    @RequestMapping("/save")
    public String adssave() {
        adsService.adsSave();
        return "Done";
    }

    @RequestMapping("/saveFormUrl")
    public String adssavefromurl() {
        adsService.adsSaveFromUrl();
        return "Done";
    }


    @RequestMapping("/find")
    public String adsfind(@RequestParam("keyword") String keyword) {
        String result = "";
        result = adsService.adsFind(keyword);
        return result;
    }

    @RequestMapping("/delete")
    public String adsdelete(@RequestParam("keyword") String keyword) {
        adsService.adsDelete(keyword);
        return "Done";
    }

    @RequestMapping("/deleteAll")
    public String adsdeleteall(@RequestParam("key") String key) {
        adsService.adsDeleteAll(key);
        return "Done";
    }


    @RequestMapping("/rename")
    public String rename() {
        String result = adsService.adsRename();
        return "Done : " + result;
    }

}