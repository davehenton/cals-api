package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.fas.Rrcpoc;
import gov.ca.cwds.cals.persistence.dao.fas.InspectionDao;
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
    private InspectionDao inspectionDao;

    @Inject
    public FacilityInspectionCollectionService(FacilityInspectionMapper facilityInspectionMapper,
                                               InspectionDao inspectionDao) {
        this.facilityInspectionMapper = facilityInspectionMapper;
        this.inspectionDao = inspectionDao;
    }

    @Override
    public Response find(Serializable facilityId) {
        final FacilityInspectionsDTO dto = new FacilityInspectionsDTO();
        List<Rrcpoc> pocsRes = inspectionDao.findByFacilityNumber((Integer) facilityId);
        pocsRes.forEach(rrcpoc -> dto.getInspections().add(
                facilityInspectionMapper.toFacilityInspectionDto(rrcpoc)));

        // TODO Implement retrieving Inspections data
        /*
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
                poc.setPocCommentCont("Comment Cont #" + j);
                poc.setPocCorrectionPlanCont("Correction Plan Cont #" + j);

                pocs.add(poc);
            }

            inspections.add(facilityInspectionDTO);
        }*/
        return dto;
    }
}
