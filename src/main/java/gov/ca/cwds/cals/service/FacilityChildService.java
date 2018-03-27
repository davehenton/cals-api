package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
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
  public FacilityChildService(ClientCoreService clientService,
      FacilityChildMapper facilityChildMapper) {
    this.clientService = clientService;
    this.facilityChildMapper = facilityChildMapper;
  }

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    Client client = clientService
        .getClientByLicNumAndChildId(parameterObject.getLicenseNumber(),
            parameterObject.getChildId());
    return facilityChildMapper.toFacilityChildDTO(client);
  }
}
