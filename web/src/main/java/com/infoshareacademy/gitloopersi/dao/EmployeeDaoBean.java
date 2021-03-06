package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmployeeDaoBean {

  @PersistenceContext
  EntityManager entityManager;
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void addEmployee(Employee employee) {
    logger.info("Employee object [{} {}] is to be merged to DB", employee.getFirstName(),
        employee.getSecondName());
    entityManager.persist(employee);
  }

  public Employee editEmployee(Employee employee) {
    logger.info("Employee object id={} is to be updated in DB", employee.getId());
    return entityManager.merge(employee);
  }

  public Employee getEmployeeById(Long id) {
    logger.info("Employee object id={} is to be get from DB", id);
    return entityManager.find(Employee.class, id);
  }

  public void deleteEmployeeById(Long id) {
    logger.info("Employee object id={} is to be deleted from DB", id);
    Employee employee = getEmployeeById(id);
    if (employee != null) {
      entityManager.remove(employee);
    } else {
      logger.warn("An attempt to delete non-existing employee object id={} has occurred", id);
    }
  }

  public List<Employee> getEmployeesList() {
    logger.info("Employee objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("Employee.findAll");
    return query.getResultList();
  }

  public Employee getEmployeeByEmail(String email) {
    Query query = entityManager.createNamedQuery("Employee.findEmployeeByEmail");
    query.setParameter("email", email);
    return (Employee) query.getResultList().stream().findFirst().orElse(null);
  }

  public List<Employee> getEmployeeListFromTeam(Long id) {
    Query query = entityManager.createNamedQuery("Employee.findEmployeesFromTeam");
    query.setParameter("id", id);
    return query.getResultList();
  }
}