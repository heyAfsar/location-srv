package com.crashtech.locationsrv.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Result {
    @JsonProperty("sunrise")
    private LocalDateTime sunrise;

    @JsonProperty("sunrise")
    private LocalDateTime sunset;

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }
}
