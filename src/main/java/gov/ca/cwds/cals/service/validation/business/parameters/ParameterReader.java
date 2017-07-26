package gov.ca.cwds.cals.service.validation.business.parameters;

import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */

public class ParameterReader<T extends BaseDTO> {

  private final Object[] parameters;
  private Class<T> clazz;
  private byte entityDtoPosition;

  public ParameterReader(Object[] parameters, Class<T> clazz, byte entityDtoPosition) {
    this.parameters = parameters;
    this.clazz = clazz;
    this.entityDtoPosition = entityDtoPosition;
  }

  public Long getFormId() {
    if (!(parameters[0] instanceof Long)) {
      throw new IllegalArgumentException("Illegal argument type");
    }
    return (Long) parameters[0];
  }

  public T getEntityDto() {
    if (!(parameters[entityDtoPosition].getClass().isAssignableFrom(clazz))) {
      throw new IllegalArgumentException("Illegal argument type");
    }
    return (T) parameters[entityDtoPosition];
  }

}

