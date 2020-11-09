package com.db.airport.codes.controller;

import com.db.airport.codes.model.AirportResponse;
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

    @GetMapping(value = "single")
    public ResponseEntity<AirportResponse> getAirportDetails(@RequestParam(value = "iata") String iata) {

        return airportCodesService.getAirportDetails(iata);
    }

    /**
     * This controller supports the downstream calls like https://www.air-port-codes.com/api/v1/single/LON
     * It has been implemented to be forward compatible if www.air-port-codes.com implements this ReST call in future
     * @param iata IATA airport code
     * @return Detailed JSON about the input Airport, if found
     */
    @GetMapping(value = "single/*")
    public ResponseEntity<AirportResponse> getAirportDetailsOthers(@RequestParam(value = "iata", required = false) String iata) {

        return airportCodesService.getAirportDetails(iata);
    }

}
