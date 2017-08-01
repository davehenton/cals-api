package exception;

import gov.ca.cwds.cals.Constants.ErrorMessages;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.MDC;

/**
 * @author CWDS CALS API Team
 */

@Provider
public class ExpectedExceptionMapperImpl implements ExceptionMapper<ExpectedException> {

    @Override
    public Response toResponse(ExpectedException exception) {
        ExpectedExceptionResponse expectedExceptionResponse = new ExpectedExceptionResponse();
        expectedExceptionResponse.setIncidentId(MDC.get("uniqueId"));
        expectedExceptionResponse.getTechnicalMessages()
            .add(exception.getCalsExceptionInfo().getMessage());
        expectedExceptionResponse.getUserMessages().add(ErrorMessages.BASE_ERROR_MESSAGE);
        expectedExceptionResponse.setCode(exception.getCalsExceptionInfo().getCode());
        return Response.status(exception.getResponseStatus()).entity(expectedExceptionResponse)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
