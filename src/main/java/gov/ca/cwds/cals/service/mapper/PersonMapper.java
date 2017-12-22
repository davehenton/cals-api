package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.PersonDTO;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.BaseStaffPerson;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface PersonMapper {

    List<PersonDTO> toPerson(Set<Client> clients);

    @Mapping(target = "firstName", source = "commonFirstName")
    @Mapping(target = "lastName", source = "commonLastName")
    @Mapping(target = "gender", source = "gender.code")
    @Mapping(target = "dateOfBirth", source = "birthDate")

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "age",         ignore = true)
    @Mapping(target = "ssn",         ignore = true)
    @Mapping(target = "ethnicityId", ignore = true)
    PersonDTO toPerson(Client client);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "gender",      ignore = true)
    @Mapping(target = "age",         ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "ssn",         ignore = true)
    @Mapping(target = "ethnicityId", ignore = true)
    PersonDTO toPersonDTO(BaseStaffPerson staffPerson);

    @AfterMapping
    default void fillAge(@MappingTarget PersonDTO personDTO) {
        LocalDate dateOfBirth = personDTO.getDateOfBirth();
        if (dateOfBirth != null) {
            Period period = Period.between(dateOfBirth, LocalDate.now());
            personDTO.setAge(period.getYears());
        }
    }
}
