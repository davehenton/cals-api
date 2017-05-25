package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.model.fas.Rr809Dn;
import gov.ca.cwds.cals.model.fas.Rrcpoc;
import gov.ca.cwds.cals.service.dto.FacilityDeficiencyDTO;
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

    @Mapping(target = "id", source = "originalunidkey")
    @Mapping(target = "href", ignore = true)
    @Mapping(target = "representativeSignatureDate", source = "date2")
    @Mapping(target = "form809PrintDate", source = "date1")
    FacilityInspectionDTO toFacilityInspectionDto(Rr809Dn rr809Dn);

    @Mapping(target = "deficiencyType",     source = "deftype1")
    @Mapping(target = "pocDate",            source = "pocDate")
    @Mapping(target = "facSectionViolated", source = "facSectionviolated")
    @Mapping(target = "deficiency",         expression = "java(deficiency1 + ' ' + deficiency1Cont)")
    @Mapping(target = "correctionPlan",     expression = "java(correctionPlan + ' ' + correctionPlanCont)")
    FacilityDeficiencyDTO toPlanOfCorrectionDTO(Rr809Dn rrcpoc);

    @Mapping(target = "deficiencyType",     source = "deftype2Ab")
    @Mapping(target = "pocDate",            source = "pocDate1")
    @Mapping(target = "facSectionViolated", source = "facSectionviolated1")
    @Mapping(target = "deficiency",         expression = "java(deficiency11 + ' ' + deficiency2Cont)")
    @Mapping(target = "correctionPlan",     expression = "java(correctionPlan1 + ' ' + correctionPlan2Cont)")
    FacilityDeficiencyDTO toPlanOfCorrectionDTO1(Rr809Dn rrcpoc);

    @Mapping(target = "deficiencyType",     source = "deftype3Ab")
    @Mapping(target = "pocDate",            source = "pocDate2")
    @Mapping(target = "facSectionViolated", source = "facSectionviolated2")
    @Mapping(target = "deficiency",         expression = "java(deficiency12 + ' ' + deficiency3Cont)")
    @Mapping(target = "correctionPlan",     expression = "java(correctionPlan2 + ' ' + correctionPlan3Cont)")
    FacilityDeficiencyDTO toPlanOfCorrectionDTO2(Rr809Dn rrcpoc);

    @Mapping(target = "deficiencyType",     source = "deftype4Ab")
    @Mapping(target = "pocDate",            source = "pocDate3")
    @Mapping(target = "facSectionViolated", source = "facSectionviolated3")
    @Mapping(target = "deficiency",         source = "deficiency13")
    @Mapping(target = "correctionPlan",     source = "correctionPlan3")
    FacilityDeficiencyDTO toPlanOfCorrectionDTO3(Rr809Dn rrcpoc);

    @Mapping(target = "id", ignore = true)
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
            @MappingTarget FacilityInspectionDTO facilityInspectionDTO, Rr809Dn rr809Dn) {

        List<FacilityDeficiencyDTO> defs = facilityInspectionDTO.getDeficiencies();
        facilityInspectionDTO.setDeficiencies(defs);
        defs.add(toPlanOfCorrectionDTO(rr809Dn));
        defs.add(toPlanOfCorrectionDTO1(rr809Dn));
        defs.add(toPlanOfCorrectionDTO2(rr809Dn));
        defs.add(toPlanOfCorrectionDTO3(rr809Dn));


        StringBuilder hrefSb = new StringBuilder();
        hrefSb.append("/").append(Constants.API.FACILITIES)
                .append("/").append(rr809Dn.getFacilityNumberText())
                .append("/").append(Constants.API.INSPECTIONS)
                .append("/").append(facilityInspectionDTO.getId());

        facilityInspectionDTO.setHref(hrefSb.toString());
    }

    @AfterMapping
    default void fillPlanOfCorrectionDTOs(
            @MappingTarget FacilityInspectionDTO facilityInspectionDTO, Rrcpoc rrcpoc) {
        List<PlanOfCorrectionDTO> pocs = new ArrayList<>();
        facilityInspectionDTO.setPocs(pocs);
        pocs.add(toPlanOfCorrectionDTO(rrcpoc));
        pocs.add(toPlanOfCorrectionDTO1(rrcpoc));
        pocs.add(toPlanOfCorrectionDTO2(rrcpoc));
        pocs.add(toPlanOfCorrectionDTO3(rrcpoc));
    }

}
