package com.pkg.utility;

import com.pkg.config.ApiConfig;
import com.pkg.model.Country;
import com.pkg.model.League;
import com.pkg.model.TeamStanding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.pkg.utility.ApiConstants.*;

@Component
@Slf4j
public class ApiClientWrapper {

    @Autowired
    private RestApiClient restAPIClient;

    @Autowired
    private ApiConfig apiConfig;

    /**
     * Get API Call to Fetch All Countries with ID and Name
     */
    public List<Country> getCountryDetails() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ACTION, apiConfig.getActionCountries());
        return new ArrayList<>(Arrays.asList(restAPIClient.createApiCall(apiConfig.getBaseUrl(), queryParams, HttpMethod.GET, apiConfig.getActionCountries(),
                Country[].class)));
    }

    /**
     * Get API Call to find League Details for a particular country id
     */
    public List<League> getLeagueDetailsFromCountryId(int countryId) throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ACTION, apiConfig.getActionLeague());
        queryParams.put(COUNTRY_ID, String.valueOf(countryId));
        return new ArrayList<>(Arrays.asList(restAPIClient.createApiCall(apiConfig.getBaseUrl(), queryParams, HttpMethod.GET, apiConfig.getActionLeague(),
                League[].class)));
    }

    /**
     * Get API Calls to find League Standing information from a league id
     */
    public List<TeamStanding> getTeamStandingListFromLeagueId(int leagueId) throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ACTION, apiConfig.getTeamStandings());
        queryParams.put(LEAGUE_ID, String.valueOf(leagueId));
        return new ArrayList<>(Arrays.asList(restAPIClient.createApiCall(apiConfig.getBaseUrl(), queryParams, HttpMethod.GET, apiConfig.getTeamStandings(),
                TeamStanding[].class)));
    }
}
