package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {LocalDateTime.class, Utils.class, StringUtils.class})
public interface PlacementHomeMapper {
  PlacementHomeMapper INSTANCE = Mappers.getMapper(PlacementHomeMapper.class);

  @Mapping(target = "ageFrmNo", constant = "0")
  @Mapping(target = "ageToNo", constant = "0")
  @Mapping(target = "atCapInd", constant = "N")
  @Mapping(target = "bckPersnm", constant = " ")
  @Mapping(target = "bckExtNo", ignore = true)
  @Mapping(target = "bckTelNo", ignore = true)
  @Mapping(target = "chlcrPlcd", constant = "U")
  @Mapping(target = "cityNm", source = "residentialAddress.city")
  @Mapping(target = "clSrvdc", constant = "0")
  @Mapping(target = "confEfind", constant = "N")
  @Mapping(target = "curOcpNo", constant = "0")
  @Mapping(target = "emrShltcd", constant = "U")
  @Mapping(target = "faxNo", ignore = true)
  @Mapping(target = "frgAdrtB", constant = "N")
  @Mapping(target = "gndrAcpcd", constant = " ")
  @Mapping(target = "geoRgntcd", constant = " ")
  @Mapping(target = "gvrEntc", source = "form.applicationCounty.cwsId")
  @Mapping(target = "inhmVstcd", constant = "U")
  @Mapping(target = "maxCapNo", constant = "0")
  @Mapping(target = "laVndrId", constant = " ")
  @Mapping(target = "licenseNo", constant = " ")
  @Mapping(target = "licCapNo", constant = "0")
  @Mapping(target = "licStc", constant = "0")
  @Mapping(target = "licBsnc", constant = "0")
  @Mapping(target = "licnseeNm", constant = " ")
  @Mapping(target = "licensrCd", constant = "NA")
  @Mapping(target = "facltyNm", ignore = true)
  @Mapping(target = "pCityNm", constant = " ")
  @Mapping(target = "pyeFstnm", constant = " ")
  @Mapping(target = "pyeLstnm", constant = " ")
  @Mapping(target = "pyeMidnm", constant = " ")
  @Mapping(target = "payeeStateCode", ignore = true)
  @Mapping(target = "pstreetNm", constant = " ")
  @Mapping(target = "pstreetNo", constant = " ")
  //@Mapping(target = "pZipNo", constant = "0")
  @Mapping(target = "facilityType", constant = "6914")
  @Mapping(target = "prmCnctnm",
      expression = "java(Utils.Applicant.getFirstLastName(Utils.Applicant.getPrimary(form)))")
  @Mapping(target = "prmExtNo", source = "form.firstApplicant.preferredPhoneNumber.extension")
  @Mapping(target = "prmSubsnm", source = "form.firstApplicant.applicantFullName")
  @Mapping(target = "prmTelNo", source = "form.firstApplicant.preferredPhoneNumber.number")
  @Mapping(target = "pvdTrnscd", constant = "U")
  @Mapping(target = "pubTrnscd", constant = "U")
  @Mapping(target = "stateCode", source = "residentialAddress.state.cwsId")
  @Mapping(target = "streetNm", expression = "java(Utils.Address.getStreetName(residentialAddress))")
  @Mapping(target = "streetNo", expression = "java(Utils.Address.getStreetNumber(residentialAddress))")
  @Mapping(target = "zipNo", source = "residentialAddress.zip")
  @Mapping(target = "addrDsc", source = "form.residence.directionsToHome")
  @Mapping(target = "spcharDsc", constant = " ")
  @Mapping(target = "ctyprfDsc", constant = " ")
  @Mapping(target = "edPvrDsc", constant = " ")
  @Mapping(target = "envFctdsc", constant = " ")
  @Mapping(target = "hazrdsDsc", expression = "java(Utils.PlacementHome.getHazardsDescription(form.getResidence()))")
  @Mapping(target = "lisPrfdsc", constant = " ")
  @Mapping(target = "petsDsc", constant = " ")
  @Mapping(target = "rlgActdsc", constant = " ")
  //@Mapping(target = "pyZipSfx", constant = "0")
  //@Mapping(target = "zipSfxNo", constant = "0")
  @Mapping(target = "apStatTp", constant = "0")
  @Mapping(target = "certCmplt", constant = " ")
  @Mapping(target = "laPCtynm", constant = " ")
  @Mapping(target = "laPFstnm", constant = " ")
  @Mapping(target = "laPLstnm", constant = " ")
  @Mapping(target = "laPMidnm", constant = " ")
  @Mapping(target = "laPayeeState", constant = "0")
  @Mapping(target = "laPStnm", constant = " ")
  @Mapping(target = "laPStno", constant = " ")
  //@Mapping(target = "laPZipno", constant = "0")
  //@Mapping(target = "laPZpsfx", constant = "0")
  @Mapping(target = "laPBsnss", constant = " ")
  @Mapping(target = "laPPhNo", ignore = true)
  @Mapping(target = "laPPhEx", ignore = true)
  @Mapping(target = "adhmonly", constant = " ")
  @Mapping(target = "pyeExtNo", ignore = true)
  @Mapping(target = "pyeTelNo", ignore = true)
  @Mapping(target = "arcassInd", constant = "N")
  @Mapping(target = "comfacInd", constant = "N")
  @Mapping(target = "trnhsgInd", constant = "N")
  @Mapping(target = "trnhsgFac", constant = "N")
  @Mapping(target = "newlicNo", ignore = true)
  @Mapping(target = "newlicUpd", constant = "N")
  @Mapping(target = "oldfacId", ignore = true)
  @Mapping(target = "emCntB", constant = "N")
  @Mapping(target = "endDt", ignore = true)
  @Mapping(target = "phEndc", ignore = true)
  @Mapping(target = "endComdsc", ignore = true)
  PlacementHome toPlacementHome(RFA1aFormDTO form, RFAAddressDTO residentialAddress);

  @AfterMapping
  default void setFacilityName(@MappingTarget PlacementHome placementHome, RFA1aFormDTO form) {
    StringBuilder sb = new StringBuilder();
    sb.append(Utils.Applicant.getPrimary(form).getLastName());
    sb.append(", ");
    List<String> firstNames = form.getApplicants().stream().map(ApplicantDTO::getFirstName).collect(
        Collectors.toList());
    sb.append(StringUtils.join(firstNames, " & "));
    sb.append(" RFH");
    placementHome.setFacltyNm(sb.toString());
  }

}
