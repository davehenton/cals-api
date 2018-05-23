package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.Constants.AddressTypes.RESIDENTIAL;

import gov.ca.cwds.cals.service.CwsDictionaryEntriesHolder;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
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

    @Mapping(target = "type", constant = RESIDENTIAL)
    FacilityAddressDTO toResidentialAddress(BasePlacementHome placementHome,
        CwsDictionaryEntriesHolder dictionaryEntriesHolder);

    @AfterMapping
    default void afterMapping(
        @TargetType FacilityAddressDTO facilityAddressDTO,
        BasePlacementHome placementHome,
        CwsDictionaryEntriesHolder dictionaryEntriesHolder) {
        AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

        AddressDTO addressDTO = null;
        if (RESIDENTIAL.equals(facilityAddressDTO.getType())) {
            addressDTO = addressMapper
                .toResidentialAddressDTO(placementHome, dictionaryEntriesHolder);
        }
        facilityAddressDTO.setAddress(addressDTO);
    }

}
