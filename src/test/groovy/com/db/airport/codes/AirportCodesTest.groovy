package com.db.airport.codes

import com.db.airport.codes.model.AirportResponse
import com.db.airport.codes.service.AirportCodesService
import com.db.airport.codes.dao.AirportCodesDAO
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest
@Slf4j
class AirportCodesTest extends Specification {

    @Autowired
    ParameterizedTypeReference<Map<String, Object>> genericTypeRef

    private AirportCodesService airportCodesService = new AirportCodesService()

    private AirportCodesDAO airportCodesDAO

    ResponseEntity<AirportResponse> mockSingleResponse, mockSingleResponse404

    def setup() {
        //log.info("Performing Setup");
        airportCodesDAO = Mock()

        def objectMapper = new ObjectMapper()
        mockSingleResponse = new ResponseEntity<AirportResponse>(objectMapper.readValue(new ClassPathResource("mockSingleResponse.json").getURL(), AirportResponse.class), HttpStatus.OK)
        mockSingleResponse404 = new ResponseEntity<AirportResponse>(objectMapper.readValue(new ClassPathResource("mockSingleResponse_404.json").getURL(), AirportResponse.class), HttpStatus.OK)

        airportCodesService.setAirportCodesDAO(airportCodesDAO)

    }

    def "Positive Single API test"() {
        def result
        given:
        airportCodesDAO.getAirportDetails("LON") >> mockSingleResponse
        when:
        result = airportCodesService.getAirportDetails("LON")
        then:
        result == mockSingleResponse
    }


    def "Negative Single API test"() {
        def result
        given:
        airportCodesDAO.getAirportDetails("LON") >> mockSingleResponse404
        when:
        result = airportCodesService.getAirportDetails("LON")
        then:
        result == mockSingleResponse404
    }

    def "Single API Number of times called"() {
        when:
        airportCodesService.getAirportDetails("LON")
        then:
        1 * airportCodesDAO.getAirportDetails("LON")
    }

}