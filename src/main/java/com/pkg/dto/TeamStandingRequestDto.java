package com.pkg.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TeamStandingRequestDto {
    @NotBlank
    private String countryName;

    @NotBlank
    private String leagueName;

    @NotBlank
    private String teamName;
}
