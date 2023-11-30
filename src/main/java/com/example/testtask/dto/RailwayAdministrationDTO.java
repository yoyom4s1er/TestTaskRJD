package com.example.testtask.dto;

import com.example.testtask.model.RailwayAdministration;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RailwayAdministrationDTO {

    @JsonProperty("код ЖА")
    private int code;
    @JsonProperty("полное наименование ЖА")
    private String fullName;
    @JsonProperty("аббревиатура ЖА")
    private String abbreviation;

    public RailwayAdministration toModel() {
        RailwayAdministration administration = new RailwayAdministration();
        administration.setCode(code);
        administration.setFullName(fullName);
        administration.setAbbreviation(abbreviation);

        return administration;
    }

    public static RailwayAdministrationDTO fromModel(RailwayAdministration administration){
        RailwayAdministrationDTO dto = new RailwayAdministrationDTO();
        dto.setCode(administration.getCode());
        dto.setFullName(administration.getFullName());
        dto.setAbbreviation(administration.getAbbreviation());

        return dto;
    }
}
