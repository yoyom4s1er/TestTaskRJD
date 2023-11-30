package com.example.testtask.service;

import com.example.testtask.model.RailwayAdministration;

import java.util.List;
import java.util.Optional;

public interface AdministrationService {

    public List<RailwayAdministration> getAll();
    public Optional<RailwayAdministration> getByCode(int code);

    public Optional<RailwayAdministration> getByFullName(String name);

    public boolean addAdministration(RailwayAdministration administration);

    public Optional<RailwayAdministration> getByAbbreviation(String abbreviation);

    public boolean deleteByCode(int code);

    public boolean updateValues(int code, RailwayAdministration administration);
}
