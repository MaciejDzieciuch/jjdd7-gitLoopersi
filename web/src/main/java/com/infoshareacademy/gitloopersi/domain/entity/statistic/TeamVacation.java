package com.infoshareacademy.gitloopersi.domain.entity.statistic;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "TeamVacation.findAll",
        query = "SELECT tv FROM TeamVacation tv ORDER BY tv.quantity"
    )
}
)
@Entity
@Table(name = "team_vacation")
public class TeamVacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(mappedBy = "teamVacation")
  private Team team;

  @Column(name = "quantity")
  @NotNull
  private Integer quantity=0;

  public TeamVacation() {
  }

  public TeamVacation(Team team, @NotNull Integer quantity) {
    this.team = team;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "TeamVacation{" +
        "id=" + id +
        ", team=" + team +
        ", quantity=" + quantity +
        '}';
  }
}