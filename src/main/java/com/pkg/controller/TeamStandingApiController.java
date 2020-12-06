package com.pkg.controller;

import com.pkg.dto.TeamStandingRequestDto;
import com.pkg.service.TeamStandingApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/v1/team_standing")
public class TeamStandingApiController {

    @Autowired
    private TeamStandingApiService teamStandingApiService;

    /**
     * URL To hit From Local:
     * http://localhost:8080/api/v1/team_standing?countryName=England&leagueName=Championship1&teamName=Watford
     *
     * @param teamStandingRequestDto
     */
    @GetMapping
    public ResponseEntity getTeamStandings(@Valid TeamStandingRequestDto teamStandingRequestDto) {
        log.info("Request received for CountryName: {} LeagueName: {} TemName: {}",
                teamStandingRequestDto.getCountryName(), teamStandingRequestDto.getLeagueName(),
                teamStandingRequestDto.getTeamName());
        try {
            return ResponseEntity.ok(teamStandingApiService.getTeamStandingInfo(teamStandingRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
