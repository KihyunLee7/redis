package com.wemakeprice.ad.redis.repository;


import com.wemakeprice.ad.redis.model.Ads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;


@Repository
public class AdsRepositoryImpl implements AdsRepository {


    private static final String KEY = "ADS";
    private static final String KEY1 = "ADS1";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, Ads> hashOperations;





    @Autowired
    public AdsRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {

        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void save(Ads ads) {

//        System.out.println("keyword : " + ads.getKeyword() +  " / keyword.hashCode() : " + ads.getKeyword().hashCode() + "/ ads.deals : " + ads.getDesls());
        hashOperations.put(KEY1 , ads.getKeyword().hashCode(), ads);

    }

    @Override
    public Ads find(String keyword) {

        System.out.println("keyword : " + keyword +  " / keyword.hashCode() : " + keyword.hashCode());


        return hashOperations.get(KEY, keyword.hashCode());

    }

    @Override
    public void delete(String keyword) {
        hashOperations.delete(KEY, keyword.hashCode());
    }


    @Override
    public void deleteAll(String key) {
        if (key.equals(KEY)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public String rename() {

        try {
            if (redisTemplate.keys(KEY1) != null && redisTemplate.keys(KEY1).size() > 0) {
                redisTemplate.delete(KEY);
                //          redisTemplate.rename(KEY1, KEY);
                redisTemplate.renameIfAbsent(KEY1, KEY);
                return "OK";
            } else {
                return "Error";
            }
        } catch (Exception e) {
            return e.toString();
        }
    }
}
