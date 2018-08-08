package com.wemakeprice.ad.redis.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class Ads implements Serializable {

    private String keyword;
    private String desls;



    @Override
    public String toString() {
        return String.format("{ \"keyword\" : \"%s\", \"deals\" : %s}", keyword, desls);
    }

}
