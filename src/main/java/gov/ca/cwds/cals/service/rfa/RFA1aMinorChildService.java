package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_USER_ID;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aMinorChildDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aMinorChildrenParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildService extends CrudServiceAdapter {

  private RFA1aMinorChildDao dao;
  private RFA1aFormService formService;

  @Inject
  public RFA1aMinorChildService(RFA1aMinorChildDao dao, RFA1aFormService formService) {
    this.dao = dao;
    this.formService = formService;
  }

  @Override
  public Response create(Request request) {
    RFA1aMinorChildrenParameterObject paramObject = getParamObject(request);
    MinorChild minorChild = paramObject.getMinorChild();
    if (minorChild == null) {
      minorChild = new MinorChild();
    }
    RFA1aMinorChild entity = new RFA1aMinorChild();
    RFA1aServiceHelper.fillCreateBaseFields(entity, SYSTEM_USER_ID);
    entity.setApplicationId(paramObject.getApplicationId());
    entity.setMinorChild(minorChild);

    entity = dao.create(entity);

    minorChild = entity.getMinorChild();
    minorChild.setId(entity.getId());

    return minorChild;
  }

  @Override
  public Response find(Serializable params) {
    RFA1aMinorChildrenParameterObject paramObject = getParamObject(params);
    RFA1aMinorChild entity =
        dao.findMinorChildByFormIdAndMinorChildId(
            paramObject.getApplicationId(), paramObject.getMinorChildId());
    MinorChild minorChild = null;
    if (entity != null) {
      minorChild = entity.getMinorChild();
    }
    return minorChild;
  }

  @Override
  public Response update(Serializable minorChildId, Request params) {
    final RFA1aMinorChildrenParameterObject paramsObj = getParamObject(params);
    RFA1aMinorChild minorChildEntity;

    if (minorChildId != null && minorChildId instanceof Long) {
      minorChildEntity =
          dao.findMinorChildByFormIdAndMinorChildId(
              paramsObj.getApplicationId(), (Long) minorChildId);
    } else {
      throw new IllegalArgumentException(
          "minorChildId expected as a Long value but got: " + minorChildId);
    }

    RFA1aMinorChild updated = null;
    if (minorChildEntity != null) {
      minorChildEntity.setMinorChild(paramsObj.getMinorChild());
      minorChildEntity.setUpdateDateTime(LocalDateTime.now());
      minorChildEntity.setUpdateUserId(SYSTEM_USER_ID);
      updated = dao.update(minorChildEntity);
    }

    MinorChild minorChild = null;
    // Update RFA 1a Form
    if (updated != null) {
      formService.update(paramsObj.getApplicationId());
      minorChild = updated.getMinorChild();
      minorChild.setId(updated.getId());
    }

    return minorChild;
  }

  @Override
  public Response delete(Serializable params) {
    RFA1aMinorChildrenParameterObject paramObject = getParamObject(params);
    RFA1aMinorChild entity =
        dao.deleteMinor(paramObject.getApplicationId(), paramObject.getMinorChildId());
    MinorChild deleted = null;
    if (entity != null) {
      deleted = entity.getMinorChild();
    }
    return deleted;
  }

  private RFA1aMinorChildrenParameterObject getParamObject(Object params) {
    if (!(params instanceof RFA1aMinorChildrenParameterObject)) {
      throw new IllegalStateException("RFA1aMinorChildrenParameterObject is expected here");
    }
    return (RFA1aMinorChildrenParameterObject) params;
  }
}
