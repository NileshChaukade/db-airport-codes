package com.db.airport.codes.controller;

import com.db.airport.codes.model.AirportCountriesAPIResponse;
import com.db.airport.codes.model.AirportResponse;
import com.db.airport.codes.model.AirportStatesAPIResponse;
import com.db.airport.codes.service.AirportCodesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class AirportCodesController {

    @Autowired
    private AirportCodesService airportCodesService;

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/single?iata=LON
     *
     * @param iata IATA airport code
     * @return Detailed JSON about the input Airport, if found
     */
    @GetMapping(value = "single")
    public ResponseEntity<AirportResponse> getAirportDetails(@RequestParam(value = "iata") String iata) {

        return airportCodesService.getAirportDetails(iata);
    }

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/single/LON
     * It has been implemented to be forward compatible if www.air-port-codes.com implements this ReST call in future
     *
     * @param iata IATA airport code
     * @return Detailed JSON about the input Airport, if found
     */
    @GetMapping(value = "single/*")
    public ResponseEntity<AirportResponse> getAirportDetailsOthers(@RequestParam(value = "iata", required = false) String iata) {

        return airportCodesService.getAirportDetails(iata);
    }

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/states?country=IN
     *
     * @param country country code
     * @return JSON about airports in the given country, if found
     */
    @GetMapping(value = "states")
    public ResponseEntity<AirportStatesAPIResponse> getStates(@RequestParam(value = "country", required = false) String country) {

        return airportCodesService.getStates(country);
    }

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/states/IN
     * It has been implemented to be forward compatible if www.air-port-codes.com implements this ReST call in future
     *
     * @param country country code
     * @return JSON about state airports in the given country, if found
     */
    @GetMapping(value = "states/*")
    public ResponseEntity<AirportStatesAPIResponse> getStatesOthers(@RequestParam(value = "country", required = false) String country) {

        return airportCodesService.getStates(country);
    }

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/countries?field_name=name
     * <p>
     * The downstream API does seem to be honoring the passed field_name, Any or no value passed still generates the same output response
     *
     * @param fieldName field name passed
     * @return Detailed JSON about the input Airport, if found
     */
    @GetMapping(value = "countries")
    public ResponseEntity<AirportCountriesAPIResponse> getCountries(@RequestParam(value = "field_name", required = false) String fieldName) {

        return airportCodesService.getCountries(fieldName);
    }

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/countries?field_name=name
     * It has been implemented to be forward compatible if www.air-port-codes.com implements this ReST call in future
     * <p>
     * The downstream API does seem to be honoring the passed field_name, Any or no value passed still generates the same output response
     *
     * @param fieldName name of the field to be returned
     * @return Detailed JSON about the input Airport, if found
     */
    @GetMapping(value = "countries/*")
    public ResponseEntity<AirportCountriesAPIResponse> getCountriesOthers(@RequestParam(value = "field_name", required = false) String fieldName) {

        return airportCodesService.getCountries(fieldName);
    }
}
