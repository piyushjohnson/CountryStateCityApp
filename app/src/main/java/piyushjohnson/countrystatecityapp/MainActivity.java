package piyushjohnson.countrystatecityapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    Spinner country,state,city;
    ArrayAdapter<String> countryAdapter,stateAdapter,cityAdapter;
    List<String> countryList,stateList,cityList;
    String selectedCountry,selectedState,selectedCity;

    CCSApi ccsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccsApi = new CCSApi(this);

        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);

        country.setOnItemSelectedListener(this);
        state.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);

        countryList = new ArrayList<>();
        stateList = new ArrayList<>();
        cityList = new ArrayList<>();

        countryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,countryList);
        stateAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,stateList);
        cityAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,cityList);

        country.setAdapter(countryAdapter);
        state.setAdapter(stateAdapter);
        city.setAdapter(cityAdapter);

        getCountries();
    }

     @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = ((TextView) view);
        switch (parent.getId()) {
            case R.id.country:
                selectedCountry = textView.getText().toString();
                getStatesFromSelectedCountry();
                break;
            case R.id.state:
                selectedState = textView.getText().toString();
                getCitiesFromSelectedState();
                break;
            case R.id.city:
                selectedCity = textView.getText().toString();
                break;
        }
    }

    private void getCitiesFromSelectedState() {
        ccsApi.getCities(selectedState).getResult(new CCSApi.Callback() {
            @Override
            public void onResult(List<String> values) {
                Log.i(TAG, "onResult: " + values.toString());
                cityAdapter.clear();
                cityAdapter.addAll(values);
                cityAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getStatesFromSelectedCountry() {
        ccsApi.getStates(selectedCountry).getResult(new CCSApi.Callback() {
            @Override
            public void onResult(List<String> values) {
                stateAdapter.clear();
                stateAdapter.addAll(values);
                stateAdapter.notifyDataSetChanged();
            }
        });
    }

    public void getCountries() {
        ccsApi.getCountries().getResult(new CCSApi.Callback() {
            @Override
            public void onResult(List<String> values) {
                countryAdapter.clear();
                countryAdapter.addAll(values);
                countryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
