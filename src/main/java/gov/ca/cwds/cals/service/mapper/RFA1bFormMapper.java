package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for RFA 1b form.
 *
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S1609", "squid:S1214"})
@Mapper
public interface RFA1bFormMapper {
  RFA1bFormMapper INSTANCE = Mappers.getMapper(RFA1bFormMapper.class);

  static final Long RESIDENTIAL_ADDRESS_TYPE_ID = 1L;

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "applicantNamePrefix", source = "namePrefix")
  @Mapping(target = "applicantFirstName", source = "firstName")
  @Mapping(target = "applicantMiddleName", source = "middleName")
  @Mapping(target = "applicantLastName", source = "lastName")
  @Mapping(target = "applicantNameSuffix", source = "nameSuffix")
  @Mapping(target = "dateOfBirth", source = "dateOfBirth")
  @Mapping(target = "driverLicense", source = "driverLicenseNumber")
  @Mapping(target = "driverLicenseState", source = "driverLicenseState")
  RFA1bFormDTO toRFA1bFormDTO(@MappingTarget RFA1bFormDTO target, ApplicantDTO applicant);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "applicantNamePrefix", source = "namePrefix")
  @Mapping(target = "applicantFirstName", source = "firstName")
  @Mapping(target = "applicantMiddleName", source = "middleName")
  @Mapping(target = "applicantLastName", source = "lastName")
  @Mapping(target = "applicantNameSuffix", source = "nameSuffix")
  @Mapping(target = "dateOfBirth", source = "dateOfBirth")
  RFA1bFormDTO toRFA1bFormDTO(@MappingTarget RFA1bFormDTO target, OtherAdultDTO otherAdultDTO);

  /**
   * Maps data from RFA1aFormDTO to given RFA1bFormDTO.
   *
   * @param target RFA1bFormDTO
   * @param rfa1a RFA1aFormDTO
   * @return mapped RFA1bFormDTO
   */
  default RFA1bFormDTO toRFA1bFormDTO(RFA1bFormDTO target, RFA1aFormDTO rfa1a) {
    // Residential Address
    Optional.ofNullable(rfa1a.getResidence()).ifPresent(residence -> {
      List<RFAAddressDTO> addresses = residence.getAddresses();
      Optional<RFAAddressDTO> residentialAddress = addresses.stream().filter(rfaAddressDTO ->
          rfaAddressDTO.getType() != null && rfaAddressDTO.getType().getId().equals(
              RESIDENTIAL_ADDRESS_TYPE_ID)).findFirst();
      residentialAddress.ifPresent(target::setResidenceAddress);
    });

    // County
    target.setApplicationCounty(rfa1a.getApplicationCounty());
    return target;
  }

}
