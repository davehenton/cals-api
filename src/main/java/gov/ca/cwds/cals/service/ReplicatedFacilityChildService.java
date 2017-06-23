package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.rs.ReplicatedClientDAO;
import gov.ca.cwds.cals.service.dto.rs.ReplicatedFacilityChildDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import java.util.stream.Stream;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedFacilityChildService extends CrudServiceAdapter {

  private ReplicatedClientDAO clientDao;
  private FacilityChildMapper facilityChildMapper;

  // todo tests, sonar

  @Inject
  public ReplicatedFacilityChildService(
      ReplicatedClientDAO clientDao,
      FacilityChildMapper facilityChildMapper) {
    this.clientDao = clientDao;
    this.facilityChildMapper = facilityChildMapper;
  }

  public Stream<ReplicatedFacilityChildDTO> childrenStream(
      FacilityChildParameterObject parameterObject) {
    return clientDao.stream(parameterObject)
        .map(cient -> facilityChildMapper.toReplicatedFacilityChildDTO(cient));
  }
}
