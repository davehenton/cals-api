package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.fas.FacilityType;
import gov.ca.cwds.cals.persistence.dao.fas.FacilityTypeDao;
import gov.ca.cwds.cals.service.dto.FacilityTypeDTO;
import gov.ca.cwds.cals.service.dto.FacilityTypesDTO;
import gov.ca.cwds.cals.service.mapper.FacilityTypeMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityTypeCollectionService extends CrudServiceAdapter {
    private FacilityTypeDao facilityTypeDao;
    private FacilityTypeMapper facilityTypeMapper;

    @Inject
    public FacilityTypeCollectionService(FacilityTypeDao facilityTypeDao, FacilityTypeMapper facilityTypeMapper) {
        this.facilityTypeDao = facilityTypeDao;
        this.facilityTypeMapper = facilityTypeMapper;
    }

    @Override
    public Response find(Serializable params) {
        List<FacilityType> facilityTypes = facilityTypeDao.findAll();
        List<FacilityTypeDTO> facilityTypeDTOs = facilityTypeMapper.toFacilityTypeDTO(facilityTypes);
        FacilityTypesDTO facilityTypesDTO = new FacilityTypesDTO();
        facilityTypesDTO.setFacilityTypes(facilityTypeDTOs);
        return facilityTypesDTO;
    }
}
