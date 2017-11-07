package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.Constants.PhoneTypes.ALTERNATE;
import static gov.ca.cwds.cals.Constants.PhoneTypes.PRIMARY;

import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */

@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
@DecoratedWith(PhoneMapperDecorator.class)
public interface PhoneMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = PRIMARY)
    @Mapping(target = "type", constant = "Cell")
    @Mapping(target = "number", source = "facPhoneNbr")
    PersonPhoneDTO lisFacilityToPhoneDTO(LisFacFile lisFacFile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = PRIMARY)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "number", source = "prmTelNo")
    PersonPhoneDTO toPrimaryPhoneDTO(BasePlacementHome placementHome);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = ALTERNATE)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "number", source = "bckTelNo")
    PersonPhoneDTO toAlternatePhoneDTO(BasePlacementHome placementHome);

}
