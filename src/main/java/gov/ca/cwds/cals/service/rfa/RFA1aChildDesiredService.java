package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team.
 */
public class RFA1aChildDesiredService
    extends AbstractRFAInternalEntityService<ChildDesiredDTO> {

  @Inject
  public RFA1aChildDesiredService(RFA1aFormsDao applicationDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<ChildDesiredDTO>(ChildDesiredDTO.class) {

          @Override
          public ChildDesiredDTO getEntityFromTheForm(RFA1aForm form) {
            return form.getChildDesired();
          }

          @Override
          public void putEntityToTheForm(RFA1aForm form, ChildDesiredDTO childDesired) {
            form.setChildDesired(childDesired);
          }

        });
  }
}
