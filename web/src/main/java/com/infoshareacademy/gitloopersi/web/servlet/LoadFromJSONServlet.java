package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.handler.JsonFileHandler;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/jsonLoader")
@MultipartConfig
public class LoadFromJSONServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private JsonFileHandler jsonFileHandler;

  @Inject
  private CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Part jsonFile = req.getPart("file");

    jsonFileHandler.prepareFileToLoadToDataBase(jsonFile);
    logger.info("Json file {} loaded", jsonFile);

    resp.sendRedirect("/admin/jsonLoader");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("userType", "admin");
    dataModel.put("function", "FileLoader");
    dataModel.put("dates", dates);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}