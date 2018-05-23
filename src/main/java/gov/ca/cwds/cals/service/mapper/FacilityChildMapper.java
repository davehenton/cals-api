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
    imports = {gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator.class},
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@DecoratedWith(FacilityChildMapperDecorator.class)
public interface FacilityChildMapper {

  FacilityChildMapper INSTANCE = Mappers.getMapper(FacilityChildMapper.class);

  @Mapping(target = "id", source = "client.identifier")
  @Mapping(target = "displayClientId",
      expression = "java(CmsKeyIdGenerator.getUIIdentifierFromKey(client.getIdentifier()))")
  @Mapping(target = "person", source = "client")
  FacilityChildDTO toFacilityChildDTO(Client client);

  @Mapping(target = "assignedWorker", source = "staffPerson")
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDto,
      BaseStaffPerson staffPerson);

  @Mapping(target = "countyOfOrigin", source = "shortDescription")
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDto,
      County county);

  @Mapping(target = "dateOfPlacement", source = "startDt")
  FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDto,
      BaseOutOfHomePlacement outOfHomePlacement);

  @AfterMapping
  default void after(@MappingTarget FacilityChildDTO facilityChildDto, Client client) {
    Optional.ofNullable(client).ifPresent(c -> {
      Set<? extends BasePlacementEpisode> placementEpisodes = c.getPlacementEpisodes();
      if (!placementEpisodes.isEmpty()) {

        FacilityChildMapper facilityChildMapper = Mappers.getMapper(FacilityChildMapper.class);

        BasePlacementEpisode placementEpisode = placementEpisodes.iterator().next();
        County county = placementEpisode.getCounty();
        facilityChildMapper.toFacilityChildDTO(facilityChildDto, county);

        Set<? extends BaseOutOfHomePlacement> outOfHomePlacements = placementEpisode
            .getOutOfHomePlacements();
        if (!outOfHomePlacements.isEmpty()) {

          BaseOutOfHomePlacement outOfHomePlacement = outOfHomePlacements.iterator().next();
          facilityChildMapper.toFacilityChildDTO(facilityChildDto, outOfHomePlacement);
        }
      }
    });
  }
}