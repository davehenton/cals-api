package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.fas.Rrcpoc;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityInspectionParameterObject;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionService extends CrudServiceAdapter {

    private FacilityInspectionMapper facilityInspectionMapper;
    private InspectionDao inspectionDao;


    @Inject
    public FacilityInspectionService(FacilityInspectionMapper facilityInspectionMapper, InspectionDao inspectionDao) {
        this.facilityInspectionMapper = facilityInspectionMapper;
        this.inspectionDao = inspectionDao;
    }

    @Override
    public Response find(Serializable params) {
        FacilityInspectionParameterObject paramsObj = (FacilityInspectionParameterObject) params;
        Rrcpoc rrcpoc = inspectionDao.getByFacilityNumberAndInspectionId(
                paramsObj.getFacilityId(), paramsObj.getInspectionId());
        FacilityInspectionDTO dto = facilityInspectionMapper.toFacilityInspectionDto( rrcpoc);
        return dto;
    }

}
