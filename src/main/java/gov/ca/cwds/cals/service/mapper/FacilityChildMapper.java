package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.BaseOutOfHomePlacement;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementEpisode;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *
 *  @author CWDS CALS API Team
 *
 */
@Mapper(uses = {PersonMapper.class, TrailingSpacesRemovalPostMappingProcessor.class})
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
    FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO, BaseStaffPerson staffPerson);

    @Mapping(target = "countyOfOrigin", source = "shortDescription")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "dateOfPlacement", ignore = true)
    @Mapping(target = "assignedWorker", ignore = true)
    @Mapping(target = "facilityId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO, County county);

    @Mapping(target = "dateOfPlacement", source = "startDt")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "countyOfOrigin", ignore = true)
    @Mapping(target = "assignedWorker", ignore = true)
    @Mapping(target = "facilityId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO, BaseOutOfHomePlacement outOfHomePlacement);

    @AfterMapping
    default void after(@MappingTarget FacilityChildDTO facilityChildDTO, Client client) {
        Set<? extends BasePlacementEpisode> placementEpisodes = client.getPlacementEpisodes();
        if (!placementEpisodes.isEmpty()) {

            FacilityChildMapper facilityChildMapper = Mappers.getMapper(FacilityChildMapper.class);

            BasePlacementEpisode placementEpisode = placementEpisodes.iterator().next();
            County county = placementEpisode.getCounty();
            facilityChildMapper.toFacilityChildDTO(facilityChildDTO, county);

// this logic needs to be reworked, StaffPerson is not the person who adds/removes child from placement home
//            BaseStaffPerson staffPerson = placementEpisode.getStaffPerson();
//            facilityChildMapper.toFacilityChildDTO(facilityChildDTO, staffPerson);

            Set<? extends BaseOutOfHomePlacement> outOfHomePlacements = placementEpisode.getOutOfHomePlacements();
            if (!outOfHomePlacements.isEmpty()) {

                BaseOutOfHomePlacement outOfHomePlacement = outOfHomePlacements.iterator().next();
                facilityChildMapper.toFacilityChildDTO(facilityChildDTO, outOfHomePlacement);
            }
        }
    }
}
