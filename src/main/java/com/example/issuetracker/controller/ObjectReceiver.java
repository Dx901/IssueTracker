package com.example.issuetracker.controller;

import java.math.BigDecimal;

public class ObjectReceiver {

    private String title;
    private String description;
    private String assignee;
    private String priority;

    private Long issueid;

    public ObjectReceiver() {}


    public Long getIssueid(){
        return issueid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
