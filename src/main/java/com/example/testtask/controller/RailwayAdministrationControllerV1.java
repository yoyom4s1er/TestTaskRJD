package com.example.testtask.controller;

import com.example.testtask.dto.RailwayAdministrationDTO;
import com.example.testtask.model.RailwayAdministration;
import com.example.testtask.service.AdministrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/administrations")
@AllArgsConstructor
public class RailwayAdministrationControllerV1 {

    private AdministrationService administrationService;

    @GetMapping("")
    public ResponseEntity<List<RailwayAdministrationDTO>> getAdministrations() {

        List<RailwayAdministrationDTO> response = new ArrayList<>();
        for (var administration: administrationService.getAll()) {
            response.add(RailwayAdministrationDTO.fromModel(administration));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity getAdministrationByCode(@PathVariable String code) {
        Optional<RailwayAdministration> administration = administrationService.getByCode(Integer.parseInt(code));
        if (administration.isEmpty()) {
            return ResponseEntity.badRequest().body("RailwayAdministration with code=" + code + " not found");
        }

        return ResponseEntity.ok(RailwayAdministrationDTO.fromModel(administration.get()));
    }

    @GetMapping("/fullName/{name}")
    public ResponseEntity getAdministrationByFullName(@PathVariable String name) {
        Optional<RailwayAdministration> administration = administrationService.getByFullName(name);
        if (administration.isEmpty()) {
            return ResponseEntity.badRequest().body("RailwayAdministration with name=" + name + " not found");
        }

        return ResponseEntity.ok(RailwayAdministrationDTO.fromModel(administration.get()));
    }

    @GetMapping("/abbreviation/{abbreviation}")
    public ResponseEntity getAdministrationByAbbreviation(@PathVariable String abbreviation) {
        Optional<RailwayAdministration> administration = administrationService.getByAbbreviation(abbreviation);
        if (administration.isEmpty()) {
            return ResponseEntity.badRequest().body("RailwayAdministration with abbreviation=" + abbreviation + " not found");
        }

        return ResponseEntity.ok(RailwayAdministrationDTO.fromModel(administration.get()));
    }

    @PostMapping("")
    public ResponseEntity<String> addAdministration(@RequestBody RailwayAdministration administration) {
        boolean result = administrationService.addAdministration(administration);
        if (result) {
            return ResponseEntity.ok("ЖА была добавлена в БД");
        }
        return ResponseEntity.badRequest().body("ЖА не добавлена в БД");
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<String> deleteAdministrationByCode(@PathVariable String code) {
        boolean result = administrationService.deleteByCode(Integer.parseInt(code));
        if (result) {
            return ResponseEntity.ok("Строка удалена");
        }

        return ResponseEntity.badRequest().body("Строка с кодом = " + code + " не существует");
    }

    @PutMapping("/code/{code}")
    public ResponseEntity<String> updateAdministration(@PathVariable String code, @RequestBody RailwayAdministrationDTO dto) {
        boolean result = administrationService.updateValues(Integer.parseInt(code), dto.toModel());

        if (result) {
            return ResponseEntity.ok("Строка обновлена");
        }

        return ResponseEntity.badRequest().body("Строка не обновлена");
    }
}
