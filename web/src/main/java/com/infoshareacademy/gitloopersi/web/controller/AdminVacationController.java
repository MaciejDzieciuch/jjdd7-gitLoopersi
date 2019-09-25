package com.infoshareacademy.gitloopersi.web.controller;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailVacationService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.StatusType;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin/vacation")
public class AdminVacationController {

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private EmailVacationService emailVacationService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private UserMessagesService userMessagesService;

  private static final String EMPLOYEE_ID = "employeeId";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PUT
  @Path("/reject/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response rejectVacation(@PathParam("id") Long id, @Context HttpServletRequest req) {
    logger.info("Api vacation editing: {}", id);

    Long employeeId = (Long) req.getSession().getAttribute(EMPLOYEE_ID);

    Vacation vacation = vacationDefiningService.getByVacationId(id);

    vacation.setStatusType(StatusType.REJECTED);

    String message = String.format("Vacation ID[%d] was changed status type %s", id,
        vacation.getStatusType().getType());

    userMessagesService.addSuccessMessage(req.getSession(), message);

    List<String> successMessageList = userMessagesService.getSuccessMessageList(req.getSession());

    userMessagesService.removeSuccessMessages(req);

    Employee employee = employeeService.getEmployeeById(employeeId);

    emailVacationService.buildEmailVacationManage(vacation, employee);

    vacationDefiningService.editVacation(vacation);

    return Response.ok().entity(successMessageList).build();
  }

  @PUT
  @Path("/accept/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response acceptVacation(@PathParam("id") Long id, @Context HttpServletRequest req) {
    logger.info("Api vacation editing: {}", id);

    Long employeeId = (Long) req.getSession().getAttribute(EMPLOYEE_ID);

    Vacation vacation = vacationDefiningService.getByVacationId(id);

    vacation.setStatusType(StatusType.ACCEPTED);

    String message = String.format("Vacation ID[%d] was changed status type %s", id,
        vacation.getStatusType().getType());

    userMessagesService.addSuccessMessage(req.getSession(), message);

    List<String> successMessageList = userMessagesService.getSuccessMessageList(req.getSession());

    userMessagesService.removeSuccessMessages(req);

    Employee employee = employeeService.getEmployeeById(employeeId);

    emailVacationService.buildEmailVacationManage(vacation, employee);

    vacationDefiningService.editVacation(vacation);

    return Response.ok().entity(successMessageList).build();
  }
}