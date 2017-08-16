package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NamePrefixType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.NameSuffixType;
import gov.ca.cwds.cals.persistence.model.cms.OtherAdultsInPlacementHome;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = LocalDate.class)
@SuppressWarnings("squid:S1609")
public interface OtherAdultsInPlacementHomeMapper {

  @Mapping(target = "birthDt", source = "dateOfBirth")
  @Mapping(target = "endDt", ignore = true)
  @Mapping(target = "genderCd", constant = "M")
  @Mapping(target = "othAdltnm", ignore = true)
  @Mapping(target = "startDt", expression = "java(LocalDate.now())")
  @Mapping(target = "othAdlCd", constant = "O")
  @Mapping(target = "resostInd", ignore = true)
  @Mapping(target = "passbcCd", constant = "U")
  @Mapping(target = "comntDsc", constant = "")
  OtherAdultsInPlacementHome toOtherAdult(OtherAdultDTO otherAdultDTO);

  @AfterMapping
  default void afterMapping(@MappingTarget OtherAdultsInPlacementHome target,
      OtherAdultDTO otherAdultDTO) {
    StringBuilder nameBuilder = new StringBuilder();

    NamePrefixType namePrefix = otherAdultDTO.getNamePrefix();
    if (namePrefix != null) {
      nameBuilder.append(namePrefix.getValue());
    }

    String firstName = otherAdultDTO.getFirstName();
    if (StringUtils.isNoneEmpty(firstName)) {
      nameBuilder.append(" ").append(firstName);
    }

    String middleName = otherAdultDTO.getMiddleName();
    if (StringUtils.isNoneEmpty(middleName)) {
      nameBuilder.append(" ").append(middleName);
    }

    String lastName = otherAdultDTO.getLastName();
    if (StringUtils.isNoneEmpty(lastName)) {
      nameBuilder.append(" ").append(lastName);
    }

    NameSuffixType nameSuffix = otherAdultDTO.getNameSuffix();
    if (nameSuffix != null) {
      nameBuilder.append(" ").append(nameSuffix.getValue());
    }

    target.setOthAdltnm(nameBuilder.toString());
  }

  default OtherAdultsInPlacementHome residedOutOfStateIndMapping(OtherAdultsInPlacementHome target,
      OtherAdultDTO otherAdultDTO) {
    /* 
       TODO: implement residedOutOfStateIndMapping
       (if rfa1b_forms.lived_in_other_state == true then Y. if rfa1b_forms.lived_in_other_state == false then N. if rfa1b_forms.lived_in_other_state == null then null)
       need to define link between OtherAdult and RFA1b form
     */

    return target;
  }


}
