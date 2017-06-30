package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import gov.ca.cwds.cals.service.rfa.configuration.ApplicantFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityParameterObject;

/**
 * @author CWDS CALS API Team
 */
public class RFAApplicantService
    extends AbstractRFAExternalEntityService<
    RFA1aApplicant, Applicant, RFAExternalEntityParameterObject<Applicant>, ApplicantsDTO> {

  @Inject
  public RFAApplicantService(RFA1aApplicantDao dao) {
    super(dao, ApplicantFactory.INSTANCE);
  }

}
