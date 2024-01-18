package com.example.issuetracker.repository;

import com.example.issuetracker.models.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Repository interface for performing CRUD operations on Issue entities
@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {
}
