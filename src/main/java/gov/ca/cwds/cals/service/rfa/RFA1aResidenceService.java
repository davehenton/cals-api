package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team.
 */
public class RFA1aResidenceService extends AbstractRFAInternalEntityService<ResidenceDTO> {

  private RFA1aFormsDao formsDao;

  @Inject
  public RFA1aResidenceService(RFA1aFormsDao applicationDao, RFA1aFormsDao formsDao) {
    super(
        applicationDao,
        new RFAInternalEntityConfiguration<ResidenceDTO>(ResidenceDTO.class) {

          @Override
          public ResidenceDTO getEntityFromTheForm(RFA1aForm form) {
            return form.getResidence();
          }

          @Override
          public void putEntityToTheForm(RFA1aForm form, ResidenceDTO residence) {
            form.setResidence(residence);
          }
        });
    this.formsDao = formsDao;
  }

  @Override
  public ResidenceDTO update(Long applicationId, ResidenceDTO request) {
    ResidenceDTO residenceDTO = super.update(applicationId, request);
    RFA1aForm form = formsDao.find(applicationId);
    if (form.getStatus() != RFAApplicationStatus.IN_PROGRESS) {
      form.setStatus(RFAApplicationStatus.IN_PROGRESS);
    }
    return residenceDTO;
  }

}

