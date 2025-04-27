package com.ankur.LogApi.repository;

import com.ankur.LogApi.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
