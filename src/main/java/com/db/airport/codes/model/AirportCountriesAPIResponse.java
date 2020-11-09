
package com.db.airport.codes.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "statusCode",
        "countries"
})
public class AirportCountriesAPIResponse {

    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonProperty("countries")
    private List<Country> countries = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("term")
    private String term;
    @JsonProperty("message")
    private String message;

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

    @JsonProperty("countries")
    public List<Country> getCountries() {
        return countries;
    }

    @JsonProperty("countries")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
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
