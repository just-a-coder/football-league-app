package com.pkg.utility;


import com.pkg.config.ApiConfig;
import com.pkg.exception.SpringRetryingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.rmi.UnexpectedException;
import java.util.Map;

import static com.pkg.utility.ApiConstants.APIKEY;

@Slf4j
@Component
public class RestApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;

    /**
     * Common Method.
     * Main API Call Happening Here
     */
    @Retryable(maxAttempts = 3, value = SpringRetryingException.class, backoff = @Backoff(delay = 5000, multiplier =
            3))
    public <T> T createApiCall(String baseUrl, Map<String, String> queryParams,
                               HttpMethod httpMethod, String action, Class<T> responseType)
            throws Exception {

        T apiResponse = null;
        try {
            UriComponentsBuilder urlBuilder = getUriComponentsBuilder(baseUrl, queryParams);
            HttpHeaders httpHeaders = prepareHeaders();
            HttpEntity<T> httpEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<T> responseEntity = restTemplate.exchange(urlBuilder.toUriString(), httpMethod,
                    httpEntity, responseType);
            log.info("makeApiCall HTTP Status : {}", responseEntity.getStatusCode());

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                apiResponse = responseEntity.getBody();
            } else {
                throw new UnexpectedException("Non 200 response from Rest API for Action: " + action);
            }
        } catch (HttpServerErrorException exception) {
            log.error(" HttpServerException. Details: {}", exception.getMessage());
            if (exception.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE
                    || exception.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT
                    || exception.getStatusCode() == HttpStatus.BAD_GATEWAY)
                throw new SpringRetryingException();
            throw exception;
        } catch (RestClientException ce) {
            log.error("RestClientException : Connection timeout exception: {}", ce.getMessage());
            throw new SpringRetryingException(ce);
        } catch (Exception e) {
            log.error("Exception Occurred in Rest API call : {}", e.getMessage());
            throw e;
        }
        return apiResponse;
    }


    private UriComponentsBuilder getUriComponentsBuilder(String url,
                                                         Map<String, String> queryParams) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(APIKEY, apiConfig.getKey());
        queryParams.forEach(componentsBuilder::queryParam);
        return componentsBuilder;
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

}
