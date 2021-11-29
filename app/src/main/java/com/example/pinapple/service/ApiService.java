package com.example.pinapple.service;

public class ApiService {

    private static String base_url = "http://10.0.2.2:8000/";
    public static Service getService (){
        return ApiClient.getRetrofit(base_url).create(Service.class);
    }


}
