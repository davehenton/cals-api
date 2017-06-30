package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsRelationship;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team.
 */
public class RFA1aApplicantsRelationshipService
    extends AbstractRFAInternalEntityService<ApplicantsRelationship> {

  @Inject
  public RFA1aApplicantsRelationshipService(RFA1aFormsDao applicationDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<ApplicantsRelationship>(ApplicantsRelationship.class) {

          @Override
          protected ApplicantsRelationship retrieveEntityFromTheForm(RFA1aForm form) {
            return form.getRelationships();
          }

          @Override
          protected void saveEntityToTheForm(RFA1aForm form, ApplicantsRelationship relationship) {
            form.setRelationships(relationship);
          }

        });
  }
}
