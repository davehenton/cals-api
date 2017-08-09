package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BaseLicensingVisit;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.fas.Rr809Dn;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.ExpandedFacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import java.time.LocalDateTime;
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
@Mapper(imports = LocalDateTime.class,
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
  FacilityDTO toFacilityDTO(LisFacFile lisFacFile, LpaInformation lpaInformation);

  @Mapping(target = "prelicensingVisitDate", ignore = true)
  @Mapping(target = "annualVisitYear", ignore = true)
  @Mapping(target = "visits", ignore = true)
  @Mapping(target = "lastDeferredVisitReason", ignore = true)
  @Mapping(target = "lastDeferredVisitDate", ignore = true)
  @Mapping(target = "id", source = "placementHome.identifier")
  @Mapping(target = "name", source = "placementHome.facltyNm")
  @Mapping(target = "type", source = "placementHome.facilityType")
  @Mapping(target = "licenseeName", source = "placementHome.licnseeNm")
  @Mapping(target = "assignedWorker", source = "placementHome.countyLicenseCase.staffPerson")
  @Mapping(target = "districtOffice", source = "placementHome.countyLicenseCase.staffPerson.county")
  @Mapping(target = "licenseNumber", source = "placementHome.licenseNo")
  @Mapping(target = "status", source = "placementHome.licenseStatus")
  @Mapping(target = "capacity", source = "placementHome.maxCapNo")
  @Mapping(target = "licenseEffectiveDate", source = "placementHome.licEfctdt")
  @Mapping(target = "originalApplicationRecievedDate", source = "placementHome.licAplDt")
  @Mapping(target = "county", source = "applicationCounty")
  @Mapping(target = "lastVisitDate", ignore = true)
  @Mapping(target = "lastVisitReason", ignore = true)
  @Mapping(target = "phone", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "href", ignore = true)
  @Mapping(target = "licenseeType", ignore = true)
  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "emailAddress", ignore = true)
  FacilityDTO toFacilityDTO(BasePlacementHome placementHome, County applicationCounty);

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
  @Mapping(target = "lastVisitReason.description", source = "visitType.shortDsc")
  void toFacilityDTO(@MappingTarget FacilityDTO facilityDTO, BaseLicensingVisit licensingVisit);

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
    List<PersonPhoneDTO> personPhoneDTOS = new ArrayList<>(2);

    PhoneMapper phoneMapper = Mappers.getMapper(PhoneMapper.class);

    PersonPhoneDTO primaryPhone = phoneMapper.toPrimaryPhoneDTO(placementHome);
    if (primaryPhone != null) {
      personPhoneDTOS.add(primaryPhone);
    }

    PersonPhoneDTO alternativePhone = phoneMapper.toAlternatePhoneDTO(placementHome);
    if (alternativePhone != null) {
      personPhoneDTOS.add(alternativePhone);
    }
    facilityDTO.setPhone(personPhoneDTOS);
  }

}
