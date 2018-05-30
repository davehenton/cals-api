package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.UnitOfWork;
import gov.ca.cwds.cals.service.dao.FacilityChildDao;
import gov.ca.cwds.cals.service.dto.ChildPlacementInformation;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.cms.data.access.service.impl.ClientCoreService;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CrudServiceAdapter {

  private ClientCoreService clientService;

  private FacilityChildMapper facilityChildMapper;

  @Inject
  private FacilityChildDao facilityChildDao;

  @Inject
  public FacilityChildService(ClientCoreService clientService,
      FacilityChildMapper facilityChildMapper) {
    this.clientService = clientService;
    this.facilityChildMapper = facilityChildMapper;
  }

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    Client client = null;
    if (parameterObject.getUnitOfWork().equals(UnitOfWork.CMS)) {
      client = clientService.getClientByFacilityIdAndChildId(parameterObject.getFacilityId(),
          parameterObject.getChildId());
    } else {
      client = clientService.getClientByLicNumAndChildId(parameterObject.getFacilityId(),
          parameterObject.getChildId());
    }
    FacilityChildDTO facilityChildDTO = facilityChildMapper.toFacilityChildDTO(client);
    if (facilityChildDTO != null) {
      enrichWithPlacementInformation(facilityChildDTO);
    }
    return facilityChildDTO;
  }

  private void enrichWithPlacementInformation(FacilityChildDTO facilityChildDto) {
    ChildPlacementInformation childPlacementInformation =
        facilityChildDao.retrieveChildPlacementInformation(facilityChildDto.getId());
    if (childPlacementInformation != null) {
      facilityChildDto.setCountyOfOrigin(childPlacementInformation.getCounty());
      facilityChildDto.setDateOfPlacement(childPlacementInformation.getDateOfPlacement());
    }
  }
}
