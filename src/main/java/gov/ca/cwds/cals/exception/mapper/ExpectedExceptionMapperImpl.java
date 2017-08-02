package gov.ca.cwds.cals.exception.mapper;

import gov.ca.cwds.cals.Constants.ErrorMessages;
import gov.ca.cwds.cals.exception.BaseExceptionResponse;
import gov.ca.cwds.cals.exception.ExceptionType;
import gov.ca.cwds.cals.exception.ExpectedException;
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
