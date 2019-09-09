package com.ks.supersync.service.unasapiservice;

public enum UnasMServiceEndpoints {

    GET_PRODUCTS("ks-supersync-unasapi/getproducts_fromunas"),
    GET_CUSTOMERS("ks-supersync-unasapi/getcustomers_fromunas"),
    GET_ORDERS("ks-supersync-unasapi/getorders_fromunas"),
    SET_PRODUCTS("ks-supersync-unasapi/setproducts_tounas"),
    SET_CUSTOMERS("ks-supersync-unasapi/setcustomers_tounas"),
    SET_ORDERS("ks-supersync-unasapi/setorders_tounas");

    public final String url;

    UnasMServiceEndpoints(String url) {
        this.url = url;
    }

    public String toString(){
        return url;
    }

}
