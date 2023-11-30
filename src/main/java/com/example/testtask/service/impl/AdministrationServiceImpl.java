package com.example.testtask.service.impl;

import com.example.testtask.model.RailwayAdministration;
import com.example.testtask.repository.AdministrationRepository;
import com.example.testtask.service.AdministrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministrationServiceImpl implements AdministrationService {

    private AdministrationRepository administrationRepository;
    @Override
    public List<RailwayAdministration> getAll() {
        return administrationRepository.getAll();
    }

    @Override
    public Optional<RailwayAdministration> getByCode(int code) {
        RailwayAdministration administration = administrationRepository.getByCode(code);
        if (administration != null) {
            return Optional.of(administration);
        }

        return Optional.empty();
    }

    @Override
    public Optional<RailwayAdministration> getByFullName(String name) {
        RailwayAdministration administration = administrationRepository.getByFullName(name);
        if (administration != null) {
            return Optional.of(administration);
        }

        return Optional.empty();
    }

    @Override
    public boolean addAdministration(RailwayAdministration administration) {
        return administrationRepository.save(administration);
    }

    @Override
    public Optional<RailwayAdministration> getByAbbreviation(String abbreviation) {
        RailwayAdministration administration = administrationRepository.getByAbbreviation(abbreviation);
        if (administration != null) {
            return Optional.of(administration);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteByCode(int code) {
        return administrationRepository.deleteByCode(code);
    }

    @Override
    public boolean updateValues(int code, RailwayAdministration administration) {

        return administrationRepository.updateValues(code, administration);
    }
}
