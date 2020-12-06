package com.pkg.service

import com.pkg.dto.TeamStandingRequestDto
import com.pkg.dto.TeamStandingResponseDto
import com.pkg.model.Country
import com.pkg.model.League
import com.pkg.model.TeamStanding
import com.pkg.utility.ApiClientWrapper
import spock.lang.Specification
import spock.lang.Subject

class TeamStandingApiServiceTest extends Specification {

    private ApiClientWrapper mockApiClientWrapper = Mock()

    @Subject
    private TeamStandingApiService teamStandingApiService

    TeamStandingRequestDto teamStandingRequestDto

    List<Country> countryList = new ArrayList<>()
    List<League> leagueList = new ArrayList<>()
    List<TeamStanding> teamStandings = new ArrayList<>()

    void setup() {
        teamStandingApiService = new TeamStandingApiService()
        teamStandingApiService.apiClientWrapper = mockApiClientWrapper
        teamStandingRequestDto = new TeamStandingRequestDto()
        teamStandingRequestDto.setCountryName("England")
        teamStandingRequestDto.setLeagueName("Championship")
        teamStandingRequestDto.setTeamName("Watford")

        Country country1 = new Country(41, "England");
        Country country2 = new Country(46, "France");
        countryList.add(country1)
        countryList.add(country2)

        League league = new League(41, "England", 149, "Championship")
        leagueList.add(league)

        TeamStanding teamStanding = new TeamStanding("England", 149, "Championship",
                2623, "Watford", 7)
        teamStandings.add(teamStanding)
    }

    def "check proper GetTeamStandingInfo from Request"() {
        given: "Set Pre conditions for Calling Method"
        mockApiClientWrapper.getCountryDetails() >> { countryList }
        mockApiClientWrapper.getLeagueDetailsFromCountryId(41) >> { leagueList }
        mockApiClientWrapper.getTeamStandingListFromLeagueId(149) >> { teamStandings }


        when: "Calling Method with Proper Request"
        TeamStandingResponseDto teamStandingResponseDto = teamStandingApiService
                .getTeamStandingInfo(teamStandingRequestDto)

        then: "Expecting Valid Response back from Method"

        teamStandingResponseDto.getCountryId() == 41
        teamStandingResponseDto.getCountryName() == "England"
        teamStandingResponseDto.getTeamId() == 2623
        teamStandingResponseDto.getTeamName() == "Watford"
        teamStandingResponseDto.getLeagueId() == 149
        teamStandingResponseDto.getLeagueName() == "Championship"
        teamStandingResponseDto.getOverallLeaguePosition() == 7
    }
}
