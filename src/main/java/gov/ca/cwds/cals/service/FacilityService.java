package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.logging.SyslogAppenderFactory;

import java.io.Serializable;

/**
 *
 *  CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 *  @author CALS API Team
 *
 */

public class FacilityService implements CrudsService {

    private LisFacFileDao lisFacFileDao;
    private FacilityMapper facilityMapper;

    @Inject
    public FacilityService(LisFacFileDao lisFacFileDao, FacilityMapper facilityMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public Response find(Serializable id) {
        LisFacFile lisFacFile = lisFacFileDao.find(id);
        return facilityMapper.facilityToFacilityDTO(lisFacFile);
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
