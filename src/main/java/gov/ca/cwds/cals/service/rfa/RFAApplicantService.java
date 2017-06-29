package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFAApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import gov.ca.cwds.cals.service.rfa.configuration.ApplicantConfiguration;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityParameterObject;

/**
 * @author CWDS CALS API Team
 */
public class RFAApplicantService
    extends AbstractRFAExternalEntityService<
    RFAApplicant, Applicant, RFAExternalEntityParameterObject<Applicant>, ApplicantsDTO> {

  @Inject
  public RFAApplicantService(RFAApplicantDao dao) {
    super(dao, ApplicantConfiguration.INSTANCE);
  }

}
