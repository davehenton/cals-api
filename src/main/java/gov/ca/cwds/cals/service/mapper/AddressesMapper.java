package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public abstract class AddressesMapper {

    @AfterMapping
    protected void fillAddresses(LisFacFile lisFacFile, @MappingTarget FacilityDTO facilityDTO) {
        List<FacilityAddressDTO> addresses = new ArrayList<>(2);
        FacilityAddressDTO residentialAddress = new ResidentialAddressMapperImpl().lisFacilityToFacilityAddressDTO(
                lisFacFile);
        FacilityAddressDTO mailAddress = new MailAddressMapperImpl().lisFacilityToFacilityAddressDTO(
                lisFacFile);
        addresses.add(residentialAddress);
        addresses.add(mailAddress);
        facilityDTO.setAddress(addresses);
    }

}
