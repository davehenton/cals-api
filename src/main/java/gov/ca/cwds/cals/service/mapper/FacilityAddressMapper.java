package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static gov.ca.cwds.cals.Constants.AddressTypes.MAIL;
import static gov.ca.cwds.cals.Constants.AddressTypes.RESIDENTIAL;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = {AddressMapper.class, TrailingSpacesRemovalPostMappingProcessor.class})
@DecoratedWith(FacilityAddressMapperDecorator.class)
public interface FacilityAddressMapper {

    //This is standard mapstruct approach that is why it's false positive
    @SuppressWarnings({"squid:S1214"})
    FacilityAddressMapper INSTANCE = Mappers.getMapper(FacilityAddressMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", constant = RESIDENTIAL)
    @Mapping(target = "address", source = "placementHome", qualifiedByName = RESIDENTIAL)
    FacilityAddressDTO toResidentialAddress(PlacementHome placementHome);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", constant = MAIL)
    @Mapping(target = "address", source = "placementHome", qualifiedByName = MAIL)
    FacilityAddressDTO toMailAddress(PlacementHome placementHome);

}
