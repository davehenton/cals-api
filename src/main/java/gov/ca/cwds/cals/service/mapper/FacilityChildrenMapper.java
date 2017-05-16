package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.cals.model.cms.OutOfHomePlacement;
import gov.ca.cwds.cals.model.cms.PlacementEpisode;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.model.cms.StaffPerson;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 *  @author CWDS CALS API Team
 *
 */
@Mapper
public interface  FacilityChildrenMapper {

    FacilityChildrenMapper INSTANCE = Mappers.getMapper(FacilityChildrenMapper.class);
    PersonDTOMapper personDtoMapper = Mappers.getMapper(PersonDTOMapper.class);

    default FacilityChildrenDTO toFacilityChildrenDTO(PlacementHome placementHome) {
        FacilityChildrenDTO facilityChildrenDTO = new FacilityChildrenDTO();
        Set<OutOfHomePlacement> outOfHomePlacements = placementHome.getOutOfHomePlacements();
        List<FacilityChildDTO> children = new ArrayList<>();

        for (OutOfHomePlacement outOfHomePlacement : outOfHomePlacements) {
            Set<PlacementEpisode> placementEpisodes = outOfHomePlacement.getPlacementEpisodes();
            for (PlacementEpisode placementEpisode: placementEpisodes) {
                Set<Client> clients = placementEpisode.getClients();
                for (Client client : clients) {
                    children.add(toFacilityChildDTO(outOfHomePlacement, placementEpisode, client));
                }
            }
            
        }

        facilityChildrenDTO.setChildren(children);

        return facilityChildrenDTO;
    }

    default FacilityChildDTO toFacilityChildDTO(OutOfHomePlacement outOfHomePlacement,
                                                PlacementEpisode placementEpisode, Client client) {
        FacilityChildDTO facilityChildDTO = new FacilityChildDTO();
        facilityChildDTO.setId(client.getIdentifier());

        PersonDTO personDTO = personDtoMapper.fromClient(client);
        facilityChildDTO.setPerson(personDTO);

        Set<StaffPerson> staffPersons = placementEpisode.getStaffPersons();

        if (staffPersons != null && !staffPersons.isEmpty()) {
            PersonDTO staffPersonDTO = INSTANCE.toPersonDTO(staffPersons.iterator().next());
            facilityChildDTO.setAssignedWorker(staffPersonDTO);
        }

        facilityChildDTO.setDateOfPlacement(outOfHomePlacement.getStartDt());

        return facilityChildDTO;
    }

    @Mapping(target = "firstName", source = "firstNm")
    @Mapping(target = "lastName", source = "lastNm")
    PersonDTO toPersonDTO(StaffPerson staffPerson);




}
