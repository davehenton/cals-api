package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aApplicantParameterObject;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantsCollectionService extends CrudServiceAdapter {

  private RFA1aApplicantDao dao;

  @Inject
  public RFA1aApplicantsCollectionService(RFA1aApplicantDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    if (!(params instanceof RFA1aApplicantParameterObject)) {
      throw new IllegalStateException("RFA1aApplicantParameterObject is expected here");
    }
    List<RFA1aApplicant> applicants =
        dao.findAllByFormId(((RFA1aApplicantParameterObject) params).getFormId());
    List<Applicant> collectDTOs =
        applicants
            .stream()
            .map(RFA1aApplicant::getApplicant)
            .collect(Collectors.toList());
    return new ApplicantsDTO(collectDTOs);
  }
}
