package com.crashtech.locationsrv.controller;

import com.crashtech.locationsrv.model.CityResponse;
import com.crashtech.locationsrv.model.TimeLineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Date;

@RestController
public class GeoCodeController {

    @GetMapping("/")
    public String hello(){
        return "heyyy";
    }

    @GetMapping(value = "/{city}")
    public TimeLineResponse getCoordinated(@PathVariable("city") String city) {
        ResponseEntity<CityResponse> response = new RestTemplate().getForEntity(
                "http://api.positionstack.com/v1/forward?access_key=7a007716d1f042279f90a014398d4bb3&query=" + city,
                CityResponse.class);

        double lat = Arrays.stream(response.getBody().getData()).findFirst().get().getLatitude();
        double lng = Arrays.stream(response.getBody().getData()).findFirst().get().getLongitude();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.sunrise-sunset.org")
                .path("/json")
                .queryParam("lat", lat)
                .queryParam("lng", lng)
                .queryParam("formatted", 0)
                .build();

        System.out.println(uri.toUriString());
        System.out.println("https://api.sunrise-sunset.org/json?lat=23.375701&lng=85.329918&formatted=0");

        ResponseEntity<TimeLineResponse> timeStampResponse = new RestTemplate().getForEntity(
                uri.toUriString(),
                TimeLineResponse.class);
        System.out.println(timeStampResponse.getBody());

        String sunrise = timeStampResponse.getBody().getResults().getSunrise();
        String sunset = timeStampResponse.getBody().getResults().getSunset();

//        try {
//
//            System.out.println(LocalDateTime.parse(sunrise));
//            System.out.println(LocalDateTime.parse(sunset));
//            System.out.println("sunrise: " + sunrise + ",\nsunset: " + sunset);
//        } catch (NumberFormatException ex) {
//            return "nfe exception";
//        }
//        OffsetDateTime dateTimeSunrise = OffsetDateTime.parse(sunrise);
//        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
//                .withZone(dateTimeSunrise.getOffset());
//
//        OffsetDateTime dateTimeSunset = OffsetDateTime.parse(sunset);
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
//                .withZone(dateTimeSunset.getOffset());

//
//        return "sunrise: " + formatter.format(dateTimeSunrise)+ ",\nsunset: " + formatter1.format(dateTimeSunset);

        return timeStampResponse.getBody();
    }


}
