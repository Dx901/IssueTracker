package com.example.issuetracker.controller;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.services.IssueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/issues", produces = "application/json")
public class IssueController {
    @Autowired
    private IssueService issueService;

//    @PostMapping
//    public Issue createIssue(@RequestBody Issue issue) {
//        return issueService.createIssue(issue);
//    }


    @PostMapping
    public ResponseEntity<?> createIssue(@Valid @RequestBody Issue issue) {
        try {
            // Validate input data
            if (issue.getTitle() == null || issue.getDescription() == null) {
                // Return a bad request response for missing required fields
                return ResponseEntity.badRequest().body("Title and description are required fields");
            }

            // Implementation
            Issue createdIssue = issueService.createIssue(issue);

            // Return a response with the created issue and HTTP status code 201
            return ResponseEntity.status(200).body(createdIssue);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();

            // Return a response with an error message
            return ResponseEntity.status(getStatusCodeForException(e)).body("Error creating issue: " + e.getMessage());
        }
    }

    private int getStatusCodeForException(Exception e) {
        // return a server error
        return 500;
    }

    @PutMapping("/{issueId}")
    public Issue updateIssue(@PathVariable Long issueId, @RequestBody Issue updatedIssue) {
        return issueService.updateIssue(issueId, updatedIssue);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<?> getIssueById(@PathVariable Long issueId) {
        try {
            // Validate issueId
            if (issueId == null || issueId <= 0) {
                return new ResponseEntity<>("Invalid issueId", HttpStatus.BAD_REQUEST);
            }

            Issue issue = issueService.getIssueById(issueId);
            if (issue == null) {
                return new ResponseEntity<>("Issue not found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(issue, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving issue: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public Iterable<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long issueId) {
        try {
            // Validate issueId
            if (issueId == null || issueId <= 0) {
                return new ResponseEntity<>("Invalid issueId", HttpStatus.BAD_REQUEST);
            }

            issueService.deleteIssue(issueId);
            return new ResponseEntity<>("Issue deleted successfully", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting issue: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
