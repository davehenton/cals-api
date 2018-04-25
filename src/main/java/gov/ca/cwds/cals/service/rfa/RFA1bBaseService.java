package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bApplicantAwareDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import java.util.Optional;

/**
 * Base RFA1b form service with no awareness of external entities.
 * <p>
 *   There is no create functionality because RFA1b should be linked with Applicant or OtherAdult.
 * </p>
 *
 * @author CWDS TPT-2 Team.
 */
public class RFA1bBaseService extends
    RFA1bService<RFA1bApplicantAwareDao, RFAExternalEntityUpdateParameterObject<RFA1bFormDTO>> {

  @Inject
  public RFA1bBaseService(RFA1bApplicantAwareDao dao) {
    super(dao);
  }

  @Override
  public RFA1bFormDTO create(RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> request) {
    throw new UnsupportedOperationException(
        "Create operation is not supported in RFA1bBaseService, please use other implementations");
  }

  @Override
  protected RFA1bForm beforeCreate(RFA1bForm entity,
      RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> request) {
    // Do nothing
    return entity;
  }

  @Override
  public RFA1bFormDTO find(RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> params) {
    Long formId = Optional.ofNullable(params.getFormId())
        .orElseThrow(() -> new IllegalArgumentException("Form id must not be null"));

    Long entityId = Optional.ofNullable(Optional.ofNullable(params.getEntityDTO())
        .orElseThrow(() -> new IllegalArgumentException("Entity DTO must not be null")).getId())
        .orElseThrow(() -> new IllegalArgumentException("Entity id must not be null"));

    return super.find(new RFAExternalEntityGetParameterObject(formId, entityId));
  }

}
