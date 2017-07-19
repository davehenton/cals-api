package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.SYSTEM_USER_ID;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import gov.ca.cwds.cals.persistence.dao.calsns.RFAExternalEntityDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAExternalEntityFactory;
import gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team.
 */
public abstract class AbstractRFAExternalEntityService<
    T extends RFAExternalEntity<D>, D extends RFAExternalEntityDTO>
    extends TypedCrudServiceAdapter<
    RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<D>, D> {

  private RFAExternalEntityDao<T, D> dao;
  private RFAExternalEntityFactory<T, D> configuration;

  public AbstractRFAExternalEntityService(
      RFAExternalEntityDao<T, D> dao,
      RFAExternalEntityFactory<T, D> configuration) {
    this.dao = dao;
    this.configuration = configuration;
  }

  @Override
  public D create(RFAExternalEntityUpdateParameterObject<D> request) {
    D entityDTO = request.getEntityDTO();

    if (entityDTO == null) {
      entityDTO = configuration.createEntityDTO();
    }

    T entity = configuration.createEntity();

    RFAServiceHelper.fillCreateBaseFields(entity, SYSTEM_USER_ID);

    entity.setEntityDTO(entityDTO);
    entity.setFormId(request.getFormId());
    entity = dao.create(entity);
    entityDTO = entity.getEntityDTO();
    entityDTO.setId(entity.getId());
    return entityDTO;
  }

  @Override
  public D find(RFAExternalEntityGetParameterObject params) {
    T entity =
        dao.findEntityByFormIdAndEntityId(
            params.getFormId(), params.getEntityId());
    if (entity == null) {
      throw new UserFriendlyException(
          CalsExceptionInfo.RFA_1A_APPLICANT_NOT_FOUND_BY_ID, NOT_FOUND);
    } else {
      D entityDto = entity.getEntityDTO();
      entityDto.setId(entity.getId());
      return entityDto;
    }
  }

  @Override
  public D delete(RFAExternalEntityGetParameterObject params) {
    T entity =
        dao.deleteEntity(params.getFormId(), params.getEntityId());
    D deleted = null;
    if (entity != null) {
      deleted = entity.getEntityDTO();
    }
    return deleted;
  }

  @Override
  public D update(RFAExternalEntityGetParameterObject params,
      RFAExternalEntityUpdateParameterObject<D> request) {
    T entity = dao.findEntityByFormIdAndEntityId(params.getFormId(), params.getEntityId());
    if (entity != null) {
      entity.setEntityDTO(request.getEntityDTO());
      entity.setUpdateDateTime(LocalDateTime.now());
      entity.setUpdateUserId(SYSTEM_USER_ID);
    }
    T updated = dao.update(entity);
    D entityDTO = null;
    // Update RFA 1a Form
    if (updated != null) {
      entityDTO = updated.getEntityDTO();
      entityDTO.setId(updated.getId());
    }

    return entityDTO;
  }

}
