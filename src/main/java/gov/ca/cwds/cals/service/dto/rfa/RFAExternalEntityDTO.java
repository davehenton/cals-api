package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2160") //reflection equals hashcode is used in superclass
public class RFAExternalEntityDTO extends BaseDTO implements Request, Response {

  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
