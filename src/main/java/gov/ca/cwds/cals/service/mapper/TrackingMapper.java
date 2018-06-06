package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDTO;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface TrackingMapper {

  TrackingMapper INSTANCE = Mappers.getMapper(TrackingMapper.class);

  @Mapping(target = "id")
  @Mapping(target = "facilityName")
  @Mapping(target = "licenseNumber")
  @Mapping(target = "rfa1aId")
  @Mapping(target = "trackingDocuments")
  @Mapping(target = "createDatetime")
  @Mapping(target = "createUserId")
  @Mapping(target = "updateUserId")
  TrackingDTO toTrackingDTO(Tracking tracking);

  @Mapping(target = "id")
  @Mapping(target = "facilityName")
  @Mapping(target = "licenseNumber")
  @Mapping(target = "rfa1aId")
  @Mapping(target = "trackingDocuments")
  @Mapping(target = "createDatetime")
  @Mapping(target = "createUserId")
  @Mapping(target = "updateUserId")
  Tracking toTracking(TrackingDTO trackingDTO);


}
