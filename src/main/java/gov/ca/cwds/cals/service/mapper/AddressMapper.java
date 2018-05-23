package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.Constants.AddressTypes.RESIDENTIAL;

import gov.ca.cwds.cals.service.CwsDictionaryEntriesHolder;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.data.legacy.cms.entity.BasePlacementHome;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @author CWDS CALS API Team
 */
@Mapper(
    imports = StringUtils.class,
    uses = TrailingSpacesRemovalPostMappingProcessor.class
)
public interface AddressMapper {

    @Named(RESIDENTIAL)
    @Mapping(target = "streetAddress", expression = "java(StringUtils.trimToEmpty(placementHome.getStreetNo()) + ' ' + placementHome.getStreetNm())")
    @Mapping(target = "city", source = "placementHome.cityNm")
    @Mapping(target = "state", source = "dictionaryEntriesHolder.stateCode.logicalId")
    @Mapping(target = "zipCode", source = "placementHome.zipNo")
    @Mapping(target = "zipSuffixCode", source = "placementHome.zipSfxNo")
    AddressDTO toResidentialAddressDTO(BasePlacementHome placementHome,
        CwsDictionaryEntriesHolder dictionaryEntriesHolder);

}

