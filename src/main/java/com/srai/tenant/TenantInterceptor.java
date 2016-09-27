package com.srai.tenant;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

  private static final String TENANT_HEADER = "X-TenantID";

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
      throws Exception {

    String tenant = req.getHeader(TENANT_HEADER);
    boolean tenantSet = false;

    if(StringUtils.isEmpty(tenant)) {
      res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      res.setContentType(MediaType.APPLICATION_JSON_VALUE);
      res.getWriter().write("{\"error\": \"No tenant supplied\"}");
      res.getWriter().flush();
    } else {
      TenantContext.setCurrentTenant(tenant);
      tenantSet = true;
    }

    return tenantSet;
  }
}