package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.util.RfaAddressUtil;
import gov.ca.cwds.cals.util.Utils;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.security.utils.PrincipalUtils;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, Utils.class, RfaAddressUtil.class, StringUtils.class,
    Constants.class,
    PrincipalUtils.class})
public interface SubstituteCareProviderMapper {

  SubstituteCareProviderMapper INSTANCE = Mappers.getMapper(SubstituteCareProviderMapper.class);

  @Mapping(target = "addTelNo", constant = "0")
  @Mapping(target = "addExtNo", constant = "0")
  @Mapping(target = "birthDt", source = "dateOfBirth")
  @Mapping(target = "caDlicNo", expression = "java(Utils.Applicant.getCaliforniaDriverLicense(" +
      "applicantDTO, Constants.SPACE))")
  @Mapping(target = "emplyrNm",
      expression = "java(applicantDTO.getEmployment() != null "
          + " && applicantDTO.getEmployment().getEmployerName() != null "
          + " ? applicantDTO.getEmployment().getEmployerName() : Constants.SPACE)")
  @Mapping(target = "firstNm", source = "firstName")
  @Mapping(target = "frgAdrtB", constant = "N")
  @Mapping(target = "genderInd", source = "gender.cwsShortCode")
  @Mapping(target = "indTrbc", constant = "0")
  @Mapping(target = "lastNm", source = "lastName")
  @Mapping(target = "midIniNm",
      expression = "java(StringUtils.capitalize(StringUtils.left(applicantDTO.getMiddleName(), 1)))")
  @Mapping(target = "nmprfxDsc",
      expression = "java(applicantDTO.getNamePrefix() != null ? " +
          "applicantDTO.getNamePrefix().getValue() : Constants.SPACE)")
  @Mapping(target = "ssNo", constant = " ")
  @Mapping(target = "sufxTldsc",
      expression = "java(applicantDTO.getNameSuffix() != null ? " +
          "applicantDTO.getNameSuffix().getValue() : Constants.SPACE)")
  @Mapping(target = "lstUpdId", expression = "java(PrincipalUtils.getStaffPersonId())")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "education", source = "highestEducationLevel.cwsId")
  @Mapping(target = "emplStat", constant = "0")
  @Mapping(target = "primInc", constant = "0")
  @Mapping(target = "secInc", constant = "0")
  @Mapping(target = "yrIncAmt", expression = "java(Utils.Applicant.getAnnualIncome(applicantDTO))")
  @Mapping(target = "hispCd", constant = "U")
  @Mapping(target = "mrtlStc", constant = "0")
  @Mapping(target = "lisownind", constant = "N")
  @Mapping(target = "emailAddr", source = "email")
  SubstituteCareProvider toSubstituteCareProvider(ApplicantDTO applicantDTO);

  @Mapping(target = "cityNm", source = "addressDTO.city")
  @Mapping(target = "streetNm", expression = "java(RfaAddressUtil.getStreetName(addressDTO))")
  @Mapping(target = "streetNo", expression = "java(RfaAddressUtil.getStreetNumber(addressDTO))")
  @Mapping(target = "zipNo", source = "zip")
  SubstituteCareProvider toSubstituteCareProviderFromResidentialAddress(
      @MappingTarget SubstituteCareProvider substituteCareProvider,
      RFAAddressDTO addressDTO);

  @Mapping(target = "stateC", source = "addressDTO.state.cwsId")
  SubstituteCareProvider toSubstituteCareProviderFromMailingAddress(
      @MappingTarget SubstituteCareProvider substituteCareProvider,
      RFAAddressDTO addressDTO);

  @Mapping(target = "ssNo",
      expression = "java(StringUtils.defaultString(rfa1bFormDTO.getSsn(), Constants.SPACE))")
  @Mapping(target = "resostInd", expression = "java(Utils.BooleanToString.resolve(" +
      "rfa1bFormDTO.isLivedInOtherState(), Constants.Y, Constants.N))")
  SubstituteCareProvider toSubstituteCareProvider(
      @MappingTarget SubstituteCareProvider substituteCareProvider,
      RFA1bFormDTO rfa1bFormDTO);
}
