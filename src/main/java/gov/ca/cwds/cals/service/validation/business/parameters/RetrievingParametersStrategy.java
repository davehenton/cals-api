package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */

@FunctionalInterface
public interface RetrievingParametersStrategy<T extends BaseDTO> {

  BusinessValidationParameterObject<T> retrieveParameters(Object[] parameters,
      Class<T> cazz);

}
