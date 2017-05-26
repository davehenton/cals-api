package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.StaffPerson;
import gov.ca.cwds.cals.service.dto.DictionaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface DictionaryMapper {

    //This is standard mapstruct approach that is why it's false positive
    @SuppressWarnings({"squid:S1214"})
    DictionaryMapper INSTANCE = Mappers.getMapper(DictionaryMapper.class);

    @Mapping(target = "description", expression = "java(staffPerson.getFirstName() + ' ' + staffPerson.getLastName())")
    DictionaryDTO toDictionary(StaffPerson staffPerson);
}
