package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.service.dto.PhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "relation", constant = "Primary")
    @Mapping(target = "type", constant = "Cell")
    @Mapping(target = "number", source = "facPhoneNbr")
    PhoneDTO lisFacilityToPhoneDTO(LisFacFile lisFacFile);

}
