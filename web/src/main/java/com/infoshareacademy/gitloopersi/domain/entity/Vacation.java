package com.infoshareacademy.gitloopersi.domain.entity;

import com.infoshareacademy.gitloopersi.types.StatusType;
import com.infoshareacademy.gitloopersi.types.VacationType;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

@NamedQueries({
    @NamedQuery(
        name = "Vacation.findAll",
        query = "SELECT v FROM Vacation v"
    ),
    @NamedQuery(
        name = "Vacation.findAllInTeam",
        query = "SELECT v FROM Vacation v "
            + "LEFT JOIN Employee e "
            + "ON v.employee.id = e.id "
            + "LEFT JOIN Team t "
            + "ON e.team.id = t.id "
            + "WHERE t.id=:id"
    ),
    @NamedQuery(
        name = "Vacation.findForEmployeeInTeam",
        query = "SELECT v FROM Vacation v "
            + "LEFT JOIN Employee e "
            + "ON v.employee.id = e.id "
            + "LEFT JOIN Team t "
            + "ON e.team.id = t.id "
            + "WHERE t.id=:teamId "
            + "AND e.id=:employeeId"
    ),
    @NamedQuery(
        name = "Vacation.findAllForEmployee",
        query = "SELECT v FROM Vacation v "
            + "LEFT JOIN Employee e "
            + "ON v.employee.id = e.id "
            + "WHERE e.id=:id"
    )
})
@Entity
@Table(name = "vacation")
public class Vacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,
      CascadeType.PERSIST})
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @Column(name = "date_from")
  @NotNull
  private LocalDate dateFrom;

  @Column(name = "date_to")
  @NotNull
  private LocalDate dateTo;

  @Column(name = "days_count")
  @NotNull
  private Integer daysCount;

  @Column(name = "deputy")
  private String deputy;

  @Column(name = "vacation_type")
  private VacationType vacationType;

  @Column(name = "status_type")
  private StatusType statusType = StatusType.REQUESTED;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creating_date")
  private Date creatingDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public void setDateFrom(LocalDate dateFrom) {
    this.dateFrom = dateFrom;
  }

  public LocalDate getDateTo() {
    return dateTo;
  }

  public void setDateTo(LocalDate dateTo) {
    this.dateTo = dateTo;
  }

  public Integer getDaysCount() {
    return daysCount;
  }

  public void setDaysCount(Integer daysCount) {
    this.daysCount = daysCount;
  }

  public StatusType getStatusType() {
    return statusType;
  }

  public void setStatusType(StatusType statusType) {
    this.statusType = statusType;
  }

  public VacationType getVacationType() {
    return vacationType;
  }

  public void setVacationType(VacationType vacationType) {
    this.vacationType = vacationType;
  }

  public String getDeputy() {
    return deputy;
  }

  public void setDeputy(String deputy) {
    this.deputy = deputy;
  }

  public Date getCreatingDate() {
    return creatingDate;
  }

  public void setCreatingDate(Date createDate) {
    this.creatingDate = createDate;
  }

  @Override
  public String toString() {
    return "Vacation{" +
        "id=" + id +
        ", employee=" + employee +
        ", dateFrom=" + dateFrom +
        ", dateTo=" + dateTo +
        ", daysCount=" + daysCount +
        ", deputy='" + deputy + '\'' +
        ", vacationType=" + vacationType +
        ", statusType=" + statusType +
        ", createDate=" + creatingDate +
        '}';
  }
}
