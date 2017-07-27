package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */

public abstract class AbstractRetrievingParametersStrategy<T extends BaseDTO> implements
    RetrievingParametersStrategy<T> {

  private byte entityDtoPosition;
  private byte expectedParametersLength;

  public AbstractRetrievingParametersStrategy(byte entityDtoPosition,
      byte expectedParametersLength) {
    this.entityDtoPosition = entityDtoPosition;
    this.expectedParametersLength = expectedParametersLength;
  }

  @Override
  public BusinessValidationParameterObject<T> retrieveParameters(
      Object[] parameters, Class<T> clazz) {
    if (parameters.length != expectedParametersLength) {
      throw new IllegalStateException("Uexpected method parameters count");
    }
    ParameterReader<T> parametersReader =
        new ParameterReader<>(parameters, clazz, entityDtoPosition);
    return new BusinessValidationParameterObject<>(
        parametersReader.getFormId(), parametersReader.getEntityDto());
  }

}
