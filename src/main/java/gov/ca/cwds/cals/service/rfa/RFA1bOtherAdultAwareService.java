package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aOtherAdultDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bOtherAdultAwareDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.mapper.RFA1bFormMapper;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAOtherAdultAwareEntityUpdateParams;
import java.util.Optional;

/**
 * @author CWDS TPT-2 Team.
 */
public class RFA1bOtherAdultAwareService extends
    RFA1bService<RFA1bOtherAdultAwareDao, RFAOtherAdultAwareEntityUpdateParams<RFA1bFormDTO>> {

  @Inject
  private RFA1aOtherAdultDao otherAdultDao;

  @Inject
  private RFA1bFormMapper rfa1bFormMapper;

  @Inject
  public RFA1bOtherAdultAwareService(RFA1bOtherAdultAwareDao dao) {
    super(dao);
  }

  @Override
  protected RFA1bForm beforeCreate(RFA1bForm entity,
      RFAOtherAdultAwareEntityUpdateParams<RFA1bFormDTO> request) {
    RFA1aOtherAdult otherAdult = findOtherAdultById(request.getOtherAdultId());
    entity.setOtherAdult(otherAdult);
    entity.setEntityDTO(
        rfa1bFormMapper.toRFA1bFormDTO(entity.getEntityDTO(), otherAdult.getEntityDTO()));
    return entity;
  }

  @Override
  public RFA1bFormDTO find(RFAExternalEntityGetParameterObject params) {
    return find(params.getEntityId());
  }

  @Override
  public RFA1bFormDTO find(RFAOtherAdultAwareEntityUpdateParams<RFA1bFormDTO> params) {
    return find(params.getOtherAdultId());
  }

  private RFA1bFormDTO find(Long otherAdultId) {
    return Optional.ofNullable(findOtherAdultById(otherAdultId))
        .map(otherAdult -> extractDTO(otherAdult.getRfa1bForm())).orElse(null);
  }

  private RFA1aOtherAdult findOtherAdultById(Long otherAdultId) {
    return otherAdultDao.find(otherAdultId);
  }

}
