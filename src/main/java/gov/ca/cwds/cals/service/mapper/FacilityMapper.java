package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author CWDS CALS API Team
 */

@Mapper
public interface FacilityMapper {

    FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);

    @Mapping(source = "isnLisFacFile", target = "id")
    @Mapping(source = "facNbr", target = "number")
    @Mapping(source = "facName", target = "name")
    @Mapping(source = "facLicenseeName", target = "licenseeName")
    @Mapping(source = "facCapacity", target = "capacity")
    @Mapping(source = "facLicEffDate", target = "licenseEffectiveDate")
    @Mapping(source = "facOrigApplRecDate", target = "originalApplicationRecievedDate")
    @Mapping(source = "facLastVisitDate", target = "lastVisitDate")
    @Mapping(source = "facEmailAddress", target = "emailAddress")
    @Mapping(source = "facLastVisitReason", target = "lastVisitReason")
    @Mapping(source = "facLicenseeType", target = "licenseeType")

    FacilityDTO lisFacilityToFacilityDTO(LisFacFile lisFacFile);

    @InheritInverseConfiguration
    LisFacFile facilityDTOToLisFacility(FacilityDTO target);

}
