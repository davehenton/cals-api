package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.domain.Facility;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;

/**
 *
 *  CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 *  @author CALS API Team
 *
 */

public class FacilityService implements CrudsService {

    private FacilityMapper facilityMapper;

    @Inject
    public FacilityService(FacilityMapper facilityMapper) {
       this.facilityMapper = facilityMapper;
    }

    @Override
    public Response find(Serializable id) {
        //facilityDao find by id
        Facility facility = new Facility();
        facility.setId(212521l);
        //end of facilityDao

        return facilityMapper.facilityToFacilityDTO(facility);
    }

    @Override
    public Response delete(Serializable serializable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response create(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response update(Serializable serializable, Request request) {
        throw new UnsupportedOperationException();
    }

}
