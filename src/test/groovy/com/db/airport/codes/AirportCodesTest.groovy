package com.db.airport.codes

import com.db.airport.codes.dao.AirportCodesDAO
import com.db.airport.codes.model.AirportCountriesAPIResponse
import com.db.airport.codes.model.AirportResponse
import com.db.airport.codes.model.AirportStatesAPIResponse
import com.db.airport.codes.service.AirportCodesService
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest
@Slf4j
class AirportCodesTest extends Specification {

    private AirportCodesService airportCodesService = new AirportCodesService()

    private AirportCodesDAO airportCodesDAO

    ResponseEntity<AirportResponse> mockSingleResponse, mockSingleResponse404
    ResponseEntity<AirportStatesAPIResponse> mockStatesResponse, mockFakeCountryStatesResponse, mockStatesResponse404
    ResponseEntity<AirportCountriesAPIResponse> mockCountriesResponse

    def setup() {
        //log.info("Performing Setup");
        airportCodesDAO = Mock()

        def objectMapper = new ObjectMapper()
        mockSingleResponse = new ResponseEntity<AirportResponse>(objectMapper.readValue(new ClassPathResource("mockSingleResponse.json").getURL(), AirportResponse.class), HttpStatus.OK)
        mockSingleResponse404 = new ResponseEntity<AirportResponse>(objectMapper.readValue(new ClassPathResource("mockSingleResponse_404.json").getURL(), AirportResponse.class), HttpStatus.OK)

        mockStatesResponse = new ResponseEntity<AirportStatesAPIResponse>(objectMapper.readValue(new ClassPathResource("mockStatesResponse.json").getURL(), AirportStatesAPIResponse.class), HttpStatus.OK)
        mockFakeCountryStatesResponse = new ResponseEntity<AirportStatesAPIResponse>(objectMapper.readValue(new ClassPathResource("mockStatesFakeCountryResponse.json").getURL(), AirportStatesAPIResponse.class), HttpStatus.OK)
        mockStatesResponse404 = new ResponseEntity<AirportStatesAPIResponse>(objectMapper.readValue(new ClassPathResource("mockStatesResponse_404.json").getURL(), AirportStatesAPIResponse.class), HttpStatus.OK)

        mockCountriesResponse = new ResponseEntity<AirportCountriesAPIResponse>(objectMapper.readValue(new ClassPathResource("mockCountriesResponse.json").getURL(), AirportCountriesAPIResponse.class), HttpStatus.OK)

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


    def "Positive States API test"() {
        def result
        given:
        airportCodesDAO.getStates("IN") >> mockStatesResponse
        when:
        result = airportCodesService.getStates("IN")
        then:
        result == mockStatesResponse
    }

    def "Fake Country States API test"() {
        def result
        given:
        airportCodesDAO.getStates("xx") >> mockFakeCountryStatesResponse
        when:
        result = airportCodesService.getStates("xx")
        then:
        result == mockFakeCountryStatesResponse
    }

    def "Negative States API test"() {
        def result
        given:
        airportCodesDAO.getStates("IN") >> mockStatesResponse404
        when:
        result = airportCodesService.getStates("IN")
        then:
        result == mockStatesResponse404
    }

    def "States API Number of times called"() {
        when:
        airportCodesService.getStates("IN")
        then:
        1 * airportCodesDAO.getStates("IN")
    }


    def "Positive Countries API test"() {
        def result
        given:
        airportCodesDAO.getCountries("name") >> mockCountriesResponse
        when:
        result = airportCodesService.getCountries("name")
        then:
        result == mockCountriesResponse
    }

    def "Countries API Number of times called"() {
        when:
        airportCodesService.getCountries("name")
        then:
        1 * airportCodesDAO.getCountries("name")
    }

}