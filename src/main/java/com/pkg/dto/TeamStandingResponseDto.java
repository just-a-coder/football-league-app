package com.pkg.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeamStandingResponseDto {
    private int countryId;
    private String countryName;
    private int leagueId;
    private String leagueName;
    private int teamId;
    private String teamName;
    private int overallLeaguePosition;
}