package com.db.airport.codes.dao;

import com.db.airport.codes.AirportCodesConfig;
import com.db.airport.codes.model.AirportResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static com.db.airport.codes.utils.Constants.TRACE_ID_KEY;

@Component
@Slf4j
public class AirportCodesDAO {

    @Autowired
    private ParameterizedTypeReference<Map<String, Object>> genericTypeRef;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders airportCodesRequestHeaders;

    @Autowired
    private AirportCodesConfig airportCodesConfig;

    /**
     * The actual method making the downstream call
     * @param iata IATA code for airport
     * @return returns the downstream response
     */
    public ResponseEntity<AirportResponse> getAirportDetails(String iata) {

        String singleURL = airportCodesConfig.getServerUrl() + airportCodesConfig.getSingleAPI();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(singleURL).queryParam("iata", iata);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, getRequestWithHeaders(), AirportResponse.class);
    }

    private HttpEntity getRequestWithHeaders() { // NOSONAR
        log.debug("Adding trace-id to out-going requests");
        airportCodesRequestHeaders.add(TRACE_ID_KEY, MDC.get(TRACE_ID_KEY));
        return new HttpEntity(airportCodesRequestHeaders); // NOSONAR
    }
}
