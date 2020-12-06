package com.pkg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {
    @JsonProperty("country_id")
    private int countryId;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("league_id")
    private int leagueId;

    @JsonProperty("league_name")
    private String leagueName;
}
