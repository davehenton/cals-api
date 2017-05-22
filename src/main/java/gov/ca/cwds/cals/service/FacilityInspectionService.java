package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.PlanOfCorrectionDTO;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityInspectionParameterObject;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionService extends CrudServiceAdapter {

    private FacilityInspectionMapper facilityInspectionMapper;

    @Inject
    public FacilityInspectionService(FacilityInspectionMapper facilityInspectionMapper) {
        this.facilityInspectionMapper = facilityInspectionMapper;
    }

    @Override
    public Response find(Serializable params) {
        FacilityInspectionParameterObject paramsObj = (FacilityInspectionParameterObject) params;

        FacilityInspectionDTO dto = new FacilityInspectionDTO();
        FacilityInspectionDTO facilityInspectionDTO = new FacilityInspectionDTO();

        facilityInspectionDTO.setId(String.valueOf(paramsObj.getInspectionId()));
        facilityInspectionDTO.setForm809PrintDate(LocalDateTime.now());
        facilityInspectionDTO.setHref("/facilities/"+ paramsObj.getFacilityId() +"/inspections/" + paramsObj.getInspectionId());
        facilityInspectionDTO.setRepresentativeSignatureDate(LocalDateTime.now());

        List<PlanOfCorrectionDTO> pocs = new ArrayList<>();
        facilityInspectionDTO.setPocs(pocs);

        for (int i = 0; i < 3; i++) {
            PlanOfCorrectionDTO poc = new PlanOfCorrectionDTO();
            poc.setPocComment("Comment #" + i);
            poc.setPocCorrectionPlan("Correction plan #" + i);
            poc.setPocDateCleared(LocalDateTime.now());
            poc.setPocDueDate(LocalDateTime.MAX);
            poc.setPocSectionViolated("Section Violated #" + i);
            poc.setPomCommentCont("Comment Cont #" + i);
            poc.setPomCorrectionPlanCont("Correction Plan Cont #" + i);

            pocs.add(poc);
        }

        //FacilityInspectionDTO dto =  facilityInspectionMapper.toFacilityInspectionDto(null);
        return dto;
    }

}
