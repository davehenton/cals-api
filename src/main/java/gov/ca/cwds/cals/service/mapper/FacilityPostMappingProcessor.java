package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.PhoneDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public abstract class FacilityPostMappingProcessor {

    @AfterMapping
    protected void fillAddresses(LisFacFile lisFacFile, @MappingTarget FacilityDTO facilityDTO) {
        mapAddresses(lisFacFile, facilityDTO);
        mapPhones(lisFacFile, facilityDTO);
    }

    private void mapPhones(LisFacFile lisFacFile, FacilityDTO facilityDTO) {
        List<PhoneDTO> phones = new ArrayList(1);
        phones.add(new PhoneMapperImpl().lisFacilityToPhoneDTO(lisFacFile));
        facilityDTO.setPhone(phones);
    }

    private void mapAddresses(LisFacFile lisFacFile, FacilityDTO facilityDTO) {
        List<FacilityAddressDTO> addresses = new ArrayList<>(2); FacilityAddressDTO residentialAddress = new ResidentialAddressMapperImpl().lisFacilityToFacilityAddressDTO(
                lisFacFile);
        FacilityAddressDTO mailAddress = new MailAddressMapperImpl().lisFacilityToFacilityAddressDTO(
                lisFacFile);
        addresses.add(residentialAddress);
        addresses.add(mailAddress);
        facilityDTO.setAddress(addresses);
    }

}
