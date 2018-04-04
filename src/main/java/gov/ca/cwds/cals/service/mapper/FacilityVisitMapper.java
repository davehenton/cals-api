package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.fas.FacilityInformation;
import gov.ca.cwds.cals.service.dto.FacilityVisitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static gov.ca.cwds.cals.Constants.VisitTypes.ANNUAL_10_MONTH;
import static gov.ca.cwds.cals.Constants.VisitTypes.ANNUAL_22_MONTH;
import static gov.ca.cwds.cals.Constants.VisitTypes.POST_LICENSING;
import static gov.ca.cwds.cals.Constants.VisitTypes.RENEWAL;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface FacilityVisitMapper {

    @Mapping(target = "visitType", constant = ANNUAL_10_MONTH)
    @Mapping(target = "approval", source = "facAnnual10MoVisitAppr")
    @Mapping(target = "visitDate", source = "facAnnual10MoVisitDate")
    @Mapping(target = "visitDeferredDate", source = "facAnnual10MoDeferDate.")
    FacilityVisitDTO toAnnual10MonthFacilityVisitDTO(FacilityInformation facilityInformation);

    @Mapping(target = "visitType", constant = ANNUAL_22_MONTH)
    @Mapping(target = "approval", source = "facAnnual22MoVisitAppr")
    @Mapping(target = "visitDate", source = "facAnnual22MoVisitDate")
    @Mapping(target = "visitDeferredDate", source = "facAnnual22MoDeferDate")
    FacilityVisitDTO toAnnual22MonthFacilityVisitDTO(FacilityInformation facilityInformation);

    @Mapping(target = "visitType", constant = POST_LICENSING)
    @Mapping(target = "approval", source = "facPostLicVisitAppr")
    @Mapping(target = "visitDate", source = "facPostLicVisitDate")
    @Mapping(target = "visitDeferredDate", source = "facPostLicDeferDate")
    FacilityVisitDTO toPostLicensingFacilityVisitDTO(FacilityInformation facilityInformation);

    @Mapping(target = "visitType", constant = RENEWAL)
    @Mapping(target = "approval", source = "facRenewalVisitAppr")
    @Mapping(target = "visitDate", source = "facRenewalVisitDate")
    @Mapping(target = "visitDeferredDate", source = "facRenewalDeferDate")
    FacilityVisitDTO toRenewalFacilityVisitDTO(FacilityInformation facilityInformation);
}
