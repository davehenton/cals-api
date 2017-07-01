package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsHistory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team.
 */
public class RFA1aApplicantsHistoryService
    extends AbstractRFAInternalEntityService<ApplicantsHistory> {

  @Inject
  public RFA1aApplicantsHistoryService(RFA1aFormsDao applicationDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<ApplicantsHistory>(ApplicantsHistory.class) {

          @Override
          protected ApplicantsHistory retrieveEntityFromTheForm(RFA1aForm form) {
            return form.getApplicantsHistory();
          }

          @Override
          protected void saveEntityToTheForm(RFA1aForm form, ApplicantsHistory applicantsHistory) {
            form.setApplicantsHistory(applicantsHistory);
          }

        });
  }
}
