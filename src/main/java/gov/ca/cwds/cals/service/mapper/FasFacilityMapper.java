package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityVisitDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class})
public interface FasFacilityMapper {

  @Mapping(target = "lastVisitDate", source = "facLastVisitDate")
  @Mapping(target = "lastVisitReason.code", source = "facLastVisitReason.tblVisitReasonCode")
  @Mapping(target = "lastVisitReason.description", source = "facLastVisitReason.tblVisitReasonDesc")
  @Mapping(target = "lastDeferredVisitDate", source = "facLastDeferVisitDate")
  @Mapping(target = "lastDeferredVisitReason.code", source = "facLastDeferVisitReason.tblVisitReasonCode")
  @Mapping(target = "lastDeferredVisitReason.description", source = "facLastDeferVisitReason.tblVisitReasonDesc")
  @Mapping(target = "annualVisitYear", source = "facAnnualVisitYear")
  @Mapping(target = "prelicensingVisitDate", source = "facPreLicVisitDate")
  void toFacilityDTO(@MappingTarget FacilityDTO facilityDTO, LisFacFile fasLisFacFile);

  @AfterMapping
  default void after(@MappingTarget FacilityDTO facilityDTO, LisFacFile lisFacFile) {
    FacilityVisitMapper mapper = Mappers.getMapper(FacilityVisitMapper.class);
    List<FacilityVisitDTO> facilityVisitDTOs = new ArrayList<>(5);
    facilityVisitDTOs.add(mapper.toAnnual10MonthFacilityVisitDTO(lisFacFile));
    facilityVisitDTOs.add(mapper.toAnnual22MonthFacilityVisitDTO(lisFacFile));
    facilityVisitDTOs.add(mapper.toPostLicensingFacilityVisitDTO(lisFacFile));
    facilityVisitDTOs.add(mapper.toRenewalFacilityVisitDTO(lisFacFile));
    facilityVisitDTOs.add(mapper.toMiddleYearVisitDTO(lisFacFile));
    facilityDTO.setVisits(facilityVisitDTOs);
  }
}
