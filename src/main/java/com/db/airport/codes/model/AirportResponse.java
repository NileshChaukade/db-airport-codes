
package com.db.airport.codes.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "statusCode",
        "district_type",
        "states"
})
public class AirportResponse {

    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonProperty("district_type")
    private String districtType;
    @JsonProperty("states")
    private List<State> states = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    @JsonProperty("message")
    private String message;
    @JsonProperty("term")
    private String term;

    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty("statusCode")
    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("district_type")
    public String getDistrictType() {
        return districtType;
    }

    @JsonProperty("district_type")
    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    @JsonProperty("states")
    public List<State> getStates() {
        return states;
    }

    @JsonProperty("states")
    public void setStates(List<State> states) {
        this.states = states;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("term")
    public String getTerm() {
        return term;
    }

    @JsonProperty("term")
    public void setTerm(String term) {
        this.term = term;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
