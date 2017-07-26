package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */

public abstract class AbstractRetrievingParametersStrategy<T extends BaseDTO> implements
    RetrievingParametersStrategy<T> {

  private byte entityDtoPosition;

  public AbstractRetrievingParametersStrategy(byte entityDtoPosition) {
    this.entityDtoPosition = entityDtoPosition;
  }

  @Override
  public BusinessValidationParameterObject<T> retrieveParameters(
      Object[] parameters, Class<T> clazz) {
    ParameterReader<T> parametersReader =
        new ParameterReader<>(parameters, clazz, entityDtoPosition);
    return new BusinessValidationParameterObject<>(
        parametersReader.getFormId(), parametersReader.getEntityDto());
  }

}
