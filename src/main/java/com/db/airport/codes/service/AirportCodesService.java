package com.db.airport.codes.service;

import com.db.airport.codes.dao.AirportCodesDAO;
import com.db.airport.codes.model.AirportCountriesAPIResponse;
import com.db.airport.codes.model.AirportResponse;
import com.db.airport.codes.model.AirportStatesAPIResponse;
import com.db.airport.codes.utils.Constants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class AirportCodesService {

    @Autowired
    private AirportCodesDAO airportCodesDAO;

    @HystrixCommand(fallbackMethod = "defaultAirportDetailsResponse")
    public ResponseEntity<AirportResponse> getAirportDetails(String iata) {

        return airportCodesDAO.getAirportDetails(iata);
    }

    @HystrixCommand(fallbackMethod = "defaultStatesResponse")
    public ResponseEntity<AirportStatesAPIResponse> getStates(String country) {

        return airportCodesDAO.getStates(country);
    }

    @HystrixCommand(fallbackMethod = "defaultCountriesResponse")
    public ResponseEntity<AirportCountriesAPIResponse> getCountries(String fieldName) {

        return airportCodesDAO.getCountries(fieldName);
    }

    private ResponseEntity<AirportResponse> defaultAirportDetailsResponse(String param) {
        AirportResponse airportResponse = new AirportResponse();
        airportResponse.setStatus(Boolean.FALSE);
        airportResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        airportResponse.setMessage(Constants.MSG_503);
        airportResponse.setTerm(param);
        return new ResponseEntity<>(airportResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<AirportStatesAPIResponse> defaultStatesResponse(String param) {
        AirportStatesAPIResponse airportStatesAPIResponse = new AirportStatesAPIResponse();
        airportStatesAPIResponse.setStatus(Boolean.FALSE);
        airportStatesAPIResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        airportStatesAPIResponse.setMessage(Constants.MSG_503);
        airportStatesAPIResponse.setTerm(param);
        return new ResponseEntity<>(airportStatesAPIResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<AirportCountriesAPIResponse> defaultCountriesResponse(String param) {
        AirportCountriesAPIResponse airportCountriesAPIResponse = new AirportCountriesAPIResponse();
        airportCountriesAPIResponse.setStatus(Boolean.FALSE);
        airportCountriesAPIResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        airportCountriesAPIResponse.setMessage(Constants.MSG_503);
        airportCountriesAPIResponse.setTerm(param);
        return new ResponseEntity<>(airportCountriesAPIResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
