package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.Constants.AddressTypes.MAIL;
import static gov.ca.cwds.cals.Constants.AddressTypes.RESIDENTIAL;

import gov.ca.cwds.cals.service.CMSDictionaryEntriesHolder;
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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "lattitude", ignore = true)
    @Mapping(target = "deliverable", ignore = true)
    AddressDTO toResidentialAddressDTO(BasePlacementHome placementHome,
        CMSDictionaryEntriesHolder dictionaryEntriesHolder);

    @Named(MAIL)
    @Mapping(target = "streetAddress", expression = "java(StringUtils.trimToEmpty(placementHome.getPstreetNo()) + ' ' + placementHome.getPstreetNm())")
    @Mapping(target = "city", source = "placementHome.pCityNm")
    @Mapping(target = "state", source = "dictionaryEntriesHolder.payeeStateCode.logicalId")
    @Mapping(target = "zipCode", source = "placementHome.pZipNo")
    @Mapping(target = "zipSuffixCode", source = "placementHome.pyZipSfx")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "lattitude", ignore = true)
    @Mapping(target = "deliverable", ignore = true)
    AddressDTO toMailAddressDTO(BasePlacementHome placementHome,
        CMSDictionaryEntriesHolder dictionaryEntriesHolder);
}

