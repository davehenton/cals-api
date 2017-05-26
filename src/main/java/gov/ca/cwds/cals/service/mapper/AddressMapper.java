package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static gov.ca.cwds.cals.Constants.ADDRESS_TYPES.MAIL;
import static gov.ca.cwds.cals.Constants.ADDRESS_TYPES.RESIDENTIAL;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Named(RESIDENTIAL)
    @Mapping(target = "streetAddress", expression = "java(placementHome.getStreetNo() + ' ' + placementHome.getStreetNm())")
    @Mapping(target = "city", source = "cityNm")
    @Mapping(target = "state", source = "stateCode.lgcId")
    @Mapping(target = "zipCode", source = "zipNo")
    @Mapping(target = "zipSuffixCode", source = "zipSfxNo")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "lattitude", ignore = true)
    @Mapping(target = "deliverable", ignore = true)
    AddressDTO toResidentialAddressDTO(PlacementHome placementHome);

    @Named(MAIL)
    @Mapping(target = "streetAddress", expression = "java(placementHome.getPstreetNo() + ' ' + placementHome.getPstreetNm())")
    @Mapping(target = "city", source = "pCityNm")
    @Mapping(target = "state", source = "payeeStateCode.lgcId")
    @Mapping(target = "zipCode", source = "pZipNo")
    @Mapping(target = "zipSuffixCode", source = "pyZipSfx")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "lattitude", ignore = true)
    @Mapping(target = "deliverable", ignore = true)
    AddressDTO toMailAddressDTO(PlacementHome placementHome);
}

