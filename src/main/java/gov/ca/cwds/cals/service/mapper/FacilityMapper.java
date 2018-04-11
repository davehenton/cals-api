package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.CwsDictionaryEntriesHolder;
import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.ExpandedFacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import gov.ca.cwds.cals.util.Utils;
import gov.ca.cwds.data.legacy.cms.entity.BaseCountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.BaseLicensingVisit;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = LocalDateTime.class,
    uses = {FacilityPostMappingProcessor.class, FacilityTypeMapper.class, CountyMapper.class,
        DistrictOfficeMapper.class, DictionaryMapper.class,
        TrailingSpacesRemovalPostMappingProcessor.class})
@SuppressWarnings("squid:S1214")
public interface FacilityMapper {

  FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);

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
  @Mapping(source = "lisFacFile.facilityType", target = "type")
  @Mapping(source = "lisFacFile.facName", target = "name")
  @Mapping(source = "lisFacFile.facLicenseeName", target = "licenseeName")
  @Mapping(source = "lisFacFile.facLicenseeType", target = "licenseeType")
  @Mapping(source = "lpaInformation.lpaCode", target = "assignedWorker.code")
  @Mapping(source = "lpaInformation.fullLpaName", target = "assignedWorker.description")
  @Mapping(source = "lisFacFile.facDoNbr.doNbr", target = "districtOffice.number")
  @Mapping(source = "lisFacFile.facDoNbr.doName", target = "districtOffice.name")
  @Mapping(source = "lisFacFile.facNbr", target = "licenseNumber")
  @Mapping(source = "lisFacFile.facilityStatus", target = "status", qualifiedByName = "facilityStatus")
  @Mapping(source = "lisFacFile.facCapacity", target = "capacity")
  @Mapping(source = "lisFacFile.facLicEffDate", target = "licenseEffectiveDate")
  @Mapping(source = "lisFacFile.facOrigApplRecDate", target = "originalApplicationRecievedDate")
  @Mapping(source = "lisFacFile.facEmailAddress", target = "emailAddress")
  @Mapping(source = "lisFacFile.county", target = "county")
  @Mapping(target = "facilitySource", constant = "LIS")
  @Mapping(source = "lisFacFile.facIncCapEffDate", target = "capacityLastChanged")
  FacilityDTO toFacilityDTO(LisFacFile lisFacFile, LpaInformation lpaInformation);

  @Mapping(target = "prelicensingVisitDate", ignore = true)
  @Mapping(target = "annualVisitYear", ignore = true)
  @Mapping(target = "visits", ignore = true)
  @Mapping(target = "lastDeferredVisitReason", ignore = true)
  @Mapping(target = "lastDeferredVisitDate", ignore = true)
  @Mapping(target = "id", source = "placementHome.identifier")
  @Mapping(target = "adoptionOnly",
      expression = "java(\"Y\".equalsIgnoreCase(placementHome.getAdhmonly()))")
  @Mapping(target = "name", source = "placementHome.facltyNm")
  @Mapping(target = "type", source = "dictionaryEntriesHolder.facilityType")
  @Mapping(target = "licenseeName", source = "placementHome.licnseeNm")
  @Mapping(target = "assignedWorker", source = "placementHome.countyLicenseCase.staffPerson")
  @Mapping(target = "districtOffice", source = "placementHome.countyLicenseCase.staffPerson.county")
  @Mapping(target = "licenseNumber", source = "placementHome.licenseNo")
  @Mapping(target = "status", source = "dictionaryEntriesHolder.licenseStatus")
  @Mapping(target = "capacity", source = "placementHome.maxCapNo")
  @Mapping(target = "licenseEffectiveDate", source = "placementHome.licEfctdt")
  @Mapping(target = "originalApplicationRecievedDate", source = "placementHome.licAplDt")
  @Mapping(target = "county", source = "dictionaryEntriesHolder.applicationCounty")
  @Mapping(target = "lastVisitDate", ignore = true)
  @Mapping(target = "lastVisitReason", ignore = true)
  @Mapping(target = "phone", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "href", ignore = true)
  @Mapping(target = "licenseeType", ignore = true)
  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "emailAddress", ignore = true)
  @Mapping(target = "facilitySource", constant = "CWS/CMS")
  @Mapping(target = "capacityLastChanged", ignore = true)
  FacilityDTO toFacilityDTO(BasePlacementHome placementHome,
      CwsDictionaryEntriesHolder dictionaryEntriesHolder);

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
  @Mapping(target = "lastVisitDate",
      expression = "java(licensingVisit.getVisitDate() == null? " +
          "null : LocalDateTime.of(licensingVisit.getVisitDate(), java.time.LocalTime.MIN))")
  @Mapping(target = "lastVisitReason.description", source = "visitType.shortDescription")
  void toFacilityDTO(@MappingTarget FacilityDTO facilityDTO, BaseLicensingVisit licensingVisit);

  ExpandedFacilityDTO toExpandedFacilityDTO(FacilityDTO facilityDTO,
      List<FacilityChildDTO> children, List<FacilityInspectionDTO> inspections,
      List<ComplaintDTO> complaints);

  @AfterMapping
  default void after(
      @MappingTarget FacilityDTO facilityDTO,
      BasePlacementHome placementHome,
      CwsDictionaryEntriesHolder dictionaryEntriesHolder) {
    afterAddresses(facilityDTO, placementHome, dictionaryEntriesHolder);
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
      BasePlacementHome placementHome,
      CwsDictionaryEntriesHolder dictionaryEntriesHolder) {
    List<FacilityAddressDTO> facilityAddressDTOs = new ArrayList<>(2);

    FacilityAddressMapper facilityAddressMapper = Mappers.getMapper(FacilityAddressMapper.class);

    if (!StringUtils
        .isAllBlank(placementHome.getStreetNo(), placementHome.getStreetNm(),
            placementHome.getCityNm(), placementHome.getZipNo(), placementHome.getZipSfxNo())) {
      FacilityAddressDTO residentialAddress = facilityAddressMapper
          .toResidentialAddress(placementHome, dictionaryEntriesHolder);
      facilityAddressMapper
          .afterMapping(residentialAddress, placementHome, dictionaryEntriesHolder);
      facilityAddressDTOs.add(residentialAddress);
    }

    if (!StringUtils.isAllBlank(placementHome.getPstreetNo(), placementHome.getPstreetNm(),
        placementHome.getpCityNm(), placementHome.getpZipNo(), placementHome.getPyZipSfx())) {
      FacilityAddressDTO mailingAddress = facilityAddressMapper
          .toMailAddress(placementHome, dictionaryEntriesHolder);
      facilityAddressMapper.afterMapping(mailingAddress, placementHome, dictionaryEntriesHolder);
      facilityAddressDTOs.add(mailingAddress);
    }

    facilityDTO.setAddress(facilityAddressDTOs);
  }

  default void afterPhones(@MappingTarget FacilityDTO facilityDTO,
      BasePlacementHome placementHome) {
    List<PersonPhoneDTO> personPhoneDTOS = new ArrayList<>(2);

    PhoneMapper phoneMapper = Mappers.getMapper(PhoneMapper.class);

    PersonPhoneDTO primaryPhone = phoneMapper.toPrimaryPhoneDTO(placementHome);
    if (Utils.Phone.checkIfPhoneDTOIsValid(primaryPhone)) {
      personPhoneDTOS.add(primaryPhone);
    }

    PersonPhoneDTO alternativePhone = phoneMapper.toAlternatePhoneDTO(placementHome);
    if (Utils.Phone.checkIfPhoneDTOIsValid(alternativePhone)) {
      personPhoneDTOS.add(alternativePhone);
    }

    facilityDTO.setPhone(personPhoneDTOS);
  }
}
