package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.ClientDao;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {

  @Inject
  private ClientDao clientDao;

  @Inject
  private FacilityChildMapper facilityChildMapper;

  @Inject
  private FacilityLicenseNumberProvider licenseNumberProvider;

  public FacilityChildCollectionService() {
    //default constructor
  }

  @Override
  public Response find(Serializable params) {
    FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
    List<FacilityChildDTO> facilityChildDTOs = new ArrayList<>();
    String licenseNumber = licenseNumberProvider.get(parameterObject);
    if (licenseNumber != null) {
      facilityChildDTOs = clientDao
          .streamByLicenseNumber(licenseNumber)
          .map(facilityChildMapper::toFacilityChildDTO).collect(Collectors.toList());
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
