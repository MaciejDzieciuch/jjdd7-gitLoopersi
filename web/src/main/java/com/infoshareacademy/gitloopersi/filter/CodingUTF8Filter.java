package com.infoshareacademy.gitloopersi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "CodingUTF8Filter",
    urlPatterns = {"/*"}
)
public class CodingUTF8Filter implements Filter {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  private final String codingType = "UTF-8";

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    logger.info("Coding type is to set");
    servletResponse.setCharacterEncoding(codingType);
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
