package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.HyperlinkDTO;
import gov.ca.cwds.cals.service.dto.PhoneDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports = {Constants.class, HyperlinkDTO.class},
        uses={FacilityPostMappingProcessor.class, FacilityTypeMapper.class} )
public interface FacilityMapper {

    FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);

    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(source = "facNbr", target = "id")
    @Mapping(source = "facType.tblFacTypeCode", target = "type.code")
    @Mapping(source = "facType.tblFacTypeDesc", target = "type.description")
    @Mapping(source = "facName", target = "name")
    @Mapping(source = "facLicenseeName", target = "licenseeName")
    @Mapping(source = "facLicenseeType", target = "licenseeType")
    @Mapping(source = "facDoEvalCode", target = "assignedWorker")
    @Mapping(source = "facDoNbr.doNbr", target = "districtOffice.number")
    @Mapping(source = "facDoNbr.doName", target = "districtOffice.name")
    @Mapping(source = "facNbr", target = "licenseNumber")
    @Mapping(source = "facStatus.tblFacStatusCode", target = "status.code")
    @Mapping(source = "facStatus.tblFacStatusDesc", target = "status.description")
    @Mapping(source = "facCapacity", target = "capacity")
    @Mapping(source = "facLicEffDate", target = "licenseEffectiveDate")
    @Mapping(source = "facOrigApplRecDate", target = "originalApplicationRecievedDate")
    @Mapping(source = "facLastVisitDate", target = "lastVisitDate")
    @Mapping(source = "facEmailAddress", target = "emailAddress")
    @Mapping(source = "facLastVisitReason.tblVisitReasonCode", target = "lastVisitReason.code")
    @Mapping(source = "facLastVisitReason.tblVisitReasonDesc", target = "lastVisitReason.description")
    @Mapping(source = "facCoNbr.tblCoNbr", target = "county.code")
    @Mapping(source = "facCoNbr.tblCoDesc", target = "county.description")
    @Mapping(target = "children", expression = "java(new HyperlinkDTO( String.format(Constants.API.FACILITIES + " +
            "\"/%s/\" + Constants.API.CHILDREN, lisFacFile.getFacNbr())))")
    @Mapping(target = "complains", expression = "java(new HyperlinkDTO( String.format(Constants.API.FACILITIES + " +
            "\"/%s/\" + Constants.API.COMPLAINTS, lisFacFile.getFacNbr())))")

    FacilityDTO toFacilityDTO(LisFacFile lisFacFile);

    @Mapping(target = "name", source = "facltyNm")
    @Mapping(target = "type", source = "facilityType")
    @Mapping(target = "licenseeName", source = "licnseeNm")
//    @Mapping(target = "Approval/Licensing worker", source = "")
//    @Mapping(target = "Assigned oversight agency", source = "")
    @Mapping(target = "licenseNumber", source = "licenseNo")
//    @Mapping(target = "status", source = "licStc")
    @Mapping(target = "capacity", source = "maxCapNo")
    @Mapping(target = "licenseEffectiveDate", source = "licEfctdt")
    @Mapping(target = "originalApplicationRecievedDate", source = "licAplDt")
//    @Mapping(target = "lastVisitDate", source = "lic_vstt \tvisit_date")
//    @Mapping(target = "lastVisitReason.code", source = "lic_vstt \tvisit_type")
    FacilityDTO toFacilityDTO(PlacementHome placementHome);

    @AfterMapping
    default void after(@MappingTarget FacilityDTO facilityDTO, PlacementHome placementHome) {
        List<FacilityAddressDTO> facilityAddressDTOs = new ArrayList<>(2);
        facilityAddressDTOs.add(FacilityAddressMapper.INSTANCE.toResidentialAddress(placementHome));
        facilityAddressDTOs.add(FacilityAddressMapper.INSTANCE.toMailAddress(placementHome));
        facilityDTO.setAddress(facilityAddressDTOs);

        List<PhoneDTO> phoneDTOs = new ArrayList<>(2);
        phoneDTOs.add(PhoneMapper.INSTANCE.toPrimaryPhoneDTO(placementHome));
        phoneDTOs.add(PhoneMapper.INSTANCE.toAlternatePhoneDTO(placementHome));
        facilityDTO.setPhone(phoneDTOs);
    }

/*
    @InheritInverseConfiguration
    LisFacFile facilityDTOToLisFacility(FacilityDTO target);
*/
}
