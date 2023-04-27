package com.crashtech.locationsrv.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityResponse {

    @JsonProperty("data")
    private Data[] data;

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }
}
