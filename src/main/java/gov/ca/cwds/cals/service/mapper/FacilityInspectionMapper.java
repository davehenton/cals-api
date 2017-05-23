package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.model.fas.Rrcpoc;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.PlanOfCorrectionDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface FacilityInspectionMapper {

    @Mapping(target = "id", source = "createDateTime")
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "representativeSignatureDate", ignore = true)
    @Mapping(target = "form809PrintDate", ignore = true)
    FacilityInspectionDTO toFacilityInspectionDto(Rrcpoc rrcpoc);

    @Mapping(target = "pocSectionViolated",    source = "cpocSectionviolated")
    @Mapping(target = "pocDueDate",            source = "cpocPocDate")
    @Mapping(target = "pocDateCleared",        source = "pocDatecleared")
    @Mapping(target = "pocCorrectionPlan",     source = "cpocCorrectionPlan")
    @Mapping(target = "pocComment",            source = "pocComments")
    @Mapping(target = "pocCorrectionPlanCont", source = "cpocCorrectionPlanCont")
    @Mapping(target = "pocCommentCont",        source = "pocCommentsCont")
    PlanOfCorrectionDTO toPlanOfCorrectionDTO(Rrcpoc rrcpoc);

    @Mapping(target = "pocSectionViolated",    source = "cpocSectionviolated1")
    @Mapping(target = "pocDueDate",            source = "cpocPocDate1")
    @Mapping(target = "pocDateCleared",        source = "pocDatecleared1")
    @Mapping(target = "pocCorrectionPlan",     source = "cpocCorrectionPlan1")
    @Mapping(target = "pocComment",            source = "pocComments1")
    @Mapping(target = "pocCorrectionPlanCont", source = "cpocCorrectionPlan2Cont")
    @Mapping(target = "pocCommentCont",        source = "pocCommentsCont1")
    PlanOfCorrectionDTO toPlanOfCorrectionDTO1(Rrcpoc rrcpoc);

    @Mapping(target = "pocSectionViolated",    source = "cpocSectionviolated2")
    @Mapping(target = "pocDueDate",            source = "cpocPocDate2")
    @Mapping(target = "pocDateCleared",        source = "pocDatecleared2")
    @Mapping(target = "pocCorrectionPlan",     source = "cpocCorrectionPlan2")
    @Mapping(target = "pocComment",            source = "pocComments2")
    @Mapping(target = "pocCorrectionPlanCont", source = "cpocCorrectionPlan3Cont")
    @Mapping(target = "pocCommentCont",        source = "pocCommentsCont3Cont")
    PlanOfCorrectionDTO toPlanOfCorrectionDTO2(Rrcpoc rrcpoc);

    @Mapping(target = "pocSectionViolated",    source = "cpocSectionviolated3")
    @Mapping(target = "pocDueDate",            source = "cpocPocDate3")
    @Mapping(target = "pocDateCleared",        source = "pocDatecleared3")
    @Mapping(target = "pocCorrectionPlan",     source = "cpocCorrectionPlan3")
    @Mapping(target = "pocComment",            source = "pocComments3")
    @Mapping(target = "pocCorrectionPlanCont", ignore = true)
    @Mapping(target = "pocCommentCont",        ignore = true)
    PlanOfCorrectionDTO toPlanOfCorrectionDTO3(Rrcpoc rrcpoc);

    @AfterMapping
    default void fillPlanOfCorrectionDTOs(
            @MappingTarget FacilityInspectionDTO facilityInspectionDTO, Rrcpoc rrcpoc) {
        List<PlanOfCorrectionDTO> pocs = new ArrayList<>();
        facilityInspectionDTO.setPocs(pocs);
        pocs.add(toPlanOfCorrectionDTO(rrcpoc));
        pocs.add(toPlanOfCorrectionDTO1(rrcpoc));
        pocs.add(toPlanOfCorrectionDTO2(rrcpoc));
        pocs.add(toPlanOfCorrectionDTO3(rrcpoc));

        StringBuilder hrefSb = new StringBuilder();
        hrefSb.append("/").append(Constants.API.FACILITIES)
                .append("/").append(rrcpoc.getFacilityNumber())
                .append("/").append(Constants.API.INSPECTIONS)
                .append("/").append(facilityInspectionDTO.getId());

        facilityInspectionDTO.setHref(hrefSb.toString());

    }

}
