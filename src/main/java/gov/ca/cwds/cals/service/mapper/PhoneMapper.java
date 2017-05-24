package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.PhoneDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static gov.ca.cwds.cals.Constants.PHONE_TYPES.ALTERNATE;
import static gov.ca.cwds.cals.Constants.PHONE_TYPES.PRIMARY;

/**
 * @author CWDS CALS API Team
 */

@Mapper
@DecoratedWith(PhoneMapperDecorator.class)
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = PRIMARY)
    @Mapping(target = "type", constant = "Cell")
    @Mapping(target = "number", source = "facPhoneNbr")
    PhoneDTO lisFacilityToPhoneDTO(LisFacFile lisFacFile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = PRIMARY)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "number", source = "prmTelNo")
    PhoneDTO toPrimaryPhoneDTO(PlacementHome placementHome);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = ALTERNATE)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "number", source = "bckTelNo")
    PhoneDTO toAlternatePhoneDTO(PlacementHome placementHome);

}
