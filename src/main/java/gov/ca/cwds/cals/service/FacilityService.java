package gov.ca.cwds.cals.service;

import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;

import java.io.Serializable;

import static gov.ca.cwds.cals.Constants.UNIT_OF_WORK.LIS;

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

    public FacilityService(LisFacFileDao lisFacFileDao, FacilityMapper facilityMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public Response find(Serializable facilityId) {
        LisFacFile lisFacFile = findFacilityById(facilityId);
        return facilityMapper.lisFacilityToFacilityDTO(lisFacFile);
    }

    @UnitOfWork(LIS)
    protected LisFacFile findFacilityById(Serializable facilityId) {
        return lisFacFileDao.find(facilityId);
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
