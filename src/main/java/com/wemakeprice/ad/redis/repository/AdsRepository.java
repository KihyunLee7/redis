package com.wemakeprice.ad.redis.repository;


import com.wemakeprice.ad.redis.model.Ads;



public interface AdsRepository {
    void save(Ads ads);
    Ads find(String keyword);
    void delete(String keyword);
    void deleteAll(String key);
    void rename();

}
