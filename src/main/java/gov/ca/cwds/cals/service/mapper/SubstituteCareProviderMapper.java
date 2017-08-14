package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformation;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProvider;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProviderUc;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {StringUtils.class, LocalDate.class, LocalDateTime.class, Utils.class})
public interface SubstituteCareProviderMapper {

  @Mapping(target = "identifier", expression = "java(Utils.Id.generate())")
  @Mapping(target = "addTelNo", constant = "0")
  @Mapping(target = "addExtNo", constant = "0")
  @Mapping(target = "birthDt", source = "dateOfBirth")
//  @Mapping(target = "caDlicNo", source = "driverLicenseNumber")//todo 8 chars but have 13 (MD123G3333232)
  @Mapping(target = "caDlicNo", constant = " ")//todo 8 chars but have 13 (MD123G3333232)
  @Mapping(target = "cityNm", ignore = true)
  @Mapping(target = "emplyrNm", source = "employment.employerName")
  @Mapping(target = "firstNm", source = "firstName")
  @Mapping(target = "frgAdrtB", constant = "N")
  @Mapping(target = "genderInd", source = "gender.cwsShortCode")
  @Mapping(target = "indTrbc", constant = "0")
  @Mapping(target = "lastNm", source = "lastName")
  @Mapping(target = "midIniNm",
      expression = "java(StringUtils.capitalize(StringUtils.left(applicantDTO.getMiddleName(), 1)))")
  @Mapping(target = "nmprfxDsc", source = "namePrefix.value")
  @Mapping(target = "ssNo", constant = " ")
  @Mapping(target = "stateC", ignore = true)
  @Mapping(target = "streetNm", ignore = true)
  @Mapping(target = "streetNo", ignore = true)
  @Mapping(target = "sufxTldsc", source = "nameSuffix.value")
  @Mapping(target = "zipNo", ignore = true)
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "zipSfxNo", ignore = true)
  @Mapping(target = "education", source = "highestEducationLevel.cwsId")
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

  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "addTelNo", ignore = true)
  @Mapping(target = "addExtNo", ignore = true)
  @Mapping(target = "birthDt", ignore = true)
  @Mapping(target = "caDlicNo", ignore = true)
  @Mapping(target = "emplyrNm", ignore = true)
  @Mapping(target = "firstNm", ignore = true)
  @Mapping(target = "frgAdrtB", ignore = true)
  @Mapping(target = "genderInd", ignore = true)
  @Mapping(target = "indTrbc", ignore = true)
  @Mapping(target = "lastNm", ignore = true)
  @Mapping(target = "midIniNm", ignore = true)
  @Mapping(target = "nmprfxDsc", ignore = true)
  @Mapping(target = "ssNo", ignore = true)
  @Mapping(target = "stateC", ignore = true)
  @Mapping(target = "sufxTldsc", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "zipSfxNo", ignore = true)
  @Mapping(target = "education", ignore = true)
  @Mapping(target = "emplStat", ignore = true)
  @Mapping(target = "primInc", ignore = true)
  @Mapping(target = "secInc", ignore = true)
  @Mapping(target = "yrIncAmt", ignore = true)
  @Mapping(target = "hispCd", ignore = true)
  @Mapping(target = "mrtlStc", ignore = true)
  @Mapping(target = "lisownind", ignore = true)
  @Mapping(target = "lisPerId", ignore = true)
  @Mapping(target = "emailAddr", ignore = true)
  @Mapping(target = "ethUdCd", ignore = true)
  @Mapping(target = "hispUdCd", ignore = true)
  @Mapping(target = "resostInd", ignore = true)
  @Mapping(target = "passbcCd", ignore = true)
  @Mapping(target = "backgroundChecks", ignore = true)
  @Mapping(target = "outOfStateChecks", ignore = true)
  @Mapping(target = "cityNm", source = "addressDTO.city")
  @Mapping(target = "streetNm", expression = "java(Utils.Address.getStreetName(addressDTO))")
  @Mapping(target = "streetNo", expression = "java(Utils.Address.getStreetNumber(addressDTO))")
  @Mapping(target = "zipNo", source = "zip")
  SubstituteCareProvider toSubstituteCareProviderFromResidentialAddress(
      @MappingTarget SubstituteCareProvider substituteCareProvider,
      RFAAddressDTO addressDTO);

  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "addTelNo", ignore = true)
  @Mapping(target = "addExtNo", ignore = true)
  @Mapping(target = "birthDt", ignore = true)
  @Mapping(target = "caDlicNo", ignore = true)
  @Mapping(target = "cityNm", ignore = true)
  @Mapping(target = "emplyrNm", ignore = true)
  @Mapping(target = "firstNm", ignore = true)
  @Mapping(target = "frgAdrtB", ignore = true)
  @Mapping(target = "genderInd", ignore = true)
  @Mapping(target = "indTrbc", ignore = true)
  @Mapping(target = "lastNm", ignore = true)
  @Mapping(target = "midIniNm", ignore = true)
  @Mapping(target = "nmprfxDsc", ignore = true)
  @Mapping(target = "ssNo", ignore = true)
  @Mapping(target = "streetNm", ignore = true)
  @Mapping(target = "streetNo", ignore = true)
  @Mapping(target = "sufxTldsc", ignore = true)
  @Mapping(target = "zipNo", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "zipSfxNo", ignore = true)
  @Mapping(target = "education", ignore = true)
  @Mapping(target = "emplStat", ignore = true)
  @Mapping(target = "primInc", ignore = true)
  @Mapping(target = "secInc", ignore = true)
  @Mapping(target = "yrIncAmt", ignore = true)
  @Mapping(target = "hispCd", ignore = true)
  @Mapping(target = "mrtlStc", ignore = true)
  @Mapping(target = "lisownind", ignore = true)
  @Mapping(target = "lisPerId", ignore = true)
  @Mapping(target = "emailAddr", ignore = true)
  @Mapping(target = "ethUdCd", ignore = true)
  @Mapping(target = "hispUdCd", ignore = true)
  @Mapping(target = "resostInd", ignore = true)
  @Mapping(target = "passbcCd", ignore = true)
  @Mapping(target = "backgroundChecks", ignore = true)
  @Mapping(target = "outOfStateChecks", ignore = true)
  @Mapping(target = "stateC", source = "addressDTO.state.cwsId")
  SubstituteCareProvider toSubstituteCareProviderFromMailingAddress(
      @MappingTarget SubstituteCareProvider substituteCareProvider,
      RFAAddressDTO addressDTO);

  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "addTelNo", ignore = true)
  @Mapping(target = "addExtNo", ignore = true)
  @Mapping(target = "birthDt", ignore = true)
  @Mapping(target = "caDlicNo", ignore = true)
  @Mapping(target = "cityNm", ignore = true)
  @Mapping(target = "emplyrNm", ignore = true)
  @Mapping(target = "firstNm", ignore = true)
  @Mapping(target = "frgAdrtB", ignore = true)
  @Mapping(target = "genderInd", ignore = true)
  @Mapping(target = "indTrbc", ignore = true)
  @Mapping(target = "lastNm", ignore = true)
  @Mapping(target = "midIniNm", ignore = true)
  @Mapping(target = "nmprfxDsc", ignore = true)
  @Mapping(target = "stateC", ignore = true)
  @Mapping(target = "streetNm", ignore = true)
  @Mapping(target = "streetNo", ignore = true)
  @Mapping(target = "sufxTldsc", ignore = true)
  @Mapping(target = "zipNo", ignore = true)
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "zipSfxNo", ignore = true)
  @Mapping(target = "education", ignore = true)
  @Mapping(target = "emplStat", ignore = true)
  @Mapping(target = "primInc", ignore = true)
  @Mapping(target = "secInc", ignore = true)
  @Mapping(target = "yrIncAmt", ignore = true)
  @Mapping(target = "hispCd", ignore = true)
  @Mapping(target = "mrtlStc", ignore = true)
  @Mapping(target = "lisownind", ignore = true)
  @Mapping(target = "lisPerId", ignore = true)
  @Mapping(target = "emailAddr", ignore = true)
  @Mapping(target = "ethUdCd", ignore = true)
  @Mapping(target = "hispUdCd", ignore = true)
  @Mapping(target = "passbcCd", ignore = true)
  @Mapping(target = "backgroundChecks", ignore = true)
  @Mapping(target = "outOfStateChecks", ignore = true)
  @Mapping(target = "ssNo", expression = "java(StringUtils.defaultString(rfa1bFormDTO.getSsn(), \" \"))")
  @Mapping(target = "resostInd",
      expression = "java(Utils.BooleanToString.resolve(rfa1bFormDTO.isLivedInOtherState(), \"Y\", \"N\"))")
  SubstituteCareProvider toSubstituteCareProvider(
      @MappingTarget SubstituteCareProvider substituteCareProvider,
      RFA1bFormDTO rfa1bFormDTO);

  @Mapping(target = "thirdId", expression = "java(Utils.Id.generate())")
  @Mapping(target = "startDt", expression = "java(LocalDate.now())")
  @Mapping(target = "endDt", ignore = true)
  @Mapping(target = "licnseeCd", constant = "U")
  @Mapping(target = "crprvdrCd", constant = "Y")
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "fksbPvdrt", source = "substituteCareProvider.identifier")
  @Mapping(target = "fkplcHmT", source = "placementHome.identifier")
  @Mapping(target = "prprvdrCd", source = "prprvdrCd")
  @Mapping(target = "cdsPrsn", constant = " ")
  @Mapping(target = "scprvdInd", source = "scprvdInd")
  PlacementHomeInformation toPlacementHomeInformation(
      PlacementHome placementHome, SubstituteCareProvider substituteCareProvider, String prprvdrCd,
      String scprvdInd);

  @Mapping(target = "pksbPvdrt", source = "identifier")
//  @Mapping(target = "caDlicNo", expression = "java(StringUtils.upperCase(applicantDTO.getDriverLicenseNumber()))") //todo 8 chars but have 13 (MD123G3333232)
  @Mapping(target = "caDlicNo", constant = " ") //todo 8 chars but have 13 (MD123G3333232)
  @Mapping(target = "firstNm", expression = "java(StringUtils.upperCase(applicantDTO.getFirstName()))")
  @Mapping(target = "lastNm", expression = "java(StringUtils.upperCase(applicantDTO.getLastName()))")
  @Mapping(target = "lstUpdId", expression = "java(Utils.Id.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  SubstituteCareProviderUc toSubstituteCareProviderUC(String identifier, ApplicantDTO applicantDTO);
}
