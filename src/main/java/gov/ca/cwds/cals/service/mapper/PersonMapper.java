package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.cals.service.dto.PersonDTO;
import org.mapstruct.*;
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

    List<PersonDTO> fromClient(Set<Client> clients);

    @Mapping(target = "firstName", source = "comFstNm")
    @Mapping(target = "lastName", source = "comLstNm")
    @Mapping(target = "gender", source = "genderCd")
    @Mapping(target = "dateOfBirth", source = "birthDt")
    PersonDTO fromClient(Client client);

    @AfterMapping
    default void fillAge(@MappingTarget PersonDTO personDTO) {
        LocalDate dateOfBirth = personDTO.getDateOfBirth();
        Period period = Period.between(dateOfBirth, LocalDate.now());
        personDTO.setAge(period.getYears());
    }
}
