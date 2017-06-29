package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFAApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import gov.ca.cwds.cals.service.rfa.configuration.ApplicantConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFAApplicantsCollectionService
    extends AbstractRFAExternalEntitiesCollectionService<Applicant, RFAApplicant, ApplicantsDTO> {

  @Inject
  public RFAApplicantsCollectionService(RFAApplicantDao dao) {
    super(dao, ApplicantConfiguration.INSTANCE);
  }

}
