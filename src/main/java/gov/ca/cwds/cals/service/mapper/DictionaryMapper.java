package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.LicenseStatus;
import gov.ca.cwds.cals.model.cms.StaffPerson;
import gov.ca.cwds.cals.service.dto.DictionaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface DictionaryMapper {

    @Mapping(target = "description", expression = "java(staffPerson.getFirstName() + ' ' + staffPerson.getLastName())")
    @Mapping(target = "code", ignore = true)
    DictionaryDTO toDictionary(StaffPerson staffPerson);

    @Mapping(target = "code", source = "lgcId")
    @Mapping(target = "description", source = "shortDsc")
    DictionaryDTO toDictionary(LicenseStatus licenseStatus);
}
