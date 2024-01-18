package com.example.issuetracker.models;

import com.example.issuetracker.constants.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Issue {

    // Unique identifier for the issue
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the issue, cannot be blank and limited to 255 characters
    @NotBlank
    @Size(max = 255)
    private String title;

    // Description of the issue, limited to 1000 characters
    @Size(max = 1000)
    private String description;

    // Priority of the issue, cannot be null and represented as an enumerated type
    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    // Default constructor for JPA
    public Issue() {
    }

    // Getters and setters

    // Get the unique identifier of the issue
    public Long getId() {
        return id;
    }

    // Set the unique identifier of the issue
    public void setId(Long id) {
        this.id = id;
    }

    // Get the title of the issue
    public String getTitle() {
        return title;
    }

    // Set the title of the issue
    public void setTitle(String title) {
        this.title = title;
    }

    // Get the description of the issue
    public String getDescription() {
        return description;
    }

    // Set the description of the issue
    public void setDescription(String description) {
        this.description = description;
    }

    // Get the priority of the issue
    public Priority getPriority() {
        return priority;
    }

    // Set the priority of the issue
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
