package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.UnitOfWork;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDto;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.cms.data.access.service.impl.ClientCoreService;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {

  @Inject
  private ClientCoreService clientService;

  @Inject
  private ChildAssignedWorkerService childAssignedWorkerService;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    FacilityChildrenDto facilityChildrenDto = new FacilityChildrenDto();
    if (parameterObject.getUnitOfWork().equals(UnitOfWork.CMS)) {
      facilityChildrenDto.getChildren()
          .addAll(getChildrenFromCwsFacility(parameterObject.getFacilityId()));
    } else {
      facilityChildrenDto.getChildren()
          .addAll(getChildrenFromLisFacility(parameterObject.getFacilityId()));
    }
    return facilityChildrenDto.getChildren().isEmpty() ? null : facilityChildrenDto;
  }

  private List<FacilityChildDTO> getChildrenFromCwsFacility(String facilityId) {
    return getChildrenFromFacilityStream(clientService.streamByFacilityId(facilityId).stream());

  }

  private List<FacilityChildDTO> getChildrenFromLisFacility(String licenseNumber) {
    return getChildrenFromFacilityStream(
        clientService.getClientsByLicenseNumber(licenseNumber).stream());

  }

  private List<FacilityChildDTO> getChildrenFromFacilityStream(Stream<Client> stream) {
    return stream.map(facilityChildMapper::toFacilityChildDTO)
        .map(facilityChildDTO -> facilityChildMapper
            .toFacilityChildDTO(facilityChildDTO,
                childAssignedWorkerService.findAssignedWorkerForClient(facilityChildDTO.getId())
                    .orElse(null))).collect(Collectors.toList());
  }
}
