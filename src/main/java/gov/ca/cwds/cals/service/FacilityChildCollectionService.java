package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.UnitOfWork;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDto;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.cms.data.access.service.impl.ClientCoreService;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {

  @Inject
  private ClientCoreService clientService;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    FacilityChildrenDto facilityChildrenDTO = new FacilityChildrenDto();
    if (parameterObject.getUnitOfWork().equals(UnitOfWork.CMS)) {
      facilityChildrenDTO.getChildren()
          .addAll(getChildrenFromCwsFacility(parameterObject.getFacilityId()));
    } else {
      facilityChildrenDTO.getChildren()
          .addAll(getChildrenFromLisFacility(parameterObject.getFacilityId()));
    }
    return facilityChildrenDTO.getChildren().isEmpty() ? null : facilityChildrenDTO;
  }

  private List<FacilityChildDTO> getChildrenFromCwsFacility(String facilityId) {
    return clientService.streamByFacilityId(facilityId).stream()
        .map(facilityChildMapper::toFacilityChildDTO)
        .collect(Collectors.toList());
  }

  private List<FacilityChildDTO> getChildrenFromLisFacility(String licenseNumber) {
    return clientService.getClientsByLicenseNumber(licenseNumber).stream()
        .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());
  }

}
