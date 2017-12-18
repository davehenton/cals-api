package gov.ca.cwds.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

/**
 * @author CWDS CALS API Team
 */

public class TestSecurityFilter extends AuthenticatingFilter {

  @Override
  protected AuthenticationToken createToken(ServletRequest request, ServletResponse response)
      throws Exception {
    return null;
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
      throws Exception {
    return false;
  }
}
