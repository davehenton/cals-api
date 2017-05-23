package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;

import java.io.Serializable;

import static gov.ca.cwds.cals.Constants.UNIT_OF_WORK.CMS;
import static gov.ca.cwds.cals.Constants.UNIT_OF_WORK.FAS;
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
    private PlacementHomeDao placementHomeDao;
    private FacilityMapper facilityMapper;

    @Inject
    public FacilityService(LisFacFileDao lisFacFileDao, PlacementHomeDao placementHomeDao, FacilityMapper facilityMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.placementHomeDao = placementHomeDao;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public Response find(Serializable params) {
        Response response = null;
        FacilityParameterObject parameterObject = (FacilityParameterObject) params;
        if (FAS.equals(parameterObject.getUnitOfWork())) {
            LisFacFile lisFacFile = findFacilityByLicenseNumber(parameterObject);
            response = facilityMapper.toFacilityDTO(lisFacFile);
        } else if (CMS.equals(parameterObject.getUnitOfWork())) {
            PlacementHome placementHome = findFacilityById(parameterObject);
            response = facilityMapper.toFacilityDTO(placementHome);
        }
        return response;
    }

    @UnitOfWork(FAS)
    protected LisFacFile findFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
        return lisFacFileDao.find(parameterObject.getLicenseNumber());
    }

    @UnitOfWork(CMS)
    protected PlacementHome findFacilityById(FacilityParameterObject parameterObject) {
        return placementHomeDao.find(parameterObject);
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
