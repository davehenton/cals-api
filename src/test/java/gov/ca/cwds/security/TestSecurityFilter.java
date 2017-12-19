package gov.ca.cwds.security;

import gov.ca.cwds.security.realm.PerryAccount;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashSet;
import java.util.Set;

import static gov.ca.cwds.cals.Utils.StaffPerson.DEFAULT_USER_ID;

/**
 * @author CWDS CALS API Team
 */

public class TestSecurityFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        AuthenticationToken token = new AuthenticationToken() {
            @Override
            public Object getPrincipal() {
                PerryAccount perryAccount = new PerryAccount();
                perryAccount.setStaffId(DEFAULT_USER_ID);
                Set<String> privileges = new HashSet<>();
                privileges.add("Resource Mgmt Placement Facility Maint");
                privileges.add("SOC158 Application");
                perryAccount.setPrivileges(privileges);
                return perryAccount;
            }

            @Override
            public Object getCredentials() {
                return new Object();
            }
        };
        return token;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        return executeLogin(request, response);
    }

}
