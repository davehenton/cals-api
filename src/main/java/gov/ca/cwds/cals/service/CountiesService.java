package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.CountiesDTO;
import gov.ca.cwds.cals.service.mapper.CountyMapper;
import gov.ca.cwds.data.legacy.cms.dao.CountiesDao;
import gov.ca.cwds.data.legacy.cms.entity.County;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;

/** @author CWDS CALS API Team */
public class CountiesService extends CrudServiceAdapter {

  private CountiesDao countiesDao;
  private CountyMapper countyMapper;

  @Inject
  public CountiesService(CountiesDao countiesDao, CountyMapper countyMapper) {
    this.countiesDao = countiesDao;
    this.countyMapper = countyMapper;
  }

  @Override
  public Response find(Serializable serializable) {
    final CountiesDTO countiesDTO = new CountiesDTO();
    List<County> counties = countiesDao.findAll();
    counties.forEach(
        (County county) -> countiesDTO.getCounties().add(countyMapper.toCountyDTO(county)));
    return countiesDTO;
  }

}
