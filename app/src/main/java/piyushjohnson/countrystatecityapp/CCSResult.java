package piyushjohnson.countrystatecityapp;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CCSResult {
    @SerializedName("result")
    private Map<String, String> result;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getValues() {
        return result;
    }

    public void setValues(Map<String, String> result) {
        this.result = result;
    }

    public String getKey(String value) {
        for (Map.Entry<String, String> entry : result.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return "";
    }
}
