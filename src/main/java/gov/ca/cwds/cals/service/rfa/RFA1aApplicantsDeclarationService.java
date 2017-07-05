package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantsDeclaration;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantsDeclarationService
    extends AbstractRFAInternalEntityService<ApplicantsDeclaration> {

  @Inject
  public RFA1aApplicantsDeclarationService(RFA1aFormsDao applicationDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<ApplicantsDeclaration>(ApplicantsDeclaration.class) {
          @Override
          protected ApplicantsDeclaration retrieveEntityFromTheForm(RFA1aForm form) {
            return form.getApplicantsDeclaration();
          }

          @Override
          protected void saveEntityToTheForm(RFA1aForm form, ApplicantsDeclaration entity) {
            form.setApplicantsDeclaration(entity);
          }
        });
  }
}
