package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsHistoryDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team.
 */
public class RFA1aApplicantsHistoryService
    extends AbstractRFAInternalEntityService<ApplicantsHistoryDTO> {

  @Inject
  public RFA1aApplicantsHistoryService(RFA1aFormsDao applicationDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<ApplicantsHistoryDTO>(ApplicantsHistoryDTO.class) {

          @Override
          public ApplicantsHistoryDTO getEntityFromTheForm(RFA1aForm form) {
            return form.getApplicantsHistory();
          }

          @Override
          public void putEntityToTheForm(RFA1aForm form,
              ApplicantsHistoryDTO applicantsHistory) {
            form.setApplicantsHistory(applicantsHistory);
          }

        });
  }
}
