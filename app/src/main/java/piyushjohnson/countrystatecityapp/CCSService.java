package piyushjohnson.countrystatecityapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CCSService {
    @GET("apiv1.php?type=getCountries")
    Call<CCSResult> getCountries();

    @GET("apiv1.php?type=getStates")
    Call<CCSResult> getStates(@Query("countryId") int countryId);

    @GET("apiv1.php?type=getCities")
    Call<CCSResult> getCities(@Query("stateId") int stateId);
}
