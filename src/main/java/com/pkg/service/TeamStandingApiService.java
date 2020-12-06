package com.pkg.service;

import com.pkg.dto.TeamStandingRequestDto;
import com.pkg.dto.TeamStandingResponseDto;
import com.pkg.model.Country;
import com.pkg.model.League;
import com.pkg.model.TeamStanding;
import com.pkg.utility.ApiClientWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamStandingApiService {

    @Autowired
    private ApiClientWrapper apiClientWrapper;

    public TeamStandingResponseDto getTeamStandingInfo(TeamStandingRequestDto teamStandingRequestDto) throws Exception {
        TeamStandingResponseDto teamStandingResponseDto = prepareResponseFromRequest(teamStandingRequestDto);

        // Find All Country Details
        List<Country> countryList = apiClientWrapper.getCountryDetails();

        // Find CountryID from Name
        Country countryInfo = getCountryIdFromName(teamStandingRequestDto, countryList);

        // If CountryInfo not Present, Show only Request Data otherwise add countryId
        if (countryInfo == null) return teamStandingResponseDto;
        else teamStandingResponseDto.setCountryId(countryInfo.getCountryId());

        // Find LeagueDetails From CountryId
        List<League> leaguesList = apiClientWrapper.getLeagueDetailsFromCountryId(countryInfo.getCountryId());

        // Find League Info From from League Name
        League leagueInfo = getLeaguesByName(teamStandingRequestDto, leaguesList);

        // If LeagueInfo not present, show only request Data otherwise add League Id
        if (leagueInfo == null) return teamStandingResponseDto;
        else teamStandingResponseDto.setLeagueId(leagueInfo.getLeagueId());

        // Find Team Standing List From League Id
        List<TeamStanding> teamStandings = apiClientWrapper.getTeamStandingListFromLeagueId(leagueInfo.getLeagueId());

        // Get Team Position Data From Team Name
        TeamStanding actualTeamStanding = getTeamStandingFromTeamName(teamStandingRequestDto,
                teamStandings);

        // If Team Position Data Not Found Show Request, otherwise set final response
        if (actualTeamStanding.getTeamId() == 0) return teamStandingResponseDto;
        else prepareFinalResponse(actualTeamStanding, teamStandingResponseDto);

        log.info("Team Overall standing is  {}", actualTeamStanding.getOverallPosition());
        return teamStandingResponseDto;
    }

    private void prepareFinalResponse(TeamStanding actualTeamStanding, TeamStandingResponseDto teamStandingResponseDto) {
        teamStandingResponseDto.setTeamId(actualTeamStanding.getTeamId());
        teamStandingResponseDto.setOverallLeaguePosition(actualTeamStanding.getOverallPosition());
    }

    private TeamStandingResponseDto prepareResponseFromRequest(TeamStandingRequestDto teamStandingRequestDto) {
        TeamStandingResponseDto teamStandingResponseDto = new TeamStandingResponseDto();
        teamStandingResponseDto.setTeamName(teamStandingRequestDto.getTeamName());
        teamStandingResponseDto.setCountryName(teamStandingRequestDto.getCountryName());
        teamStandingResponseDto.setLeagueName(teamStandingRequestDto.getLeagueName());
        return teamStandingResponseDto;
    }

    private Country getCountryIdFromName(TeamStandingRequestDto teamStandingRequestDto,
                                         List<Country> countryList) {
        return countryList.stream()
                .filter(country -> teamStandingRequestDto.getCountryName()
                        .equalsIgnoreCase(country.getCountryName()))
                .findFirst().orElse(null);
    }

    private League getLeaguesByName(TeamStandingRequestDto teamStandingRequestDto,
                                    List<League> leaguesList) {
        return leaguesList.stream()
                .filter(league -> teamStandingRequestDto.getLeagueName()
                        .equalsIgnoreCase(league.getLeagueName()))
                .findFirst().orElse(null);
    }

    private TeamStanding getTeamStandingFromTeamName(TeamStandingRequestDto teamStandingRequestDto,
                                                     List<TeamStanding> teamStanding) {
        return teamStanding.stream()
                .filter(teamStandingInfo -> teamStandingRequestDto.getTeamName()
                        .equalsIgnoreCase(teamStandingInfo.getTeamName()))
                .findFirst().orElse(null);
    }
}
