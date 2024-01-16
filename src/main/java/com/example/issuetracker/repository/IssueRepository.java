package com.example.issuetracker.repository;

import com.example.issuetracker.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@org.springframework.stereotype.Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {
}
