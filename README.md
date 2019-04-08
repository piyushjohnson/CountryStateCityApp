# CountryStateCityApp
A simple app demostrates to load country/state/city names

# Setup

Add jitpack to project level build.gradle

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

Add CCSApi dependency to app module build.gradle

```
dependencies {
	        implementation 'com.github.piyushjohnson:CCS:564b00619a'
}
```

# Usage

## Creating instance of CCSApi

```
CCSApi api = new CCSApi(this);
```

## Getting country names

```
 ccsApi.getCountries().getResult(new CCSApi.Callback() {
            @Override
            public void onResult(List<String> values) {
                
            }
});
```

## Getting state names

```
String selectedCountry = "India";
 ccsApi.getStates(selectedCountry).getResult(new CCSApi.Callback() {
            @Override
            public void onResult(List<String> values) {
                
            }
});
```

## Getting city names

```
String selectedState = "Rajasthan";
 ccsApi.getCities(selectedState).getResult(new CCSApi.Callback() {
            @Override
            public void onResult(List<String> values) {
                
            }
});
```
