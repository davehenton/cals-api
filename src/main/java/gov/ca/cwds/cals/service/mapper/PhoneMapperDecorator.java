package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;

/**
 * @author CWDS CALS API Team
 */
public abstract class PhoneMapperDecorator implements PhoneMapper {

    private PhoneMapper delegate;

    public PhoneMapperDecorator(PhoneMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public PersonPhoneDTO toPrimaryPhoneDTO(BasePlacementHome placementHome) {
      PersonPhoneDTO personPhoneDTO = null;
        if (placementHome.getPrmTelNo() > 0) {
          personPhoneDTO = delegate.toPrimaryPhoneDTO(placementHome);
        }
      return personPhoneDTO;
    }

    @Override
    public PersonPhoneDTO toAlternatePhoneDTO(BasePlacementHome placementHome) {
      PersonPhoneDTO personPhoneDTO = null;
        if (placementHome.getBckTelNo() > 0) {
          personPhoneDTO = delegate.toAlternatePhoneDTO(placementHome);
        }
      return personPhoneDTO;
    }
}
