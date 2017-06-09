package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.AgeGroupTypeDao;
import gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType;
import gov.ca.cwds.cals.service.dto.AgeGroupTypesDTO;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import java.io.Serializable;
import java.util.List;

/** @author CWDS CALS API Team */
public class AgeGroupTypesService extends CrudServiceAdapter implements CrudsService {

  private AgeGroupTypeDao dao;

  @Inject
  public AgeGroupTypesService(AgeGroupTypeDao dao) {
    this.dao = dao;
  }

  @Override
  public Response find(Serializable params) {
    AgeGroupTypesDTO ageGroupTypesDTO = new AgeGroupTypesDTO();
    List<AgeGroupType> types = dao.findAll();
    ageGroupTypesDTO.setAgeGroupTypes(types);
    return ageGroupTypesDTO;
  }
}
