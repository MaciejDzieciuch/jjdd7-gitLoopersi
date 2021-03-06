package com.infoshareacademy.gitloopersi.validator;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.handler.VacationDefiningHandler;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.VacationType;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class VacationDefiningValidator {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  @Inject
  private VacationDefiningHandler vacationDefiningHandler;
  @EJB
  private VacationDefiningService vacationDefiningService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());
  private LocalDate dateToday = LocalDate.now();

  public boolean isValidDateFrom(String dateFrom) {

    try {
      logger.info("Validate correct format dateFrom {}", dateFrom);
      simpleDateFormat.parse(dateFrom);

    } catch (ParseException e) {
      logger.warn(e.getMessage());
      return false;
    }
    return true;
  }

  public boolean isValidDateTo(String dateTo) {

    try {
      logger.info("Validate correct format dateTo {}", dateTo);
      simpleDateFormat.parse(dateTo);

    } catch (ParseException e) {
      logger.warn(e.getMessage());
      return false;
    }
    return true;
  }

  public boolean isValidDateFromFuture(String dateFrom) {

    logger.info("Validate whether the dateFrom {} is from the future", dateFrom);
    LocalDate dateFromVacation = LocalDate.parse(dateFrom);

    Timestamp timestampDateFrom = Timestamp.valueOf(dateFromVacation.atTime(LocalTime.MIDNIGHT));
    Long dateFromCount = timestampDateFrom.getTime();

    Timestamp timestampToday = Timestamp.valueOf(dateToday.atTime(LocalTime.MIDNIGHT));
    Long todayCount = timestampToday.getTime();

    return todayCount < dateFromCount;
  }

  public boolean isValidDateToFuture(String dateTo) {

    logger.info("Validate whether the dateTo {} is from the future", dateTo);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    Timestamp timestampDateTo = Timestamp.valueOf(dateToVacation.atTime(LocalTime.MIDNIGHT));
    Long dateToCount = timestampDateTo.getTime();

    Timestamp timestampToday = Timestamp.valueOf(dateToday.atTime(LocalTime.MIDNIGHT));
    Long todayCount = timestampToday.getTime();

    return todayCount < dateToCount;
  }

  public boolean isValidDateFromBeforeDateTo(String dateFrom, String dateTo) {

    logger.info("Validate whether the dateFrom {} is before dateTo", dateFrom);
    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    Timestamp timestampDateFrom = Timestamp.valueOf(dateFromVacation.atTime(LocalTime.MIDNIGHT));
    Long dateFromCount = timestampDateFrom.getTime();

    Timestamp timestampDateTo = Timestamp.valueOf(dateToVacation.atTime(LocalTime.MIDNIGHT));
    Long dateToCount = timestampDateTo.getTime();

    return dateFromCount <= dateToCount;
  }

  public boolean isValidOverlappingOfDates(Long id, String dateFrom, String dateTo) {

    List<Vacation> vacations = vacationDefiningService.getVacationsList().stream()
        .filter(vacation -> vacation.getEmployee().getId().equals(id))
        .collect(Collectors.toList());

    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    for (Vacation dateRange : vacations) {
      for (LocalDate existDate = dateRange.getDateFrom(); existDate.isBefore(dateRange.getDateTo());
          existDate = existDate.plusDays(1)) {
        for (LocalDate definingDate = dateFromVacation; definingDate.isBefore(dateToVacation);
            definingDate = definingDate.plusDays(1)) {
          if (definingDate.equals(existDate)) {
            logger.info("The dates given overlap {} - {}", dateFromVacation, dateToVacation);
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isValidTurnOfTheYear(String dateFrom, String dateTo) {

    logger.info("The dates are at the turn of the year {} - {}", dateFrom, dateTo);
    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    return (dateToday.getYear() == dateFromVacation.getYear()
        && dateToday.getYear() == dateToVacation.getYear()) ||
        (dateToday.plusYears(1).getYear() == dateFromVacation.getYear()
            && dateToday.plusYears(1).getYear() == dateToVacation.getYear());
  }

  public boolean isValidVacationType(String vacationType) {

    logger.info("Validating vacation type {}", vacationType);

    return VacationType.containsValueType(vacationType);
  }

  public int calculateVacationPoolForEmployee(Long employeeId) throws IOException {
    return vacationDefiningHandler.calculateVacationPoolForEmployee(employeeId);
  }

  public int calculateNumberOfSelectedVacationDays(String dateFrom, String dateTo) {
    return vacationDefiningHandler.calculateNumberOfSelectedVacationDays(dateFrom, dateTo);
  }

  public int calculateRemainingVacationPool(Long employeeId, int numberOfSelectedVacationDays,
      int numberOfVacationPool) throws IOException {

    return vacationDefiningHandler
        .calculateRemainingVacationPool(employeeId, numberOfSelectedVacationDays,
            numberOfVacationPool);
  }

  public int calculateVacationPoolChildcare(Long employeeId, int numberOfSelectedVacationDays,
      int numberOfVacationPool) throws IOException {

    return vacationDefiningHandler
        .calculateVacationPoolChildcare(employeeId, numberOfSelectedVacationDays,
            numberOfVacationPool);
  }

  public int calculateVacationPoolSpecialLeave(Long employeeId, int numberOfSelectedVacationDays,
      int numberOfVacationPool) throws IOException {

    return vacationDefiningHandler
        .calculateVacationPoolSpecialLeave(employeeId, numberOfSelectedVacationDays,
            numberOfVacationPool);
  }
}