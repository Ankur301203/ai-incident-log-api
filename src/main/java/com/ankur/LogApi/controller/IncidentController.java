package com.ankur.LogApi.controller;

import com.ankur.LogApi.model.Incident;
import com.ankur.LogApi.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getIncidents();
        return new ResponseEntity<>(incidents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIncidentById(@PathVariable Long id) {
        Incident incident = incidentService.getIncidentById(id);
        if(incident == null){
            return new ResponseEntity<>("Incident not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(incident,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createIncident(
            @Valid @RequestBody Incident incident,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Incident savedIncident = incidentService.createIncident(incident);
        return new ResponseEntity<>(savedIncident,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIncident(@PathVariable Long id) {
        boolean deleted = incidentService.deleteById(id);
        if(!deleted){
            return new ResponseEntity<>("Incident not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Incident deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateIncident(@PathVariable Long id,
                                                 @Valid @RequestBody Incident incident,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult,HttpStatus.BAD_REQUEST);
        }
        Incident existingIncident = incidentService.getIncidentById(id);
        if(existingIncident == null){
            return new ResponseEntity<>("Incident not found", HttpStatus.NOT_FOUND);
        }
        existingIncident.setId(id);
        IncidentService.updateIncident(incident, existingIncident);

        Incident updatedIncident = incidentService.updateIncident(id,existingIncident);
        return new ResponseEntity<>(updatedIncident,HttpStatus.OK);
    }
}
