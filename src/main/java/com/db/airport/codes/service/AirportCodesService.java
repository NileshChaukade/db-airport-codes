package com.db.airport.codes.service;

import com.db.airport.codes.dao.AirportCodesDAO;
import com.db.airport.codes.model.AirportResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class AirportCodesService {

    @Autowired
    private AirportCodesDAO airportCodesDAO;

    public ResponseEntity<AirportResponse> getAirportDetails(String iata) {

        return airportCodesDAO.getAirportDetails(iata);
    }

}
