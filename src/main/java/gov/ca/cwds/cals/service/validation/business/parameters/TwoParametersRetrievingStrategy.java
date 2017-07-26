package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
public final class TwoParametersRetrievingStrategy implements RetrievingParametersStrategy {

  public static final RetrievingParametersStrategy INSTANCE = new TwoParametersRetrievingStrategy();

  private TwoParametersRetrievingStrategy() {
  }

  public <T extends BaseDTO> BusinessValidationParameterObject<T> retrieveParameters(
      Object[] parameters, Class<T> clazz) {
    if (!(parameters[0] instanceof Long) ||
        !(parameters[1].getClass().isAssignableFrom(clazz))) {
      throw new IllegalArgumentException(
          "Illegal method signature");
    }
    return new BusinessValidationParameterObject<>((Long) parameters[0], (T) parameters[1]);
  }
}
