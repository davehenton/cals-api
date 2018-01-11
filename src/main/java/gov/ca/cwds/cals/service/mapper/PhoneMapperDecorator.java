package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CWDS CALS API Team
 */
public abstract class PhoneMapperDecorator implements PhoneMapper {

  private PhoneMapper delegate;

  public PhoneMapperDecorator(PhoneMapper delegate) {
    this.delegate = delegate;
  }

  @Override
  public PersonPhoneDTO lisFacilityToPhoneDTO(LisFacFile lisFacFile) {
    PersonPhoneDTO personPhoneDTO = null;
    if (StringUtils.isNotBlank(lisFacFile.getFacPhoneNbr()) && !lisFacFile.getFacPhoneNbr()
        .equalsIgnoreCase("null")) {
      personPhoneDTO = delegate.lisFacilityToPhoneDTO(lisFacFile);
    }
    return personPhoneDTO;
  }

  @Override
  public PersonPhoneDTO toPrimaryPhoneDTO(BasePlacementHome placementHome) {
    PersonPhoneDTO personPhoneDTO = null;
    if (StringUtils.isNotBlank(placementHome.getPrmTelNo()) && !placementHome.getPrmTelNo()
        .equalsIgnoreCase("null")) {
      personPhoneDTO = delegate.toPrimaryPhoneDTO(placementHome);
    }
    return personPhoneDTO;
  }

  @Override
  public PersonPhoneDTO toAlternatePhoneDTO(BasePlacementHome placementHome) {
    PersonPhoneDTO personPhoneDTO = null;
    if (StringUtils.isNotBlank(placementHome.getBckTelNo()) && !placementHome.getBckTelNo()
        .equalsIgnoreCase("null")) {
      personPhoneDTO = delegate.toAlternatePhoneDTO(placementHome);
    }
    return personPhoneDTO;
  }
}
