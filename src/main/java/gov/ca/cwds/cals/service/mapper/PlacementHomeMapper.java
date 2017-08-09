package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeUc;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = LocalDateTime.class)
public interface PlacementHomeMapper {

  @Mapping(target = "identifier", source = "form.placementHomeId")
  @Mapping(target = "ageFrmNo", constant = "0")
  @Mapping(target = "ageToNo", constant = "0")
  @Mapping(target = "atCapInd", constant = "N")
  @Mapping(target = "bckPersnm", constant = " ")
  @Mapping(target = "bckExtNo", constant = "0")
  @Mapping(target = "bckTelNo", constant = "0")
  @Mapping(target = "chlcrPlcd", constant = "U")
  @Mapping(target = "cityNm", source = "form.residence.residentialAddress.city")
  @Mapping(target = "clSrvdc", constant = "0")
  @Mapping(target = "confEfind", constant = "N")
  @Mapping(target = "curOcpNo", constant = "0")
  @Mapping(target = "emrShltcd", constant = "U")
  @Mapping(target = "faxNo", constant = "0")
  @Mapping(target = "frgAdrtB", constant = "N")
  @Mapping(target = "gndrAcpcd", constant = " ")
  @Mapping(target = "geoRgntcd", constant = " ")
  @Mapping(target = "gvrEntc", source = "applicationCounty.cwsId")
  @Mapping(target = "inhmVstcd", constant = "U")
  @Mapping(target = "maxCapNo", constant = "0")
  @Mapping(target = "laVndrId", constant = " ")
  @Mapping(target = "licenseNo", constant = "NULL")
  @Mapping(target = "licCapNo", constant = "0")
  @Mapping(target = "licenseStatus", ignore = true) //TODO 2 LIC_STC, LicenseStatus
  @Mapping(target = "licBsnc", constant = "0")
  @Mapping(target = "licnseeNm", constant = " ")
  @Mapping(target = "licensrCd", constant = "NA")
  @Mapping(target = "facltyNm", ignore = true)
  @Mapping(target = "pCityNm", constant = " ")
  @Mapping(target = "pyeFstnm", constant = " ")
  @Mapping(target = "pyeLstnm", constant = " ")
  @Mapping(target = "pyeMidnm", constant = " ")
  @Mapping(target = "payeeStateCode", ignore = true)
  //TODO 4 P_STATE_C placementHome.setStateCode ????
  @Mapping(target = "pstreetNm", constant = " ")
  @Mapping(target = "pstreetNo", constant = " ")
  @Mapping(target = "pZipNo", constant = "0")
  @Mapping(target = "facilityType", ignore = true) //TODO 5 PLC_FCLC - facility type
  @Mapping(target = "prmCnctnm", expression = "java(form.getFirstApplicant().getFirstName() + \" \" + form.getFirstApplicant().getLastName())")
  @Mapping(target = "prmExtNo", source = "form.firstApplicant.preferredPhoneNumber.extension")
  @Mapping(target = "prmSubsnm", source = "form.firstApplicant.applicantFullName")
  @Mapping(target = "prmTelNo", source = "form.firstApplicant.preferredPhoneNumber.number")
  @Mapping(target = "pvdTrnscd", constant = "U")
  @Mapping(target = "pubTrnscd", constant = "U")
  @Mapping(target = "stateCode", ignore = true)
  //TODO 10 -- F_STATE_C residence.addresses[x].type = Residential then residence.addresses[x].state
  @Mapping(target = "streetNm", source = "form.residence.residentialStreet")
  @Mapping(target = "streetNo", source = "form.residence.residentialStreetNumber")
  @Mapping(target = "zipNo", source = "form.residence.residentialAddress.zip")
  @Mapping(target = "lstUpdId", constant = "SYS")
  @Mapping(target = "lstUpdTs", expression = "java(LocalDateTime.now())")
  @Mapping(target = "addrDsc", source = "form.residence.directionsToHome")
  @Mapping(target = "spcharDsc", constant = " ") //
  @Mapping(target = "ctyprfDsc", constant = " ")
  @Mapping(target = "edPvrDsc", constant = " ")
  @Mapping(target = "envFctdsc", constant = " ")
  @Mapping(target = "hazrdsDsc", constant = " ")
  @Mapping(target = "lisPrfdsc", constant = " ")
  @Mapping(target = "petsDsc", constant = " ")
  @Mapping(target = "rlgActdsc", constant = " ")
  @Mapping(target = "pyZipSfx", constant = "0")
  @Mapping(target = "zipSfxNo", constant = "0")
  @Mapping(target = "apStatTp", constant = "0")
  @Mapping(target = "certCmplt", constant = " ")
  @Mapping(target = "laPCtynm", constant = " ")
  @Mapping(target = "laPFstnm", constant = " ")
  @Mapping(target = "laPLstnm", constant = " ")
  @Mapping(target = "laPMidnm", constant = " ")
  @Mapping(target = "laPayeeState", ignore = true) //TODO 15 LP_STATE_C
  @Mapping(target = "laPStnm", constant = " ")
  @Mapping(target = "laPStno", constant = " ")
  @Mapping(target = "laPZipno", constant = "0")
  @Mapping(target = "laPZpsfx", constant = "0")
  @Mapping(target = "laPBsnss", constant = " ")
  @Mapping(target = "laPPhNo", constant = "0")
  @Mapping(target = "laPPhEx", constant = "0")
  @Mapping(target = "adhmonly", constant = " ")
  @Mapping(target = "pyeExtNo", constant = "0")
  @Mapping(target = "pyeTelNo", constant = "0")
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
  PlacementHome toPlacementHome(RFA1aFormDTO form, CountyType applicationCounty);

  @AfterMapping
  default void setFacilityName(@MappingTarget PlacementHome placementHome, RFA1aFormDTO form) {
    StringBuilder sb = new StringBuilder();
    sb.append(form.getFirstApplicant().getLastName());
    sb.append(", ");
    List<String> firstNames = form.getApplicants().stream().map(ApplicantDTO::getFirstName).collect(
        Collectors.toList());
    sb.append(StringUtils.join(firstNames, " & "));
    sb.append(" RFH");
    placementHome.setFacltyNm(sb.toString());
  }


  @Mapping(target = "pkplcHmt", source = "identifier")
  @Mapping(target = "cityNm", expression = "java( placementHome.getCityNm() != null ? placementHome.getCityNm().toUpperCase() : null )")
  @Mapping(target = "geoRgntcd", expression = "java( placementHome.getGeoRgntcd() != null ? placementHome.getGeoRgntcd().toUpperCase(): null )")
  @Mapping(target = "laVndrId", expression = "java( placementHome.getLaVndrId() != null ? placementHome.getLaVndrId().toUpperCase() : null  )")
  @Mapping(target = "lstUpdId", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "licenseNo", expression = "java( placementHome.getLicenseNo() != null ? placementHome.getLicenseNo().toUpperCase() : null )")
  @Mapping(target = "facltyNm", expression = "java( placementHome.getFacltyNm() != null ? placementHome.getFacltyNm().toUpperCase() : null )")
  @Mapping(target = "streetNo", expression = "java( placementHome.getStreetNo() != null ? placementHome.getStreetNo().toUpperCase() : null )")
  @Mapping(target = "streetNm", expression = "java( placementHome.getStreetNm() != null ? placementHome.getStreetNm().toUpperCase() : null )")
  PlacementHomeUc toPlacementHomeUc(PlacementHome placementHome);
}
