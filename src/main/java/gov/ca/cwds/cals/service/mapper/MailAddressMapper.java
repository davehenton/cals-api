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

@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface MailAddressMapper {

    //This is standard mapstruct approach that is why it's false positive
    @SuppressWarnings({"squid:S1214"})
    MailAddressMapper INSTANCE = Mappers.getMapper(MailAddressMapper.class);

    @Mapping(source = "facMailStreetAddr", target = "address.streetAddress")
    @Mapping(source = "facMailCity", target = "address.city")
    @Mapping(source = "facMailState", target = "address.state")
    @Mapping(source = "facMailZipCode", target = "address.zipCode")
    @Mapping(constant = Constants.AddressTypes.MAIL, target = "type")
    @Mapping(target = "id", ignore = true)
    FacilityAddressDTO lisFacilityToFacilityAddressDTO(LisFacFile lisFacFile);

}
