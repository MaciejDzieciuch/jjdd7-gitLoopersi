package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailParameterCodingService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/email-msg-responder")
public class EmailResponderMessageServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider
        .getTemplate(getServletContext(), "emailMessageResponder.ftlh");

    Map<String, Object> params = EmailParameterCodingService
        .doDecode(req.getParameter("params"));

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(params, printWriter);
    } catch (
        TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}
