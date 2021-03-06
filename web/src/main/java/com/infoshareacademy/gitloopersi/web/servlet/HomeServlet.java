package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

  private static final String USER_TYPE = "userType";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private CalendarService calendarService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String userType;

    if (req.getSession().getAttribute(USER_TYPE) == null) {
      userType = "guest";
    } else {
      userType = String.valueOf(req.getSession().getAttribute(USER_TYPE));
    }

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    List<Calendar> dates = calendarService.findAllHolidaysDates();

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put(USER_TYPE, userType);
    dataModel.put("function", "HomePage");
    dataModel.put("dates", dates);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

}