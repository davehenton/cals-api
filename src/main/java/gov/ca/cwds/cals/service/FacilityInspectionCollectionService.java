package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.FacilityInspectionDTO;
import gov.ca.cwds.cals.service.dto.FacilityInspectionsDTO;
import gov.ca.cwds.cals.service.dto.PlanOfCorrectionDTO;
import gov.ca.cwds.cals.service.mapper.FacilityInspectionMapper;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityInspectionCollectionService extends CrudServiceAdapter {

    private FacilityInspectionMapper facilityInspectionMapper;

    @Inject
    public FacilityInspectionCollectionService(FacilityInspectionMapper facilityInspectionMapper) {
        this.facilityInspectionMapper = facilityInspectionMapper;
    }

    @Override
    public Response find(Serializable facilityId) {
        FacilityInspectionsDTO dto = new FacilityInspectionsDTO();

        // TODO Implement retrieving Inspections data
        List<FacilityInspectionDTO> inspections = dto.getInspections();
        for (int i = 0; i < 3; i++)  {

            FacilityInspectionDTO facilityInspectionDTO = new FacilityInspectionDTO();

            facilityInspectionDTO.setId(String.valueOf(i));
            facilityInspectionDTO.setForm809PrintDate(LocalDateTime.now());
            facilityInspectionDTO.setHref("/facilities/"+ facilityId +"/inspections/" + i);
            facilityInspectionDTO.setRepresentativeSignatureDate(LocalDateTime.now());

            List<PlanOfCorrectionDTO> pocs = new ArrayList<>();
            facilityInspectionDTO.setPocs(pocs);

            for (int j = 0; j < 3; j++) {
                PlanOfCorrectionDTO poc = new PlanOfCorrectionDTO();
                poc.setPocComment("Comment #" + j);
                poc.setPocCorrectionPlan("Correction plan #" + j);
                poc.setPocDateCleared(LocalDateTime.now());
                poc.setPocDueDate(LocalDateTime.MAX);
                poc.setPocSectionViolated("Section Violated #" + j);
                poc.setPomCommentCont("Comment Cont #" + j);
                poc.setPomCorrectionPlanCont("Correction Plan Cont #" + j);

                pocs.add(poc);
            }

            inspections.add(facilityInspectionDTO);
        }

        return dto;
    }
}
