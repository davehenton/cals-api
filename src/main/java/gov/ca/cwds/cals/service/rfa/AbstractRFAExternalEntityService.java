package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.dao.calsns.RFAExternalEntityDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAExternalEntityFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityGetParameterObject;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityUpdateParameterObject;
import gov.ca.cwds.rest.exception.ExpectedException;
import gov.ca.cwds.security.utils.PrincipalUtils;

import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author CWDS CALS API Team.
 */
public abstract class AbstractRFAExternalEntityService<
    T extends RFAExternalEntity<D>, D extends RFAExternalEntityDTO>
    extends TypedCrudServiceAdapter<
    RFAExternalEntityGetParameterObject, RFAExternalEntityUpdateParameterObject<D>, D> {

  private RFAExternalEntityDao<T> dao;
  private RFAExternalEntityFactory<T, D> configuration;

  public AbstractRFAExternalEntityService(
      RFAExternalEntityDao<T> dao,
      RFAExternalEntityFactory<T, D> configuration) {
    this.dao = dao;
    this.configuration = configuration;
  }

  @Override
  public D create(RFAExternalEntityUpdateParameterObject<D> request) {
    T entity = composeEntity(request);
    entity = dao.create(entity);
    return extractDTO(entity);
  }

  protected D extractDTO(T entity) {
    if (entity == null) {
      return null;
    }
    D entityDTO = entity.getEntityDTO();
    entityDTO.setId(entity.getId());
    return entityDTO;
  }

  protected T composeEntity(RFAExternalEntityUpdateParameterObject<D> request) {
    D entityDTO = request.getEntityDTO();
    if (entityDTO == null) {
      entityDTO = configuration.createEntityDTO();
    }
    T entity = configuration.createEntity();
    RFAServiceHelper.fillCreateBaseFields(entity, PrincipalUtils.getStaffPersonId());
    entity.setEntityDTO(entityDTO);
    entity.setFormId(request.getFormId());
    return entity;
  }

  @Override
  public D find(RFAExternalEntityGetParameterObject params) {
    T entity =
        dao.findEntityByFormIdAndEntityId(
            params.getFormId(), params.getEntityId());
    if (entity == null) {
      throw new ExpectedException(
          Constants.ExpectedExceptionMessages.RFA_1A_APPLICANT_NOT_FOUND_BY_ID, NOT_FOUND);
    } else {
      return extractDTO(entity);
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
      entity.setUpdateUserId(PrincipalUtils.getStaffPersonId());
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

  public RFAExternalEntityDao<T> getDao() {
    return dao;
  }
}
