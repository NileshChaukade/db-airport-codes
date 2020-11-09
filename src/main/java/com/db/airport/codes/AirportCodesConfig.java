package com.db.airport.codes;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@Configuration
@Slf4j
@Data
public class AirportCodesConfig {

    @Value("${application.airport-codes.auth-key}")
    private String authKey;

    @Value("${application.airport-codes.auth-secret}")
    private String authSecret;

    @Value("${application.airport-codes.server-url}")
    private String serverUrl;

    @Value("${application.airport-codes.single}")
    private String singleAPI;

    @Value("${application.airport-codes.countries}")
    private String countriesAPI;

    @Value("${application.airport-codes.states}")
    private String statesAPI;

    @Bean
    public ParameterizedTypeReference<Map<String, Object>> getGenericTypeRef(){
        return new ParameterizedTypeReference<Map<String, Object>>(){};
    }

    /**
     * This bean is adding the required headers, along with other, for Authentication with downstream APIs from www.air-port-codes.com
     * @return HTTPHeaders with additional headers
     */
    @Bean
    public HttpHeaders airportCodesRequestHeaders(){
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            requestHeaders.set("APC-Auth", authKey); //0c1d337658
            requestHeaders.set("APC-Auth-Secret", authSecret); //919f452c8801781

            return requestHeaders;
    }

    /**
     * RestTemplate to be used for calling downstream APIs
     * @param builder RestTemplateBuilder
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }


}
