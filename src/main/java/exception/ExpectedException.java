package exception;

import javax.ws.rs.core.Response.Status;

/**
 * @author CWDS CALS API Team
 */

public class ExpectedException extends RuntimeException {

  private final ExpectedExceptionInfo calsExceptionInfo;

    private final Status responseStatus;

  public ExpectedException(ExpectedExceptionInfo calsExceptionInfo, Status responseStatus) {
        this.calsExceptionInfo = calsExceptionInfo;
        this.responseStatus = responseStatus;
    }

  public ExpectedExceptionInfo getCalsExceptionInfo() {
        return calsExceptionInfo;
    }

    public Status getResponseStatus() {
        return responseStatus;
    }
}
