package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public interface ResidentialAddressMapper {

    ResidentialAddressMapper INSTANCE = Mappers.getMapper(ResidentialAddressMapper.class);

    @Mapping(source = "facResStreetAddr", target = "address.streetAddress")
    @Mapping(source = "facResCity", target = "address.city")
    @Mapping(source = "facResState", target = "address.state")
    @Mapping(source = "facResZipCode", target = "address.zipCode")
    @Mapping(constant = Constants.AddressTypes.RESIDENTIAL, target = "type")
    @Mapping(target = "id", ignore = true)
    FacilityAddressDTO lisFacilityToFacilityAddressDTO(LisFacFile lisFacFile);

}
