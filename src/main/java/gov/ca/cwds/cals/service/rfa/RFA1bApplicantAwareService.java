package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aApplicantDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bApplicantAwareDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.mapper.RFA1bFormMapper;
import gov.ca.cwds.cals.web.rest.parameter.RFAApplicantAwareEntityUpdateParams;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import java.util.Optional;

/**
 * @author CWDS TPT-2 Team.
 */
public class RFA1bApplicantAwareService extends
    RFA1bService<RFA1bApplicantAwareDao, RFAApplicantAwareEntityUpdateParams<RFA1bFormDTO>> {

  @Inject
  private RFA1aApplicantDao applicantDao;

  @Inject
  private RFA1bFormMapper rfa1bFormMapper;

  @Inject
  public RFA1bApplicantAwareService(RFA1bApplicantAwareDao dao) {
    super(dao);
  }

  @Override
  protected RFA1bForm beforeCreate(RFA1bForm entity,
      RFAApplicantAwareEntityUpdateParams<RFA1bFormDTO> request) {
    RFA1aApplicant applicant = findApplicantById(request.getApplicantId());
    entity.setApplicant(applicant);
    entity.setEntityDTO(
        rfa1bFormMapper.toRFA1bFormDTO(entity.getEntityDTO(), applicant.getEntityDTO()));

    return entity;
  }

  @Override
  public RFA1bFormDTO find(RFAExternalEntityGetParameterObject params) {
    return find(params.getEntityId());
  }

  @Override
  public RFA1bFormDTO find(RFAApplicantAwareEntityUpdateParams<RFA1bFormDTO> params) {
    return find(params.getApplicantId());
  }

  private RFA1bFormDTO find(Long applicantId) {
    return Optional.ofNullable(findApplicantById(applicantId))
        .map(applicant -> extractDTO(applicant.getRfa1bForm())).orElse(null);
  }

  private RFA1aApplicant findApplicantById(Long applicantId) {
    return applicantDao.find(applicantId);
  }
}
