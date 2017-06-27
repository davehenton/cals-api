package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aMinorChildDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.MinorChild;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.MinorChildrenDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aMinorChildrenParameterObject;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aMinorChildrenCollectionService extends CrudServiceAdapter {

  private RFA1aMinorChildDao dao;

  @Inject
  public RFA1aMinorChildrenCollectionService(RFA1aMinorChildDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    if (!(params instanceof RFA1aMinorChildrenParameterObject)) {
      throw new IllegalStateException("RFA1aMinorChildrenParameterObject is expected here");
    }
    List<RFA1aMinorChild> applicants =
        dao.findAllByFormId(((RFA1aMinorChildrenParameterObject) params).getApplicationId());
    List<MinorChild> collectDTOs =
        applicants.stream().map(RFA1aMinorChild::getMinorChild).collect(Collectors.toList());
    return new MinorChildrenDTO(collectDTOs);
  }
}
