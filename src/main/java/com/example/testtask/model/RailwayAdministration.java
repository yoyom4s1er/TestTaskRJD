package com.example.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RailwayAdministration {

    private int code;
    private String fullName;
    private String abbreviation;

    @Override
    public String toString() {
        return "RailwayAdministration{" +
                "code=" + code +
                ", fullName='" + fullName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
