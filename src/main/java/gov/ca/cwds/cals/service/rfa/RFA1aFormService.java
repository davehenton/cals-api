package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.SYSTEM_USER_ID;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.mapper.rfa.RFA1aFormMapper;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormService
    extends TypedCrudServiceAdapter<RFA1aFormsParameterObject, RFA1aFormDTO, RFA1aFormDTO> {

  private RFA1aFormsDao dao;
  private RFA1aFormMapper mapper;

  @Inject
  public RFA1aFormService(RFA1aFormsDao dao, RFA1aFormMapper mapper) {
    this.dao = dao;
    this.mapper = mapper;
  }

  @Override
  public RFA1aFormDTO create(RFA1aFormDTO formDTO) {

    RFA1aForm form = new RFA1aForm();
    mapper.toRFA1aForm(form, formDTO);

    LocalDateTime now = LocalDateTime.now();
    form.setCreateDateTime(now);
    form.setCreateUserId(SYSTEM_USER_ID);
    form.setUpdateDateTime(now);
    form.setUpdateUserId(SYSTEM_USER_ID);

    form = dao.create(form);

    return mapper.toRFA1aFormDTO(form);
  }

  @Override
  public RFA1aFormDTO find(RFA1aFormsParameterObject parameterObject) {
    RFA1aForm form = dao.find(parameterObject.getFormId());

    RFA1aFormDTO formDTO;
    if (parameterObject.isExpanded()) {
      formDTO = mapper.toExpandedRFA1aFormDTO(form);
    } else {
      formDTO = mapper.toRFA1aFormDTO(form);
    }

    return formDTO;
  }

  @Override
  public RFA1aFormDTO update(RFA1aFormsParameterObject parameterObject, RFA1aFormDTO formDTO) {
    RFA1aForm form = dao.find(parameterObject.getFormId());
    if (form == null) {
      throw new UserFriendlyException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    mapper.toRFA1aForm(form, formDTO);
    form.setUpdateDateTime(LocalDateTime.now());
    form.setUpdateUserId(SYSTEM_USER_ID);
    dao.update(form);
    return mapper.toRFA1aFormDTO(form);
  }
}
