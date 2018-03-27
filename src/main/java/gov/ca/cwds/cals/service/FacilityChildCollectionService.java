package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.cms.data.access.service.impl.ClientCoreService;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {

  @Inject
  private ClientCoreService clientService;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  public FacilityChildCollectionService() {
  }

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    List<Client> clients = clientService
        .getClientsByLicenseNumber(parameterObject.getLicenseNumber());
    List<FacilityChildDTO> facilityChildDTOs = new ArrayList<>(clients.size());
    for (Client client : clients) {
      facilityChildDTOs.add(facilityChildMapper.toFacilityChildDTO(client));
    }

    if (CollectionUtils.isEmpty(facilityChildDTOs)) {
      return null;
    } else {
      FacilityChildrenDTO facilityChildrenDTO = new FacilityChildrenDTO();
      facilityChildrenDTO.setChildren(facilityChildDTOs);
      return facilityChildrenDTO;
    }
  }

}
