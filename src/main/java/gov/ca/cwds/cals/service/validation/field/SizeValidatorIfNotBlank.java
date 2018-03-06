package gov.ca.cwds.cals.service.validation.field;


import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidatorIfNotBlank implements ConstraintValidator<SizeIfNotBlank, CharSequence> {


  private int min;
  private int max;

  @Override
  public void initialize(SizeIfNotBlank parameters) {
    min = parameters.min();
    max = parameters.max();
    validateParameters();
  }

  /**
   * Checks the length of the specified character sequence (e.g. string).
   *
   * @param charSequence               The character sequence to validate.
   * @param constraintValidatorContext context in which the constraint is evaluated.
   * @return Returns <code>true</code> if the string is <code>null</code> or the length of <code>charSequence</code> between the specified
   * <code>min</code> and <code>max</code> values (inclusive), <code>false</code> otherwise.
   */
  @Override
  public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
    if (StringUtils.isBlank(charSequence)) {
      return true;
    }
    int length = charSequence.length();
    return length >= min && length <= max;
  }

  private void validateParameters() {
    if (min < 0) {
      throw new IllegalArgumentException("The min parameter cannot be negative.");
    }
    if (max < 0) {
      throw new IllegalArgumentException("The max parameter cannot be negative.");
    }
    if (max < min) {
      throw new IllegalArgumentException("The length cannot be negative.");
    }
  }
}
