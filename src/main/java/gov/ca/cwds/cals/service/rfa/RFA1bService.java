package gov.ca.cwds.cals.service.rfa;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1bDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1bFormDTO;
import gov.ca.cwds.cals.service.mapper.RFA1aFormMapper;
import gov.ca.cwds.cals.service.mapper.RFA1bFormMapper;
import gov.ca.cwds.cals.service.rfa.factory.RFA1bFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.rest.exception.ExpectedException;
import java.util.Optional;

/**
 * @author CWDS CALS API Team
 */
public abstract class RFA1bService<D extends RFA1bDao,
    Q extends RFAExternalEntityUpdateParameterObject<RFA1bFormDTO>>
    extends AbstractRFAExternalEntityService<RFA1bForm, Q, RFA1bFormDTO> {

  @Inject
  private RFA1bFormMapper rfa1bFormMapper;

  @Inject
  private RFA1aFormsDao rfa1aFormDao;

  @Inject
  private RFA1aFormMapper rfa1aFormMapper;

  @Inject
  public RFA1bService(D dao) {
    super(dao, RFA1bFactory.INSTANCE);
  }

  @Override
  public RFA1bFormDTO create(Q request) {
    Optional.ofNullable(find(request)).ifPresent(rfa1bFormDTO -> {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.RFA_1B_FORM_ALREADY_EXISTS, BAD_REQUEST);
    });

    // Prepare RFA1bForm
    RFA1bForm rfa1bForm = beforeCreate(composeEntity(request), request);

    // Map data from RFA 1a Form
    rfa1bForm.setEntityDTO(
        rfa1bFormMapper.toRFA1bFormDTO(rfa1bForm.getEntityDTO(), getRfa1aFormDTO(request)));

    // Save to DB
    rfa1bForm = getDao().create(rfa1bForm);

    // Postprocessing
    rfa1bForm = afterCreate(rfa1bForm, request);

    return extractDTO(rfa1bForm);
  }

  /**
   * Implement this method to prepare RFA1bForm to first save.
   *
   * @param entity entity for preparation
   * @param request request's parameters
   * @return Prepared entity
   */
  protected abstract RFA1bForm beforeCreate(RFA1bForm entity, Q request);

  /**
   * Override this method if any actions need to be performed after RFA1bForm creation.
   *
   * @param createdEntity created entity
   * @param request request's parameters
   * @return created entity
   */
  protected RFA1bForm afterCreate(RFA1bForm createdEntity, Q request) {
    // do nothing
    return createdEntity;
  }

  private RFA1aFormDTO getRfa1aFormDTO(
      RFAExternalEntityUpdateParameterObject<RFA1bFormDTO> request) {
    return Optional.ofNullable(rfa1aFormDao.find(request.getFormId()))
        .map(rfa1aForm -> rfa1aFormMapper.toExpandedRFA1aFormDTO(rfa1aForm)).orElse(null);
  }

  public abstract RFA1bFormDTO find(Q params);

}
