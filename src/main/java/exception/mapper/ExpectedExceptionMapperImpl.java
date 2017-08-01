package exception.mapper;

import exception.BaseExceptionResponse;
import exception.ExceptionType;
import exception.ExpectedException;
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
        BaseExceptionResponse expectedExceptionResponse = new BaseExceptionResponse();
        expectedExceptionResponse.setExceptionType(ExceptionType.EXPECTED_EXCEPTION);
        expectedExceptionResponse.setIncidentId(MDC.get("uniqueId"));
        expectedExceptionResponse
            .addTechnicalMessage(exception.getCalsExceptionInfo().getMessage());
        expectedExceptionResponse.addUserMessage(ErrorMessages.BASE_ERROR_MESSAGE);
        expectedExceptionResponse.setCode(exception.getCalsExceptionInfo().getCode());
        return Response.status(exception.getResponseStatus()).entity(expectedExceptionResponse)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
