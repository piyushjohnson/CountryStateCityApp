package piyushjohnson.countrystatecityapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CCSBuilder {
    private static Retrofit instance;

    public static Retrofit getInstance() {
        return  new Retrofit.Builder()
                .baseUrl("http://lab.iamrohit.in/php_ajax_country_state_city_dropdown/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
