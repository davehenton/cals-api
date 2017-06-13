package gov.ca.cwds.cals.service.mapper.rs;

import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPlacementHome;
import gov.ca.cwds.cals.service.dto.rs.ReplicatedFacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS TPT-2
 */
public interface ReplicatedFacilityMapper extends FacilityMapper {
  @AfterMapping
  default void after(@MappingTarget ReplicatedFacilityDTO facilityDTO, ReplicatedPlacementHome placementHome) {
    facilityDTO.setReplicationDate(placementHome.getReplicationDate());
    facilityDTO.setReplicationOperation(placementHome.getReplicationOperation());
  }
}
