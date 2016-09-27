package com.srai.tenant.hibernate;

import com.srai.tenant.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

  @Override
  public String resolveCurrentTenantIdentifier() {
    return TenantContext.getCurrentTenant();
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}