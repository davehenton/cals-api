package gov.ca.cwds.cals.service.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDTO;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDocumentsDTO;
import gov.ca.cwds.cals.service.tracking.builder.TrackingDocumentsBuilder;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackingMapper {

  TrackingMapper INSTANCE = Mappers.getMapper(TrackingMapper.class);

  @Mapping(target = "id")
  @Mapping(target = "facilityName")
  @Mapping(target = "licenseNumber")
  @Mapping(target = "rfa1aId")
  @Mapping(target = "createDateTime")
  @Mapping(target = "updateDateTime")
  @Mapping(target = "createUserId")
  @Mapping(target = "updateUserId")
  TrackingDTO toTrackingDTO(Tracking tracking);

  @Mapping(target = "id")
  @Mapping(target = "facilityName")
  @Mapping(target = "licenseNumber")
  @Mapping(target = "rfa1aId")
  @Mapping(target = "createDateTime")
  @Mapping(target = "updateDateTime")
  @Mapping(target = "createUserId")
  @Mapping(target = "updateUserId")
  Tracking toTracking(TrackingDTO trackingDTO);

  @AfterMapping
  default void deserializeMappingDocuments(
      Tracking tracking,
      @MappingTarget TrackingDTO trackingDTO) {
    TrackingDocumentsBuilder builder = new TrackingDocumentsBuilder();
    TrackingDocumentsDTO trackingDocumentsDTO = builder.build(tracking.getTrackingJson());
    trackingDTO.setTrackingDocuments(trackingDocumentsDTO);
  }

  @AfterMapping
  default void serializeMappingDocuments(
      TrackingDTO trackingDTO,
      @MappingTarget Tracking tracking) {
    TrackingDocumentsBuilder builder = new TrackingDocumentsBuilder();
    JsonNode trackingJson = builder.buildJson(trackingDTO.getTrackingDocuments());
    tracking.setTrackingJson(trackingJson);
  }

}
