package com.crashtech.locationsrv.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeLineResponse {

    @JsonProperty("results")
    private Result results;

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }
}
