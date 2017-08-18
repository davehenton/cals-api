package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.PhoneContactDetail;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper
@SuppressWarnings("squid:S1609")
public interface PhoneContactDetailMapper {

  @Mapping(target = "phoneNo", source = "phoneDTO.number")
  @Mapping(target = "phextNo", source = "phoneDTO.extension")
  @Mapping(target = "phnTypCd", source = "phoneDTO.phoneType.cwsShortCode")
  @Mapping(target = "estblshCd", constant = "S")
  @Mapping(target = "estblshId", source = "substituteCareProviderId")
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  PhoneContactDetail toPhoneContactDetail(PhoneDTO phoneDTO,
      String substituteCareProviderId);

}
