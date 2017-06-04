package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.cms.CountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.StaffPerson;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lis.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;

import java.io.Serializable;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

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
    private CountiesDao countiesDao;
    private FacilityMapper facilityMapper;
    private LpaInformationDao lpaInformationDao;

    @Inject
    public FacilityService(LisFacFileDao lisFacFileDao, PlacementHomeDao placementHomeDao,
                           LpaInformationDao lpaInformationDao, CountiesDao countiesDao, FacilityMapper facilityMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.placementHomeDao = placementHomeDao;
        this.lpaInformationDao = lpaInformationDao;
        this.countiesDao = countiesDao;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public Response find(Serializable params) {
        Response response = null;
        FacilityParameterObject parameterObject = (FacilityParameterObject) params;
        if (LIS.equals(parameterObject.getUnitOfWork())) {
            LisFacFile lisFacFile = findFacilityByLicenseNumber(parameterObject);
            LpaInformation lpaInformation = lisFacFile != null ? findAssignedWorkerInformation(lisFacFile) : null;
            response = facilityMapper.toFacilityDTO(lisFacFile, lpaInformation);
        } else if (CMS.equals(parameterObject.getUnitOfWork())) {
            PlacementHome placementHome = findFacilityById(parameterObject);
            response = facilityMapper.toFacilityDTO(placementHome);
        }
        return response;
    }

    @UnitOfWork(LIS)
    protected LisFacFile findFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
        return lisFacFileDao.find(parameterObject.getLicenseNumber());
    }

    @UnitOfWork(FAS)
    protected LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
        if (lisFacFile.getFacDoNbr() == null) {
            throw new UserFriendlyException(DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN, EXPECTATION_FAILED);
        }
        String lpaCode = String.format("%02d", lisFacFile.getFacDoNbr().getDoNbr()) + lisFacFile.getFacDoEvalCode();
        return lpaInformationDao.findByLpaCode(lpaCode);
    }

    @UnitOfWork(CMS)
    protected PlacementHome findFacilityById(FacilityParameterObject parameterObject) {
        PlacementHome placementHome = placementHomeDao.find(parameterObject);
        CountyLicenseCase countyLicenseCase = placementHome.getCountyLicenseCase();
        if (countyLicenseCase != null) {
            StaffPerson staffPerson = countyLicenseCase.getStaffPerson();
            if (staffPerson != null) {
                County county = countiesDao.findByLogicalId(staffPerson.getCntySpfcd());
                staffPerson.setCounty(county);
            }
        }
        return placementHome;
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
