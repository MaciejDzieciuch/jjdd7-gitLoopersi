package com.infoshareacademy.gitloopersi.service.alertmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Stateless
public class UserMessagesService {

  private static final String ERROR_MESSAGE_ATTR_KEY = "errorMessage";
  private static final String SUCCESS_MESSAGE_ATTR_KEY = "successMessage";

  public List<String> getErrorMessageList(HttpSession httpSession) {
    return (List<String>) httpSession.getAttribute(ERROR_MESSAGE_ATTR_KEY);
  }

  public void addErrorMessage(HttpSession httpSession, String message) {

    if (getErrorMessageList(httpSession) == null) {
      List<String> errorMessages = new ArrayList<>();
      errorMessages.add(message);
      httpSession.setAttribute(ERROR_MESSAGE_ATTR_KEY, errorMessages);
    } else {
      List<String> errorMessages = getErrorMessageList(httpSession);
      errorMessages.add(message);
      httpSession.setAttribute(ERROR_MESSAGE_ATTR_KEY, errorMessages);
    }
  }

  public List<String> getSuccessMessageList(HttpSession httpSession) {
    return (List<String>) httpSession.getAttribute(SUCCESS_MESSAGE_ATTR_KEY);
  }

  public void addSuccessMessage(HttpSession httpSession, String message) {

    if (getSuccessMessageList(httpSession) == null) {
      List<String> successMessages = new ArrayList<>();
      successMessages.add(message);
      httpSession.setAttribute(SUCCESS_MESSAGE_ATTR_KEY, successMessages);
    } else {
      List<String> successMessages = getSuccessMessageList(httpSession);
      successMessages.add(message);
      httpSession.setAttribute(SUCCESS_MESSAGE_ATTR_KEY, successMessages);
    }
  }

  public void removeErrorMessages(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute(ERROR_MESSAGE_ATTR_KEY);
  }

  public void removeSuccessMessages(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute(SUCCESS_MESSAGE_ATTR_KEY);
  }
}