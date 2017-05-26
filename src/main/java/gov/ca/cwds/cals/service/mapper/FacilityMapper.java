package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.model.cms.CountyLicenseCase;
import gov.ca.cwds.cals.model.cms.LicensingVisit;
import gov.ca.cwds.cals.model.fas.LpaInformation;
import gov.ca.cwds.cals.model.lis.LisFacFile;
import gov.ca.cwds.cals.model.cms.PlacementHome;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.dto.FacilityAddressDTO;
import gov.ca.cwds.cals.service.dto.HyperlinkDTO;
import gov.ca.cwds.cals.service.dto.PhoneDTO;
import org.apache.commons.collections4.CollectionUtils;
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
        uses={FacilityPostMappingProcessor.class, FacilityTypeMapper.class, CountyMapper.class,
              DistrictOfficeMapper.class, DictionaryMapper.class, TrailingSpacesRemovalPostMappingProcessor.class} )
public interface FacilityMapper {

    //This is standard mapstruct approach that is why it's false positive
    @SuppressWarnings({"squid:S1214"})
    FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);

    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(source = "lisFacFile.facNbr", target = "id")
    @Mapping(source = "lisFacFile.facType.tblFacTypeCode", target = "type.code")
    @Mapping(source = "lisFacFile.facType.tblFacTypeDesc", target = "type.description")
    @Mapping(source = "lisFacFile.facName", target = "name")
    @Mapping(source = "lisFacFile.facLicenseeName", target = "licenseeName")
    @Mapping(source = "lisFacFile.facLicenseeType", target = "licenseeType")
    @Mapping(source = "lpaInformation.lpaCode", target = "assignedWorker.code")
    @Mapping(source = "lpaInformation.fullLpaName", target = "assignedWorker.description")
    @Mapping(source = "lisFacFile.facDoNbr.doNbr", target = "districtOffice.number")
    @Mapping(source = "lisFacFile.facDoNbr.doName", target = "districtOffice.name")
    @Mapping(source = "lisFacFile.facNbr", target = "licenseNumber")
    @Mapping(source = "lisFacFile.facStatus.tblFacStatusCode", target = "status.code")
    @Mapping(source = "lisFacFile.facStatus.tblFacStatusDesc", target = "status.description")
    @Mapping(source = "lisFacFile.facCapacity", target = "capacity")
    @Mapping(source = "lisFacFile.facLicEffDate", target = "licenseEffectiveDate")
    @Mapping(source = "lisFacFile.facOrigApplRecDate", target = "originalApplicationRecievedDate")
    @Mapping(source = "lisFacFile.facLastVisitDate", target = "lastVisitDate")
    @Mapping(source = "lisFacFile.facEmailAddress", target = "emailAddress")
    @Mapping(source = "lisFacFile.facLastVisitReason.tblVisitReasonCode", target = "lastVisitReason.code")
    @Mapping(source = "lisFacFile.facLastVisitReason.tblVisitReasonDesc", target = "lastVisitReason.description")
    @Mapping(source = "lisFacFile.facCoNbr.tblCoNbr", target = "county.code")
    @Mapping(source = "lisFacFile.facCoNbr.tblCoDesc", target = "county.description")
    @Mapping(target = "children", expression = "java(new HyperlinkDTO( String.format(Constants.API.FACILITIES + " +
            "\"/%s/\" + Constants.API.CHILDREN, lisFacFile.getFacNbr())))")
    @Mapping(target = "complains", expression = "java(new HyperlinkDTO( String.format(Constants.API.FACILITIES + " +
            "\"/%s/\" + Constants.API.COMPLAINTS, lisFacFile.getFacNbr())))")
    FacilityDTO toFacilityDTO(LisFacFile lisFacFile, LpaInformation lpaInformation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "facltyNm")
    @Mapping(target = "type", source = "facilityType")
    @Mapping(target = "licenseeName", source = "licnseeNm")
    @Mapping(target = "assignedWorker", source = "countyLicenseCase.staffPerson")
    @Mapping(target = "districtOffice", source = "countyLicenseCase.staffPerson.county")
    @Mapping(target = "licenseNumber", source = "licenseNo")
//    @Mapping(target = "status", source = "licStc")
    @Mapping(target = "capacity", source = "maxCapNo")
    @Mapping(target = "licenseEffectiveDate", source = "licEfctdt")
    @Mapping(target = "originalApplicationRecievedDate", source = "licAplDt")
    @Mapping(target = "county", source = "county")
    @Mapping(target = "lastVisitDate", ignore = true)
    @Mapping(target = "lastVisitReason", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "emailAddress", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "complains", ignore = true)
    FacilityDTO toFacilityDTO(PlacementHome placementHome);

    @Mapping(target = "lastVisitDate", source = "visitDate")
    @Mapping(target = "lastVisitReason.description", source = "visitType.shortDsc")
    FacilityDTO toFacilityDTO(@MappingTarget FacilityDTO facilityDTO, LicensingVisit licensingVisit);

    @AfterMapping
    default void after(@MappingTarget FacilityDTO facilityDTO, PlacementHome placementHome) {
        afterAddresses(facilityDTO, placementHome);
        afterPhones(facilityDTO, placementHome);
        afterLastVisit(facilityDTO, placementHome.getCountyLicenseCase());
    }

    default void afterLastVisit(@MappingTarget FacilityDTO facilityDTO, CountyLicenseCase countyLicenseCase) {
        if (CollectionUtils.isNotEmpty(countyLicenseCase.getLicensingVisits())) {
            INSTANCE.toFacilityDTO(facilityDTO, countyLicenseCase.getLicensingVisits().get(0));
        }
    }

    default void afterAddresses(@MappingTarget FacilityDTO facilityDTO, PlacementHome placementHome) {
        List<FacilityAddressDTO> facilityAddressDTOs = new ArrayList<>(2);

        FacilityAddressDTO residentialAddress = FacilityAddressMapper.INSTANCE.toResidentialAddress(placementHome);
        if (residentialAddress != null) {
            facilityAddressDTOs.add(residentialAddress);
        }

        FacilityAddressDTO mailingAddress = FacilityAddressMapper.INSTANCE.toMailAddress(placementHome);
        if (mailingAddress != null) {
            facilityAddressDTOs.add(mailingAddress);
        }
        facilityDTO.setAddress(facilityAddressDTOs);
    }

    default void afterPhones(@MappingTarget FacilityDTO facilityDTO, PlacementHome placementHome) {
        List<PhoneDTO> phoneDTOs = new ArrayList<>(2);

        PhoneDTO primaryPhone = PhoneMapper.INSTANCE.toPrimaryPhoneDTO(placementHome);
        if (primaryPhone != null) {
            phoneDTOs.add(primaryPhone);
        }

        PhoneDTO alternativePhone = PhoneMapper.INSTANCE.toAlternatePhoneDTO(placementHome);
        if (alternativePhone != null) {
            phoneDTOs.add(alternativePhone);
        }
        facilityDTO.setPhone(phoneDTOs);
    }

}
