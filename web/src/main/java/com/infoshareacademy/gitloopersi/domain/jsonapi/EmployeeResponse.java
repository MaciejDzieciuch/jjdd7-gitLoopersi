package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"locations", "states"})

public class EmployeeResponse {

  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("secondName")
  private String secondName;
  @JsonProperty("email")
  private String email;
  @JsonProperty("team")
  private TeamResponse team;
  @JsonProperty("startDate")
  private Date startDate;
  @JsonProperty("startHireDate")
  private Date startHireDate;
  @JsonProperty("admin")
  private boolean admin;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public TeamResponse getTeam() {
    return team;
  }

  public void setTeam(TeamResponse team) {
    this.team = team;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getStartHireDate() {
    return startHireDate;
  }

  public void setStartHireDate(Date startHireDate) {
    this.startHireDate = startHireDate;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}