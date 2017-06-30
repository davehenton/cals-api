package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import gov.ca.cwds.cals.service.rfa.factory.ApplicantFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantsCollectionService
    extends AbstractRFAExternalEntitiesCollectionService<RFA1aApplicant, Applicant, ApplicantsDTO> {

  @Inject
  public RFA1aApplicantsCollectionService(RFA1aApplicantDao dao) {
    super(dao, ApplicantFactory.INSTANCE);
  }

}
