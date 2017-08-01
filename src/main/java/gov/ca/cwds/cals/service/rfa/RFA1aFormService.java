package gov.ca.cwds.cals.service.rfa;

import static exception.ExpectedExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID;
import static exception.ExpectedExceptionInfo.TRANSITION_BACK_TO_DRAFT_IS_NOT_ALLOWED;
import static gov.ca.cwds.cals.Constants.SYSTEM_USER_ID;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import exception.ExpectedException;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.mapper.rfa.RFA1aFormMapper;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormService
    extends TypedCrudServiceAdapter<RFA1aFormsParameterObject, RFA1aFormDTO, RFA1aFormDTO> {

  private static final Logger LOG = LoggerFactory
      .getLogger(RFA1aFormService.class);

  private RFA1aFormsDao dao;
  private RFA1aFormMapper rfa1aFomMapper;
  private FacilityService facilityService;

  @Inject
  public RFA1aFormService(
      RFA1aFormsDao dao, RFA1aFormMapper rfa1aFomMapper, FacilityService facilityService) {
    this.dao = dao;
    this.rfa1aFomMapper = rfa1aFomMapper;
    this.facilityService = facilityService;
  }

  @Override
  public RFA1aFormDTO create(RFA1aFormDTO formDTO) {

    RFA1aForm form = new RFA1aForm();
    rfa1aFomMapper.toRFA1aForm(form, formDTO);

    LocalDateTime now = LocalDateTime.now();
    form.setCreateDateTime(now);
    form.setCreateUserId(SYSTEM_USER_ID);
    form.setUpdateDateTime(now);
    form.setUpdateUserId(SYSTEM_USER_ID);
    form.setStatus(RFAApplicationStatus.DRAFT);
    form = dao.create(form);

    return rfa1aFomMapper.toRFA1aFormDTO(form);
  }

  @Override
  public RFA1aFormDTO find(RFA1aFormsParameterObject parameterObject) {
    RFA1aForm form = dao.find(parameterObject.getFormId());

    RFA1aFormDTO formDTO;
    if (parameterObject.isExpanded()) {
      formDTO = rfa1aFomMapper.toExpandedRFA1aFormDTO(form);
    } else {
      formDTO = rfa1aFomMapper.toRFA1aFormDTO(form);
    }

    return formDTO;
  }

  @Override
  public RFA1aFormDTO update(RFA1aFormsParameterObject parameterObject, RFA1aFormDTO formDTO) {
    RFA1aForm form = findFormById(
        parameterObject.getFormId());
    rfa1aFomMapper.toRFA1aForm(form, formDTO);
    updateForm(form);
    return rfa1aFomMapper.toRFA1aFormDTO(form);
  }

  private void updateForm(RFA1aForm form) {
    form.setUpdateDateTime(LocalDateTime.now());
    form.setUpdateUserId(SYSTEM_USER_ID);
    dao.update(form);
  }

  public RFAApplicationStatusDTO getApplicationStatus(Long formId) {
    RFA1aForm form = findFormById(formId);
    return new RFAApplicationStatusDTO(form.getStatus());
  }

  public void setApplicationStatus(Long formId, RFAApplicationStatusDTO statusDTO) {
    RFA1aForm form = findFormById(formId);
    RFAApplicationStatus newStatus = statusDTO.getStatus();
    if (form.getStatus() != newStatus) {
      if (form.getStatus() == RFAApplicationStatus.SUBMITTED
          && newStatus == RFAApplicationStatus.DRAFT) {
        throw new ExpectedException(TRANSITION_BACK_TO_DRAFT_IS_NOT_ALLOWED, BAD_REQUEST);
      }
      submitApplication(form, newStatus);
    }
  }

  private void submitApplication(RFA1aForm form, RFAApplicationStatus newStatus) {
    form.setStatus(newStatus);
    form.setPlacementHomeId(Utils.Id.generate());
    PlacementHome placementHome;
    try {
      placementHome = facilityService.createPlacementHomeByRfaApplication(form);
    } catch (Exception e) {
      LOG.error("Can not create Placement Home in database", e);
      throw e;
    }
    form.setPlacementHomeId(placementHome.getIdentifier());
    updateForm(form);
  }

  private RFA1aForm findFormById(Long formId) {
    RFA1aForm form = dao.find(formId);
    if (form == null) {
      throw new ExpectedException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    return form;
  }

}
