package gov.ca.cwds.cals.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CountiesServiceBackendResource;
import gov.ca.cwds.cals.service.dto.CountiesDTO;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static gov.ca.cwds.cals.Constants.API.COUNTIES;
import static gov.ca.cwds.cals.Constants.API.DICTIONARY;

/**
 * @author CWDS CALS API Team
 */

@Api(value = COUNTIES, tags = {DICTIONARY, COUNTIES})
@Path(DICTIONARY + "/" + COUNTIES)
@Produces(MediaType.APPLICATION_JSON)
public class CountiesResource {

    private ResourceDelegate countiesResourceDeledate;

    @Inject
    public CountiesResource(@CountiesServiceBackendResource ResourceDelegate countiesResourceDeledate) {
        this.countiesResourceDeledate = countiesResourceDeledate;
    }

    @UnitOfWork(value = "cms")
    @GET
    @Timed
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 406, message = "Accept Header not supported")})
    @ApiOperation(value = "Returns List of counties ", response = CountiesDTO.class)
    public Response getCounties() {
        return countiesResourceDeledate.get(null);
    }

}
