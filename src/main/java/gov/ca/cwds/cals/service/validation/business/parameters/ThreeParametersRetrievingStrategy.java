package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
public final class ThreeParametersRetrievingStrategy<T extends BaseDTO>
    extends AbstractRetrievingParametersStrategy<T> {

  public static final ThreeParametersRetrievingStrategy INSTANCE = new ThreeParametersRetrievingStrategy();

  private ThreeParametersRetrievingStrategy() {
    super((byte) 2, (byte) 3);
  }

}
