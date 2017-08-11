package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.TargetType;

import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {StringUtils.class, LocalDateTime.class, Utils.class})
public interface SubstituteCareProviderMapper {
  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "addTelNo", constant = "0")
  @Mapping(target = "addExtNo", constant = "0")
  @Mapping(target = "birthDt", source = "dateOfBirth")
  @Mapping(target = "caDlicNo", source = "driverLicenseNumber")
  @Mapping(target = "cityNm", ignore = true)
  @Mapping(target = "emplyrNm", source = "employment.employerName")
  @Mapping(target = "firstNm", source = "firstName")
  @Mapping(target = "frgAdrtB", constant = "N")
  @Mapping(target = "genderInd", source = "gender")
  @Mapping(target = "indTrbc", constant = "0")
  @Mapping(target = "lastNm", source = "lastName")
  @Mapping(target = "midIniNm", expression = "java(StringUtils.capitalize(StringUtils.left(applicantDTO.middleName);)")
  @Mapping(target = "nmprfxDsc", source = "namePrefix.value")
  @Mapping(target = "ssNo", ignore = true)
  @Mapping(target = "stateC", source = "")//todo: set id in service
  @Mapping(target = "streetNm", ignore = true)
  @Mapping(target = "streetNo", ignore = true)
  @Mapping(target = "sufxTldsc", source = "nameSuffix.value")
  @Mapping(target = "zipNo", ignore = true)
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId();)")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now();)")
  @Mapping(target = "zipSfxNo", ignore = true)
  @Mapping(target = "education", source = "highestEducationLevel")
  @Mapping(target = "emplStat", constant = "0")
  @Mapping(target = "primInc", constant = "0")
  @Mapping(target = "secInc", constant = "0")
  @Mapping(target = "yrIncAmt", constant = "0")
  @Mapping(target = "hispCd", constant = "U")
  @Mapping(target = "mrtlStc", constant = "0")
  @Mapping(target = "lisownind", constant = "N")
  @Mapping(target = "lisPerId", ignore = true)
  @Mapping(target = "emailAddr", source = "email")
  @Mapping(target = "ethUdCd", ignore = true)
  @Mapping(target = "hispUdCd", ignore = true)
  @Mapping(target = "resostInd", ignore = true)
  @Mapping(target = "passbcCd", ignore = true)
  @Mapping(target = "backgroundChecks", ignore = true)
  @Mapping(target = "outOfStateChecks", ignore = true)
  SubstituteCareProvider toSubstituteCareProvider(ApplicantDTO applicantDTO);

  @Mapping(target = "cityNm", source = "city")
  @Mapping(target = "streetNm", expression = "java(Utils.Address.getStreetName(addressDTO);)")
  @Mapping(target = "streetNo", expression = "java(Utils.Address.getStreetNumber(addressDTO);)")
  @Mapping(target = "zipNo", source = "zipCode")
  @Mapping(target = "zipSfxNo", source = "zipSuffixCode")
  SubstituteCareProvider toSubstituteCareProviderFromResidentialAddress(
      @TargetType SubstituteCareProvider substituteCareProvider,
      AddressDTO addressDTO);

  @Mapping(target = "stateC", source = "state.")
  SubstituteCareProvider toSubstituteCareProviderFromMailingAddress(
      @TargetType SubstituteCareProvider substituteCareProvider,
      AddressDTO addressDTO);

  @Mapping(target = "ssNo", source = "ssn")
  @Mapping(target = "resostInd",
      expression = "java(Utils.BooleanToString.resolve(rfa1bFormDTO.isLivedInOtherState(), 'Y', 'N');)")
  SubstituteCareProvider toSubstituteCareProvider(//todo
      @TargetType SubstituteCareProvider substituteCareProvider,
      RFA1bFormDTO rfa1bFormDTO);
}
