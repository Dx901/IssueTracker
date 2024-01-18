Issue Tracker Documentation


Table of Contents
Introduction
Implemented Functionalities
1. Issue Creation
2. Issue Update
3. Data Validation
4. Error Handling
5. Security
6. Integration Testing
7. Areas for Further Attention



Introduction
Issue Tracker is  a web application built using Spring Boot. This document provides documentation for the implemented functionalities, security considerations, and usage guidelines.

Implemented Functionalities
1. Issue Creation
Relevant Information
Users can create new issues by providing the following information:

Title (mandatory)
Description (optional)
Assignee (optional)
Priority (mandatory)
Unique Identifier
The system generates a unique identifier for each newly created issue using the @GeneratedValue(strategy = GenerationType.IDENTITY) annotation in the Issue entity.

2. Issue Update
Ability to Update
Issues can be updated using the provided endpoint:

Endpoint: PUT /api/issues/{issueId}
Example Request:
json
Copy code
{
  "title": "Updated Issue",
  "description": "Updated description",
  "priority": "MEDIUM",
  "assignee": "John Doe"
}
Authorization: Only authorized users can modify issue details.

3. Data Validation
Input Data Validation
Input data is validated to prevent issues with incorrect or malformed data. The following validations are implemented in the Issue entity:

@NotBlank: Title is mandatory.
@Size(max = 255): Title has a maximum length of 255 characters.
@Size(max = 1000): Description has a maximum length of 1000 characters.
@NotNull: Priority is mandatory.
Boundary Cases
Boundary cases, such as maximum lengths for issue titles or descriptions, are tested implicitly to ensure the system handles them appropriately.

4. Error Handling

Meaningful Error Messages
The system provides meaningful error messages in case of issues during issue creation, retrieval, and update.

HTTP Status Codes
The system returns appropriate HTTP status codes with each response. For example:

200 OK: Successful operation.
201 Created: Issue created successfully.
400 Bad Request: Invalid input data or request.
404 Not Found: Issue not found.
500 Internal Server Error: Server-side errors.

5. Security
Sensitive information is handled securely.
Authorization checks for issue modification are not explicitly implemented in the provided code.


6. Integration Testing

Integration tests are implemented using the IssueControllerIntegrationTest class. These tests simulate HTTP requests and verify responses for creating and updating issues.

Implementation Details
Spring Boot Components
IssueController:

Methods for creating, updating, retrieving, listing, and deleting issues have been implemented.
Explicit authorization checks for issue modification are not present.

Issue Entity:

An Issue entity class is defined, including fields for title, description, priority, and assignee.

IssueRepository:

A repository interface extending CrudRepository is implemented for database operations.

IssueService:

Interface defining methods for issue-related operations.

IssueServiceImplementation:

Service implementation provides functionality for creating, updating, retrieving, listing, and deleting issues.

Testing Components
IssueControllerIntegrationTest:

Integration tests ensure correct functioning of issue creation and updating.
Authorization checks for issue modification are not explicitly covered in the provided code.

IssueServiceTest:

Unit tests for service methods like createIssue, updateIssue, and getIssueById.
Mocking of the IssueRepository to isolate service testing.

Areas for Further Attention
Authorization Checks:
Explicit authorization checks for issue modification are not implemented explicitly in the provided code. There will be additional logic to ensure that only authorized users can modify issue details.

HOW TO TEST THE ENDPOINTS


1. POST = CREATE A NEW ISSUE
Set the request type to POST.
Enter the URL: http://localhost:8080/api/issues.

{
"title": "New Issue",
"description": "Description of the new issue",
"priority": "HIGH",
"assignee": "John Doe"
}


2.PUT = Update an Issue:

Set the request type to PUT.
Enter the URL: http://localhost:8080/api/issues/{issueId}, replacing {issueId} with the actual ID of an existing issue.

{
"title": "Updated Issue",
"description": "Updated description of the issue",
"priority": "MEDIUM",
"assignee": "Jane Doe"
}


3.GET = Retrieve a Specific Issue:

Set the request type to GET.
Enter the URL: http://localhost:8080/api/issues/{issueId}, replacing {issueId} with the actual ID of an existing issue.
Click the Send button.
Check the response for the specific issue.


4.GET = Retrieve All Issues:

Set the request type to GET.
Enter the URL: http://localhost:8080/api/issues.
Click the Send button.
Check the response for all issues.

5.DELETE = Delete an Issue:

Set the request type to DELETE.
Enter the URL: http://localhost:8080/api/issues/{issueId}, replacing {issueId} with the actual ID of an existing issue.
Click the Send button.
Check the response for the delete operation.