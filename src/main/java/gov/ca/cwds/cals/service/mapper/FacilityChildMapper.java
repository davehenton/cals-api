package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.data.legacy.cms.entity.BaseOutOfHomePlacement;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementEpisode;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import java.util.Optional;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = {PersonMapper.class,
    TrailingSpacesRemovalPostMappingProcessor.class},
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@DecoratedWith(FacilityChildMapperDecorator.class)
public interface FacilityChildMapper {

  FacilityChildMapper INSTANCE = Mappers.getMapper(FacilityChildMapper.class);

  @Mapping(target = "id", source = "client.identifier")
  @Mapping(target = "person", source = "client")
  @Mapping(target = "dateOfPlacement", ignore = true)
  @Mapping(target = "countyOfOrigin", ignore = true)
  @Mapping(target = "assignedWorker", ignore = true)
  @Mapping(target = "facilityId", ignore = true)
  @Mapping(target = "messages", ignore = true)
  FacilityChildDTO toFacilityChildDTO(Client client);

  @Mapping(target = "assignedWorker", source = "staffPerson")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "person", ignore = true)
  @Mapping(target = "dateOfPlacement", ignore = true)
  @Mapping(target = "countyOfOrigin", ignore = true)
  @Mapping(target = "facilityId", ignore = true)
  @Mapping(target = "messages", ignore = true)
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO,
      BaseStaffPerson staffPerson);

  @Mapping(target = "countyOfOrigin", source = "shortDescription")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "person", ignore = true)
  @Mapping(target = "dateOfPlacement", ignore = true)
  @Mapping(target = "assignedWorker", ignore = true)
  @Mapping(target = "facilityId", ignore = true)
  @Mapping(target = "messages", ignore = true)
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO,
      County county);

  @Mapping(target = "dateOfPlacement", source = "startDt")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "person", ignore = true)
  @Mapping(target = "countyOfOrigin", ignore = true)
  @Mapping(target = "assignedWorker", ignore = true)
  @Mapping(target = "facilityId", ignore = true)
  @Mapping(target = "messages", ignore = true)
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO,
      BaseOutOfHomePlacement outOfHomePlacement);

  @AfterMapping
  default void after(@MappingTarget FacilityChildDTO facilityChildDTO, Client client) {
    Optional.ofNullable(client).ifPresent(c -> {
      Set<? extends BasePlacementEpisode> placementEpisodes = c.getPlacementEpisodes();
      if (!placementEpisodes.isEmpty()) {

        FacilityChildMapper facilityChildMapper = Mappers.getMapper(FacilityChildMapper.class);

        BasePlacementEpisode placementEpisode = placementEpisodes.iterator().next();
        County county = placementEpisode.getCounty();
        facilityChildMapper.toFacilityChildDTO(facilityChildDTO, county);

        Set<? extends BaseOutOfHomePlacement> outOfHomePlacements = placementEpisode
            .getOutOfHomePlacements();
        if (!outOfHomePlacements.isEmpty()) {

          BaseOutOfHomePlacement outOfHomePlacement = outOfHomePlacements.iterator().next();
          facilityChildMapper.toFacilityChildDTO(facilityChildDTO, outOfHomePlacement);
        }
      }
    });
  }
}