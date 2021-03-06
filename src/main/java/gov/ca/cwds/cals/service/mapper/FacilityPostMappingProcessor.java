package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import gov.ca.cwds.cals.util.LisFacilityAddressUtil;
import gov.ca.cwds.cals.util.Utils;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public abstract class FacilityPostMappingProcessor {

  private static final PhoneMapper PHONE_MAPPER = Mappers.getMapper(PhoneMapper.class);
  private static final MailAddressMapper MAIL_ADDRESS_MAPPER = Mappers
      .getMapper(MailAddressMapper.class);
  private static final ResidentialAddressMapper RESIDENTIAL_ADDRESS_MAPPER = Mappers.getMapper(
      ResidentialAddressMapper.class);

  @AfterMapping
  protected void mapLisPhonesAndAddresses(LisFacFile lisFacFile,
      @MappingTarget FacilityDTO facilityDTO) {
    mapAddresses(lisFacFile, facilityDTO);
    mapPhones(lisFacFile, facilityDTO);
  }

  private void mapPhones(LisFacFile lisFacFile, FacilityDTO facilityDTO) {
    List<PersonPhoneDTO> phones = new ArrayList<>(1);

    PersonPhoneDTO phone = PHONE_MAPPER.lisFacilityToPhoneDTO(lisFacFile);
    if (Utils.Phone.checkIfPhoneDTOIsValid(phone)) {
      phones.add(phone);
    }
    facilityDTO.setPhone(phones);
  }

  private void mapAddresses(LisFacFile lisFacFile, FacilityDTO facilityDTO) {
    List<FacilityAddressDTO> addresses = new ArrayList<>(2);

    if (LisFacilityAddressUtil.checkIfLisResidentialAddressIsValid(lisFacFile)) {
      FacilityAddressDTO residentialAddress = RESIDENTIAL_ADDRESS_MAPPER
          .lisFacilityToFacilityAddressDTO(lisFacFile);
      facilityDTO.setFullResidentialAddress(
          AddressDTO.buildFullAddressText(residentialAddress.getAddress()));
      addresses.add(residentialAddress);
    }

    if (LisFacilityAddressUtil.checkIfLisMailAddressIsValid(lisFacFile)) {
      FacilityAddressDTO mailAddress = MAIL_ADDRESS_MAPPER
          .lisFacilityToFacilityAddressDTO(lisFacFile);
      facilityDTO.setFullMailingAddress(
          AddressDTO.buildFullAddressText(mailAddress.getAddress()));
      addresses.add(mailAddress);
    }

    facilityDTO.setAddress(addresses);
  }
}
