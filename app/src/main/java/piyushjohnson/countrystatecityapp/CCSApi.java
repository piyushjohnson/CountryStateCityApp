package piyushjohnson.countrystatecityapp;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;

public class CCSApi {
    private static final String TAG = "CCSApi";
    private Context mContext;
    private CCSService ccsService;
    private Callback mCallback;
    private Handler mHandler;
    private ExecutorService executorService;
    private Retrofit retrofit;
    private static CCSResult countries,states,cities;

    public interface Callback {
        public void onResult(List<String> values);
    }

    public CCSApi(Context context) {
        mHandler = new Handler(context.getMainLooper());
        executorService = Executors.newFixedThreadPool(2);
        ccsService = CCSBuilder.getInstance().create(CCSService.class);
    }

    public CCSApi getCountries() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    countries = ccsService.getCountries().execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                publishResult(Arrays.asList(countries.getValues().values().toArray(new String[0])));
            }
        });
        return this;
    }

    public CCSApi getStates(final String country) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if(countries != null) {
                    try {
                        states = ccsService.getStates(Integer.parseInt(countries.getKey(country))).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    publishResult(Arrays.asList(states.getValues().values().toArray(new String[0])));
                }
            }
        });
        return this;
    }

    public CCSApi getCities(final String state) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if(states != null) {
                    try {
                        cities = ccsService.getCities(Integer.parseInt(states.getKey(state))).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    publishResult(Arrays.asList(cities.getValues().values().toArray(new String[0])));
                }
            }
        });
        return this;
    }

    private synchronized void publishResult(final List<String> result) {
        Log.i(TAG, "publishResult: " + result.size());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onResult(result);
            }
        });
    }

    public void getResult(Callback callback) {
        this.mCallback = callback;
    }
}
