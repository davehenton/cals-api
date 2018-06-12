package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.CwsDictionaryEntriesHolder;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.cals.service.dto.AssignedWorkerDto;
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
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
@Mapper(imports = {LocalDateTime.class, StringUtils.class},
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

  @Mapping(source = "lisFacFile.facNbr", target = "id")
  @Mapping(source = "lisFacFile.facilityType", target = "type")
  @Mapping(source = "lisFacFile.facName", target = "name")
  @Mapping(source = "lisFacFile.facLicenseeName", target = "licenseeName")
  @Mapping(source = "lisFacFile.facLicenseeType", target = "licenseeType")
  @Mapping(source = "lpaInformation.lpaCode", target = "assignedWorker.id")
  @Mapping(source = "lpaInformation.fullLpaName", target = "assignedWorker.fullName")
  @Mapping(source = "lpaInformation.lpaEmailAddress", target = "assignedWorker.email")
  @Mapping(source = "lisFacFile.facDoNbr.doNbr", target = "districtOffice.number")
  @Mapping(source = "lisFacFile.facDoNbr.doName", target = "districtOffice.name")
  @Mapping(source = "lisFacFile.facNbr", target = "licenseNumber")
  @Mapping(source = "lisFacFile.facilityStatus", target = "status",
      qualifiedByName = "facilityStatus")
  @Mapping(source = "lisFacFile.facCapacity", target = "capacity")
  @Mapping(source = "lisFacFile.facLicEffDate", target = "licenseEffectiveDate")
  @Mapping(source = "lisFacFile.facOrigApplRecDate", target = "originalApplicationRecievedDate")
  @Mapping(source = "lisFacFile.facEmailAddress", target = "emailAddress")
  @Mapping(source = "lisFacFile.county", target = "county")
  @Mapping(target = "facilitySource", constant = "LIS")
  @Mapping(source = "lisFacFile.facIncCapEffDate", target = "capacityLastChanged")
  FacilityDTO toFacilityDTO(LisFacFile lisFacFile, LpaInformation lpaInformation);

  @AfterMapping
  default void afterLisFacilityMapping(
      @MappingTarget FacilityDTO facilityDto,
      LpaInformation lpaInformation) {
    if (lpaInformation != null) {
      PersonPhoneDTO phone = new PersonPhoneDTO();
      phone.setRelation("primary");
      phone.setNumber(lpaInformation.getLpaDoPhone());
      facilityDto.getAssignedWorker().addPhone(phone);
    }
  }

  @Mapping(target = "id", source = "placementHome.identifier")
  @Mapping(target = "adoptionOnly",
      expression = "java(\"Y\".equalsIgnoreCase(placementHome.getAdhmonly()))")
  @Mapping(target = "name", source = "placementHome.facltyNm")
  @Mapping(target = "type", source = "dictionaryEntriesHolder.facilityType")
  @Mapping(target = "licenseeName", source = "placementHome.licnseeNm")
  @Mapping(target = "assignedWorker.email",
      source = "placementHome.countyLicenseCase.staffPerson.emailAddr")
  @Mapping(target = "assignedWorker.fullName",
      expression = "java(StringUtils.trimToEmpty("
          + "placementHome.getCountyLicenseCase().getStaffPerson().getFirstName()) + ' '"
          + " + placementHome.getCountyLicenseCase().getStaffPerson().getLastName())")
  @Mapping(target = "districtOffice", source = "placementHome.countyLicenseCase.staffPerson.county")
  @Mapping(target = "licenseNumber", source = "placementHome.licenseNo")
  @Mapping(target = "status", source = "dictionaryEntriesHolder.licenseStatus")
  @Mapping(target = "capacity", source = "placementHome.maxCapNo")
  @Mapping(target = "licenseEffectiveDate", source = "placementHome.licEfctdt")
  @Mapping(target = "originalApplicationRecievedDate", source = "placementHome.licAplDt")
  @Mapping(target = "county", source = "dictionaryEntriesHolder.applicationCounty")
  @Mapping(target = "facilitySource", constant = "CWS/CMS")
  FacilityDTO toFacilityDTO(BasePlacementHome placementHome,
      CwsDictionaryEntriesHolder dictionaryEntriesHolder);

  @Mapping(target = "lastVisitDate",
      expression = "java(licensingVisit.getVisitDate() == null? " +
          "null : LocalDateTime.of(licensingVisit.getVisitDate(), java.time.LocalTime.MIN))")
  @Mapping(target = "lastVisitReason.description", source = "visitType.shortDescription")
  void toFacilityDTO(@MappingTarget FacilityDTO facilityDto, BaseLicensingVisit licensingVisit);

  ExpandedFacilityDTO toExpandedFacilityDTO(FacilityDTO facilityDto,
      List<FacilityChildDTO> children, List<FacilityInspectionDTO> inspections,
      Set<ComplaintDTO> complaints);

  @AfterMapping
  default void after(
      @MappingTarget FacilityDTO facilityDto,
      BasePlacementHome placementHome,
      CwsDictionaryEntriesHolder dictionaryEntriesHolder) {
    mapAssignedWorkerPhones(facilityDto.getAssignedWorker(),
        placementHome.getCountyLicenseCase().getStaffPerson());
    afterAddresses(facilityDto, placementHome, dictionaryEntriesHolder);
    afterPhones(facilityDto, placementHome);
    afterLastVisit(facilityDto, placementHome.getCountyLicenseCase());
  }

  default void afterLastVisit(FacilityDTO facilityDto,
      BaseCountyLicenseCase countyLicenseCase) {
    if (countyLicenseCase != null && CollectionUtils
        .isNotEmpty(countyLicenseCase.getLicensingVisits())) {
      List<? extends BaseLicensingVisit> licensingVisits = countyLicenseCase.getLicensingVisits();
      Mappers.getMapper(FacilityMapper.class).toFacilityDTO(
          facilityDto, licensingVisits.get(0));
    }
  }

  default void afterAddresses(FacilityDTO facilityDto,
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
      facilityDto.setFullResidentialAddress(
          AddressDTO.buildFullAddressText(residentialAddress.getAddress()));
      facilityAddressDTOs.add(residentialAddress);
    }

    facilityDto.setAddress(facilityAddressDTOs);
  }

  default void afterPhones(FacilityDTO facilityDto,
      BasePlacementHome placementHome) {
    List<PersonPhoneDTO> personPhoneDtos = new ArrayList<>(2);

    PhoneMapper phoneMapper = Mappers.getMapper(PhoneMapper.class);

    PersonPhoneDTO primaryPhone = phoneMapper.toPrimaryPhoneDTO(placementHome);
    if (Utils.Phone.checkIfPhoneDTOIsValid(primaryPhone)) {
      personPhoneDtos.add(primaryPhone);
    }

    PersonPhoneDTO alternativePhone = phoneMapper.toAlternatePhoneDTO(placementHome);
    if (Utils.Phone.checkIfPhoneDTOIsValid(alternativePhone)) {
      personPhoneDtos.add(alternativePhone);
    }

    facilityDto.setPhone(personPhoneDtos);
  }

  default void mapAssignedWorkerPhones(AssignedWorkerDto assignedWorkerDto,
      BaseStaffPerson assignedWorker) {
    PersonPhoneDTO phone = new PersonPhoneDTO();
    phone.setRelation("primary");
    phone.setNumber(String.valueOf(assignedWorker.getPhoneNo()));
    assignedWorkerDto.addPhone(phone);
  }

}
