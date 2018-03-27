package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.ClientDao;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildService extends CrudServiceAdapter {

  private ClientDao clientDao;

  private FacilityChildMapper facilityChildMapper;

  @Inject
  private FacilityLicenseNumberProvider licenseNumberProvider;


  @Inject
  public FacilityChildService(ClientDao clientDao, FacilityChildMapper facilityChildMapper) {
    this.clientDao = clientDao;
    this.facilityChildMapper = facilityChildMapper;
  }

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    String licenseNumber = licenseNumberProvider.get(parameterObject);
    if (licenseNumber != null) {
      Client client = clientDao
          .findByLicNumAndChildId(licenseNumber, parameterObject.getChildId());
      return facilityChildMapper.toFacilityChildDTO(client);
    }
    return null;
  }
}
