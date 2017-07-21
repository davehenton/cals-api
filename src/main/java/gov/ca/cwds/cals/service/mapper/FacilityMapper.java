package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BaseLicensingVisit;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.fas.Rr809Dn;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.ExpandedFacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.HyperlinkDTO;
import gov.ca.cwds.cals.service.dto.PhoneDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {Constants.class, HyperlinkDTO.class},
    uses = {FacilityPostMappingProcessor.class, FacilityTypeMapper.class, CountyMapper.class,
        DistrictOfficeMapper.class, DictionaryMapper.class,
        TrailingSpacesRemovalPostMappingProcessor.class})
public interface FacilityMapper {

  /**
   * Visit data is converted in {@link FasFacilityMapper}
   *
   * @param lisFacFile param to convert
   * @param lpaInformation param to convert
   * @return converted data
   */

  @Mapping(target = "lastVisitDate", ignore = true)
  @Mapping(target = "lastDeferredVisitDate", ignore = true)
  @Mapping(target = "lastVisitReason", ignore = true)
  @Mapping(target = "lastDeferredVisitReason", ignore = true)
  @Mapping(target = "visits", ignore = true)
  @Mapping(target = "annualVisitYear", ignore = true)
  @Mapping(target = "prelicensingVisitDate", ignore = true)
  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "href", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "phone", ignore = true)
  @Mapping(source = "lisFacFile.facNbr", target = "id")
  @Mapping(source = "lisFacFile.facType.tblFacTypeCode", target = "type.code")
  @Mapping(source = "lisFacFile.facType.tblFacTypeDesc", target = "type.description")
  @Mapping(source = "lisFacFile.facName", target = "name")
  @Mapping(source = "lisFacFile.facLicenseeName", target = "licenseeName")
  @Mapping(source = "lisFacFile.facLicenseeType", target = "licenseeType")
  @Mapping(source = "lpaInformation.lpaCode", target = "assignedWorker.code")
  @Mapping(source = "lpaInformation.fullLpaName", target = "assignedWorker.description")
  @Mapping(source = "lisFacFile.facDoNbr.doNbr", target = "districtOffice.number")
  @Mapping(source = "lisFacFile.facDoNbr.doName", target = "districtOffice.name")
  @Mapping(source = "lisFacFile.facNbr", target = "licenseNumber")
  @Mapping(source = "lisFacFile.facStatus.tblFacStatusCode", target = "status.code")
  @Mapping(source = "lisFacFile.facStatus.tblFacStatusDesc", target = "status.description")
  @Mapping(source = "lisFacFile.facCapacity", target = "capacity")
  @Mapping(source = "lisFacFile.facLicEffDate", target = "licenseEffectiveDate")
  @Mapping(source = "lisFacFile.facOrigApplRecDate", target = "originalApplicationRecievedDate")
  @Mapping(source = "lisFacFile.facEmailAddress", target = "emailAddress")
  @Mapping(source = "lisFacFile.facCoNbr.tblCoNbr", target = "county.code")
  @Mapping(source = "lisFacFile.facCoNbr.tblCoDesc", target = "county.description")
  FacilityDTO toFacilityDTO(LisFacFile lisFacFile, LpaInformation lpaInformation);

  @Mapping(target = "prelicensingVisitDate", ignore = true)
  @Mapping(target = "annualVisitYear", ignore = true)
  @Mapping(target = "visits", ignore = true)
  @Mapping(target = "lastDeferredVisitReason", ignore = true)
  @Mapping(target = "lastDeferredVisitDate", ignore = true)
  @Mapping(target = "id", source = "identifier")
  @Mapping(target = "name", source = "facltyNm")
  @Mapping(target = "type", source = "facilityType")
  @Mapping(target = "licenseeName", source = "licnseeNm")
  @Mapping(target = "assignedWorker", source = "countyLicenseCase.staffPerson")
  @Mapping(target = "districtOffice", source = "countyLicenseCase.staffPerson.county")
  @Mapping(target = "licenseNumber", source = "licenseNo")
  @Mapping(target = "status", source = "licenseStatus")
  @Mapping(target = "capacity", source = "maxCapNo")
  @Mapping(target = "licenseEffectiveDate", source = "licEfctdt")
  @Mapping(target = "originalApplicationRecievedDate", source = "licAplDt")
  @Mapping(target = "county", source = "county")
  @Mapping(target = "lastVisitDate", ignore = true)
  @Mapping(target = "lastVisitReason", ignore = true)
  @Mapping(target = "phone", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "href", ignore = true)
  @Mapping(target = "licenseeType", ignore = true)
  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "emailAddress", ignore = true)
  FacilityDTO toFacilityDTO(BasePlacementHome placementHome);

  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "phone", ignore = true)
  @Mapping(target = "prelicensingVisitDate", ignore = true)
  @Mapping(target = "annualVisitYear", ignore = true)
  @Mapping(target = "visits", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "county", ignore = true)
  @Mapping(target = "lastDeferredVisitReason", ignore = true)
  @Mapping(target = "emailAddress", ignore = true)
  @Mapping(target = "lastDeferredVisitDate", ignore = true)
  @Mapping(target = "originalApplicationRecievedDate", ignore = true)
  @Mapping(target = "licenseEffectiveDate", ignore = true)
  @Mapping(target = "capacity", ignore = true)
  @Mapping(target = "licenseNumber", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "districtOffice", ignore = true)
  @Mapping(target = "assignedWorker", ignore = true)
  @Mapping(target = "licenseeType", ignore = true)
  @Mapping(target = "licenseeName", ignore = true)
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "type", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "href", ignore = true)
  @Mapping(target = "lastVisitDate", source = "visitDate")
  @Mapping(target = "lastVisitReason.description", source = "visitType.shortDsc")
  void toFacilityDTO(@MappingTarget FacilityDTO facilityDTO, BaseLicensingVisit licensingVisit);

  @Mapping(target = "facilityType", ignore = true)
  @Mapping(target = "county", ignore = true)
  @Mapping(target = "stateCode", ignore = true)
  @Mapping(target = "payeeStateCode", ignore = true)
  @Mapping(target = "licenseStatus", ignore = true)
  @Mapping(target = "licenseNo", ignore = true)
  @Mapping(target = "licAplDt", ignore = true)
  @Mapping(target = "licEfctdt", ignore = true)
  @Mapping(target = "licExpDt", ignore = true)
  @Mapping(target = "lstUpdTs", ignore = true)
  @Mapping(target = "endDt", ignore = true)
  @Mapping(target = "phEndc", ignore = true)
  @Mapping(target = "endComdsc", ignore = true)
  @Mapping(target = "countyLicenseCase", ignore = true)
  @Mapping(target = "identifier", source = "placementHomeId")
  @Mapping(target = "ageToNo", constant = "18")
  @Mapping(target = "ageFrmNo", constant = "0")
  @Mapping(target = "atCapInd", constant = "N")
  @Mapping(target = "bckPersnm", constant = "")
  @Mapping(target = "bckExtNo", constant = "0")
  @Mapping(target = "bckTelNo", constant = "0")
  @Mapping(target = "certfPndt", constant = "1997-08-25")
  @Mapping(target = "chlcrPlcd", constant = "U")
  @Mapping(target = "cityNm", constant = "San Diego")
  @Mapping(target = "clSrvdc", constant = "0")
  @Mapping(target = "confEfind", constant = "N")
  @Mapping(target = "curOcpNo", constant = "15")
  @Mapping(target = "emrShltcd", constant = "U")
  @Mapping(target = "faxNo", constant = "0")
  @Mapping(target = "frgAdrtB", constant = "N")
  @Mapping(target = "gndrAcpcd", constant = "B")
  @Mapping(target = "geoRgntcd", constant = "")
  @Mapping(target = "inhmVstcd", constant = "U")
  @Mapping(target = "maxCapNo", constant = "0")
  @Mapping(target = "laVndrId", constant = "")
  @Mapping(target = "licCapNo", constant = "0")
  @Mapping(target = "licStatdt", constant = "1997-08-25")
  @Mapping(target = "licBsnc", constant = "0")
  @Mapping(target = "licnseeNm", constant = "")
  @Mapping(target = "licensrCd", constant = "CT")
  @Mapping(target = "facltyNm", constant = "San Diego Placement Home 3")
  @Mapping(target = "oprtdByid", constant = "")
  @Mapping(target = "oprtdBycd", constant = "")
  @Mapping(target = "pCityNm", constant = "")
  @Mapping(target = "pyeFstnm", constant = "")
  @Mapping(target = "pyeLstnm", constant = "")
  @Mapping(target = "pyeMidnm", constant = "")
  @Mapping(target = "pstreetNm", constant = "")
  @Mapping(target = "pstreetNo", constant = "")
  @Mapping(target = "pZipNo", constant = "0")
  @Mapping(target = "prmCnctnm", constant = "")
  @Mapping(target = "prmExtNo", constant = "0")
  @Mapping(target = "prmSubsnm", constant = "Dixon, Kathleen")
  @Mapping(target = "prmTelNo", constant = "0")
  @Mapping(target = "pvdTrnscd", constant = "U")
  @Mapping(target = "pubTrnscd", constant = "U")
  @Mapping(target = "streetNm", constant = "Beach St")
  @Mapping(target = "streetNo", constant = "3300")
  @Mapping(target = "zipNo", constant = "95223")
  @Mapping(target = "lstUpdId", constant = "q41")
  @Mapping(target = "addrDsc", constant = "")
  @Mapping(target = "spcharDsc", constant = "")
  @Mapping(target = "ctyprfDsc", constant = "")
  @Mapping(target = "edPvrDsc", constant = "")
  @Mapping(target = "envFctdsc", constant = "")
  @Mapping(target = "hazrdsDsc", constant = "")
  @Mapping(target = "lisPrfdsc", constant = "")
  @Mapping(target = "petsDsc", constant = "")
  @Mapping(target = "rlgActdsc", constant = "")
  @Mapping(target = "pyZipSfx", constant = "0")
  @Mapping(target = "zipSfxNo", constant = "0")
  @Mapping(target = "apStatTp", constant = "3081")
  @Mapping(target = "certCmplt", constant = "Y")
  @Mapping(target = "laPCtynm", constant = "")
  @Mapping(target = "laPFstnm", constant = "")
  @Mapping(target = "laPLstnm", constant = "")
  @Mapping(target = "laPMidnm", constant = "")
  @Mapping(target = "laPStnm", constant = "")
  @Mapping(target = "laPStno", constant = "")
  @Mapping(target = "laPZipno", constant = "0")
  @Mapping(target = "laPZpsfx", constant = "0")
  @Mapping(target = "laPBsnss", constant = "")
  @Mapping(target = "apStatDt", constant = "1997-08-25")
  @Mapping(target = "laPPhNo", constant = "0")
  @Mapping(target = "laPPhEx", constant = "0")
  @Mapping(target = "adhmonly", constant = "N")
  @Mapping(target = "pyeExtNo", constant = "0")
  @Mapping(target = "pyeTelNo", constant = "0")
  @Mapping(target = "arcassInd", constant = "N")
  @Mapping(target = "comfacInd", constant = "N")
  @Mapping(target = "trnhsgInd", constant = "N")
  @Mapping(target = "trnhsgFac", constant = "N")
  @Mapping(target = "newlicNo", constant = "")
  @Mapping(target = "newlicUpd", constant = "N")
  @Mapping(target = "oldfacId", constant = "")
  @Mapping(target = "emCntB", constant = "N")
  PlacementHome toPlacementHome(RFA1aForm form);

  ExpandedFacilityDTO toExpandedFacilityDTO(FacilityDTO facilityDTO,
      List<FacilityChildDTO> children, List<Rr809Dn> inspections,
      List<ComplaintReportLic802> complaints);

  @AfterMapping
  default void after(@MappingTarget FacilityDTO facilityDTO, BasePlacementHome placementHome) {
    afterAddresses(facilityDTO, placementHome);
    afterPhones(facilityDTO, placementHome);
    afterLastVisit(facilityDTO, placementHome.getCountyLicenseCase());
  }

  default void afterLastVisit(@MappingTarget FacilityDTO facilityDTO,
      BaseCountyLicenseCase countyLicenseCase) {
    if (countyLicenseCase != null && CollectionUtils
        .isNotEmpty(countyLicenseCase.getLicensingVisits())) {
      List<? extends BaseLicensingVisit> licensingVisits = countyLicenseCase.getLicensingVisits();
      Mappers.getMapper(FacilityMapper.class).toFacilityDTO(
          facilityDTO, licensingVisits.get(0));
    }
  }

  default void afterAddresses(@MappingTarget FacilityDTO facilityDTO,
      BasePlacementHome placementHome) {
    List<FacilityAddressDTO> facilityAddressDTOs = new ArrayList<>(2);

    FacilityAddressMapper facilityAddressMapper = Mappers.getMapper(FacilityAddressMapper.class);

    FacilityAddressDTO residentialAddress = facilityAddressMapper
        .toResidentialAddress(placementHome);
    if (residentialAddress != null) {
      facilityAddressDTOs.add(residentialAddress);
    }

    FacilityAddressDTO mailingAddress = facilityAddressMapper.toMailAddress(placementHome);
    if (mailingAddress != null) {
      facilityAddressDTOs.add(mailingAddress);
    }
    facilityDTO.setAddress(facilityAddressDTOs);
  }

  default void afterPhones(@MappingTarget FacilityDTO facilityDTO,
      BasePlacementHome placementHome) {
    List<PhoneDTO> phoneDTOs = new ArrayList<>(2);

    PhoneMapper phoneMapper = Mappers.getMapper(PhoneMapper.class);

    PhoneDTO primaryPhone = phoneMapper.toPrimaryPhoneDTO(placementHome);
    if (primaryPhone != null) {
      phoneDTOs.add(primaryPhone);
    }

    PhoneDTO alternativePhone = phoneMapper.toAlternatePhoneDTO(placementHome);
    if (alternativePhone != null) {
      phoneDTOs.add(alternativePhone);
    }
    facilityDTO.setPhone(phoneDTOs);
  }
}
