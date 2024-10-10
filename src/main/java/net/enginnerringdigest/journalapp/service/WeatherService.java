package net.enginnerringdigest.journalapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.enginnerringdigest.journalapp.api.response.WeatherResponse;
import net.enginnerringdigest.journalapp.cache.AppCache;
import net.enginnerringdigest.journalapp.constraints.Placeholders;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;
    
    
    public WeatherResponse getWeather(String city) {
        String weatherAppUrl = appCache.appCache.get(AppCache.keys.WEATHER_APP.toString());
    
        if (weatherAppUrl == null) {
            throw new RuntimeException("Weather app URL not found in the cache.");
        }
    
        String finalAPI = weatherAppUrl.replace(Placeholders.API_KEY, apiKey).replace(Placeholders.CITY, city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
        return response.getBody();
    }
    

    // public WeatherResponse PostWeather(String city){
    //     String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

    //     HttpHeaders httpsHeaders = new HttpHeaders();
    //     httpsHeaders.set("key", "value");
    //     ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
    //     WeatherResponse body = response.getBody();
    //     return body;
    // }


}
