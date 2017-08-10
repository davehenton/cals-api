package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.Constants.AddressTypes.MAIL;
import static gov.ca.cwds.cals.Constants.AddressTypes.RESIDENTIAL;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.service.CMSDictionaryEntriesHolder;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.TargetType;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = {TrailingSpacesRemovalPostMappingProcessor.class})
public interface FacilityAddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", constant = RESIDENTIAL)
    @Mapping(target = "address", ignore = true)
    FacilityAddressDTO toResidentialAddress(BasePlacementHome placementHome,
        CMSDictionaryEntriesHolder dictionaryEntriesHolder);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", constant = MAIL)
    @Mapping(target = "address", ignore = true)
    FacilityAddressDTO toMailAddress(BasePlacementHome placementHome,
        CMSDictionaryEntriesHolder dictionaryEntriesHolder);

    @AfterMapping
    default void afterMapping(
        @TargetType FacilityAddressDTO facilityAddressDTO,
        BasePlacementHome placementHome,
        CMSDictionaryEntriesHolder dictionaryEntriesHolder) {
        AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

        AddressDTO addressDTO = null;
        if (RESIDENTIAL.equals(facilityAddressDTO.getType())) {
            addressDTO = addressMapper
                .toResidentialAddressDTO(placementHome, dictionaryEntriesHolder);
        }
        if (MAIL.equals(facilityAddressDTO.getType())) {
            addressDTO = addressMapper.toMailAddressDTO(placementHome, dictionaryEntriesHolder);
        }
        facilityAddressDTO.setAddress(addressDTO);
    }

}
