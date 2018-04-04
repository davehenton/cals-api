package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.data.legacy.cms.entity.Client;

/**
 * Decorator for FacilityChild mapper class.
 *
 * @author CWDS CALS API Team
 */
public abstract class FacilityChildMapperDecorator implements FacilityChildMapper {

  private FacilityChildMapper delegate;

  public FacilityChildMapperDecorator(FacilityChildMapper delegate) {
    this.delegate = delegate;
  }

  @Override
  public FacilityChildDTO toFacilityChildDTO(Client client) {
    FacilityChildDTO facilityChildDTO = null;
    if (null != client) {
      facilityChildDTO = delegate.toFacilityChildDTO(client);
    }
    return facilityChildDTO;
  }
}
