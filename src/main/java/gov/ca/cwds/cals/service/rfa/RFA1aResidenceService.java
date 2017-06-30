package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Residence;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team.
 */
public class RFA1aResidenceService extends AbstractRFAInternalEntityService<Residence> {

  @Inject
  public RFA1aResidenceService(RFA1aFormsDao applicationDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<Residence>(Residence.class) {

          @Override
          protected Residence retrieveEntityFromTheForm(RFA1aForm form) {
            return form.getResidence();
          }

          @Override
          protected void saveEntityToTheForm(RFA1aForm form, Residence residence) {
            form.setResidence(residence);
          }
        });
  }
}
