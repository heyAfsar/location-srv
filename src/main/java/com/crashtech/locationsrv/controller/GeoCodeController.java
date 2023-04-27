package com.crashtech.locationsrv.controller;

import com.crashtech.locationsrv.model.CityResponse;
import com.crashtech.locationsrv.model.TimeLineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class GeoCodeController {

    @GetMapping("/")
    public String hello(){
        return "heyyy";
    }

    @GetMapping(value = "/{city}")
    public TimeLineResponse getCoordinated(@PathVariable("city") String city){
        ResponseEntity<CityResponse> response = new RestTemplate().getForEntity(
                "http://api.positionstack.com/v1/forward?access_key=7a007716d1f042279f90a014398d4bb3&query="+city,
                CityResponse.class);

        String lat = String.valueOf(Arrays.stream(response.getBody().getData()).findFirst().get().getLatitude());
        String lng = String.valueOf(Arrays.stream(response.getBody().getData()).findFirst().get().getLongitude());

        ResponseEntity<TimeLineResponse> timeStampResponse = new RestTemplate().getForEntity(
                "https://api.sunrise-sunset.org/json?lat=23.375701&lng=85.329918&formatted=0",
                TimeLineResponse.class);
        System.out.println(timeStampResponse.getBody());

        return timeStampResponse.getBody();
    }


}
