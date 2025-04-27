package com.ankur.LogApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Severity is required")
    private String severity;

    @NotNull(message = "Reported time is required")
    private LocalDateTime reportedAt;

    @NotBlank(message = "Source is required")
    private String source;

    public Incident(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Description is required") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is required") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Severity is required") String getSeverity() {
        return severity;
    }

    public void setSeverity(@NotBlank(message = "Severity is required") String severity) {
        this.severity = severity;
    }

    public @NotNull(message = "Reported time is required") LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(@NotNull(message = "Reported time is required") LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public @NotBlank(message = "Source is required") String getSource() {
        return source;
    }

    public void setSource(@NotBlank(message = "Source is required") String source) {
        this.source = source;
    }

    public Incident(Long id, String title, String description, String severity, LocalDateTime reportedAt, String source) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.reportedAt = reportedAt;
        this.source = source;
    }
}
