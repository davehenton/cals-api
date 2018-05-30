package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.UnitOfWork;
import gov.ca.cwds.cals.service.dao.FacilityChildDao;
import gov.ca.cwds.cals.service.dto.ChildPlacementInformation;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDto;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.cms.data.access.service.impl.ClientCoreService;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {

  private static final Logger LOG = LoggerFactory.getLogger(FacilityChildCollectionService.class);

  @Inject
  private ClientCoreService clientService;

  @Inject
  private ChildAssignedWorkerService childAssignedWorkerService;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Inject
  private FacilityChildDao facilityChildDao;

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    FacilityChildrenDto facilityChildrenDto = new FacilityChildrenDto();
    if (parameterObject.getUnitOfWork().equals(UnitOfWork.CMS)) {
      facilityChildrenDto
          .getChildren()
          .addAll(getChildrenFromCwsFacility(parameterObject.getFacilityId()));
    } else {
      facilityChildrenDto
          .getChildren()
          .addAll(getChildrenFromLisFacility(parameterObject.getFacilityId()));
    }
    return facilityChildrenDto.getChildren().isEmpty() ? null : facilityChildrenDto;
  }

  private List<FacilityChildDTO> getChildrenFromCwsFacility(String facilityId) {
    return getChildrenFromFacilityStream(clientService.streamByFacilityId(facilityId));
  }

  private List<FacilityChildDTO> getChildrenFromLisFacility(String licenseNumber) {
    return getChildrenFromFacilityStream(clientService.getClientsByLicenseNumber(licenseNumber));
  }

  private Map<String, ChildPlacementInformation> buildChildPlacementInformation(
      List<Client> clients) {
    String[] clientIds = clients.stream().map(Client::getIdentifier).toArray(String[]::new);
    List<ChildPlacementInformation> childrenPlacementInfo =
        facilityChildDao.retrieveChildPlacementInformationList(clientIds);
    return childrenPlacementInfo
        .stream()
        .collect(Collectors.toMap(ChildPlacementInformation::getChildIdentifier, x -> x,
            (placementInformtion1, placementInformation2) -> {
              LOG.warn("Child with identifier {} has more than one open placement",
                  placementInformtion1.getChildIdentifier());
              return placementInformation2.getDateOfPlacement()
                  .isAfter(placementInformtion1.getDateOfPlacement()) ? placementInformation2
                  : placementInformtion1;
            }));
  }

  private FacilityChildDTO enrichClientWithPlacementInformation(
      FacilityChildDTO facilityChildDto,
      Map<String, ChildPlacementInformation> placementInformationMap) {
    ChildPlacementInformation childPlacementInformation =
        placementInformationMap.get(facilityChildDto.getId());
    if (childPlacementInformation != null) {
      facilityChildDto.setCountyOfOrigin(childPlacementInformation.getCounty());
      facilityChildDto.setDateOfPlacement(childPlacementInformation.getDateOfPlacement());
    }
    return facilityChildDto;
  }

  private List<FacilityChildDTO> getChildrenFromFacilityStream(List<Client> clients) {
    if (clients.isEmpty()) {
      return Collections.emptyList();
    }
    Map<String, ChildPlacementInformation> placementInformationMap =
        buildChildPlacementInformation(clients);
    return clients
        .stream()
        .map(facilityChildMapper::toFacilityChildDTO)
        .map(
            facilityChildDto ->
                enrichClientWithPlacementInformation(facilityChildDto, placementInformationMap))
        .map(
            facilityChildDto ->
                facilityChildMapper.toFacilityChildDTO(
                    facilityChildDto,
                    childAssignedWorkerService
                        .findAssignedWorkerForClient(facilityChildDto.getId())
                        .orElse(null)))
        .collect(Collectors.toList());
  }
}
