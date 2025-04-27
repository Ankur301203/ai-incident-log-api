package com.ankur.LogApi.service;

import com.ankur.LogApi.model.Incident;
import com.ankur.LogApi.repository.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {
    private static final Logger log = LoggerFactory.getLogger(IncidentService.class);
    private final IncidentRepository incidentRepository;
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }
    public List<Incident> getIncidents() {
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(Long id) {
        Optional<Incident> incident = incidentRepository.findById(id);
        return incident.orElse(null);
    }

    public Incident createIncident(Incident incident) {
        log.info("Created a incident with id: {}", incident.getId());
        return incidentRepository.save(incident);
    }

    public boolean deleteById(Long id) {
        if(incidentRepository.existsById(id)) {
            incidentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Incident updateIncident(Long id, Incident updatedIncident) {
        Optional<Incident> existingIncidentOpt = incidentRepository.findById(id);

        if (existingIncidentOpt.isPresent()) {
            Incident existingIncident = existingIncidentOpt.get();
            updateIncident(updatedIncident, existingIncident);
            return incidentRepository.save(existingIncident);
        }
        return null;
    }

    public static void updateIncident(Incident updatedIncident, Incident existingIncident) {
        if (updatedIncident.getTitle() != null) {
            existingIncident.setTitle(updatedIncident.getTitle());
        }
        if (updatedIncident.getDescription() != null) {
            existingIncident.setDescription(updatedIncident.getDescription());
        }
        if (updatedIncident.getSeverity() != null) {
            existingIncident.setSeverity(updatedIncident.getSeverity());
        }
        if (updatedIncident.getReportedAt() != null) {
            existingIncident.setReportedAt(updatedIncident.getReportedAt());
        }
        if (updatedIncident.getSource() != null) {
            existingIncident.setSource(updatedIncident.getSource());
        }
    }
}
