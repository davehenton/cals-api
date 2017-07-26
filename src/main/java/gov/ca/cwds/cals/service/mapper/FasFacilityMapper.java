package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.fas.FacilityInfoLis;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityVisitDTO;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class, DictionaryMapper.class})
@FunctionalInterface
public interface FasFacilityMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "phone", ignore = true)
  @Mapping(target = "visits", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "county", ignore = true)
  @Mapping(target = "emailAddress", ignore = true)
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
  @Mapping(target = "lastVisitDate", source = "facLastVisitDate")
  @Mapping(target = "lastVisitReason", source = "facilityLastVisitReason", qualifiedByName = "facilityVisitReason")
  @Mapping(target = "lastDeferredVisitDate", source = "facLastDeferVisitDate")
  @Mapping(target = "lastDeferredVisitReason", source = "facilityLastDeferredVisitReason", qualifiedByName = "facilityVisitReason")
  @Mapping(target = "annualVisitYear", source = "facAnnualVisitYear")
  @Mapping(target = "prelicensingVisitDate", source = "facPreLicVisitDate")
  void toFacilityDTO(@MappingTarget FacilityDTO facilityDTO, FacilityInfoLis facilityInfoLis);

  @AfterMapping
  default void after(@MappingTarget FacilityDTO facilityDTO, FacilityInfoLis facilityInfoLis) {
    FacilityVisitMapper mapper = Mappers.getMapper(FacilityVisitMapper.class);
    List<FacilityVisitDTO> facilityVisitDTOs = new ArrayList<>(5);
    facilityVisitDTOs.add(mapper.toAnnual10MonthFacilityVisitDTO(facilityInfoLis));
    facilityVisitDTOs.add(mapper.toAnnual22MonthFacilityVisitDTO(facilityInfoLis));
    facilityVisitDTOs.add(mapper.toPostLicensingFacilityVisitDTO(facilityInfoLis));
    facilityVisitDTOs.add(mapper.toRenewalFacilityVisitDTO(facilityInfoLis));
    facilityDTO.setVisits(facilityVisitDTOs);
  }
}
