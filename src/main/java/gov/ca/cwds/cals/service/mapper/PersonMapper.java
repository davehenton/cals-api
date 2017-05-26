package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.cals.model.cms.StaffPerson;
import gov.ca.cwds.cals.service.dto.PersonDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    List<PersonDTO> toPerson(Set<Client> clients);

    @Mapping(target = "firstName", source = "comFstNm")
    @Mapping(target = "lastName", source = "comLstNm")
    @Mapping(target = "gender", source = "genderCd")
    @Mapping(target = "dateOfBirth", source = "birthDt")

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "age",         ignore = true)
    @Mapping(target = "ssn",         ignore = true)
    @Mapping(target = "ethnicityId", ignore = true)
    PersonDTO toPerson(Client client);

    @Mapping(target = "firstName", source = "firstNm")
    @Mapping(target = "lastName", source = "lastNm")
    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "gender",      ignore = true)
    @Mapping(target = "age",         ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "ssn",         ignore = true)
    @Mapping(target = "ethnicityId", ignore = true)
    PersonDTO toPersonDTO(StaffPerson staffPerson);

    @AfterMapping
    default void fillAge(@MappingTarget PersonDTO personDTO) {
        LocalDate dateOfBirth = personDTO.getDateOfBirth();
        if (dateOfBirth != null) {
            Period period = Period.between(dateOfBirth, LocalDate.now());
            personDTO.setAge(period.getYears());
        }
    }
}
