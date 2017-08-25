package gov.ca.cwds.cals.service.dto.rfa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.Constants.Validation.Constraint;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.service.BeanValidationTestSupport;

import java.util.Set;
import javax.validation.ConstraintViolation;

import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class PhoneDTOTest extends BeanValidationTestSupport<PhoneDTO> {

  @Test
  public void phoneNumberValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("1234567890");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertTrue(violations.isEmpty());
  }

  @Test
  public void phoneNumberNullValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertTrue(violations.isEmpty());
  }

  @Test
  public void phoneNumberLessThan10digitsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("123456789");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertEquals(violations.size(), 1);
    String actualMessage = violations.iterator().next().getMessage();
    String expectedMessage = getBetweenLengthMessage(10, 10);
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void phoneNumberMoreThan10digitsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("12345678901");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertEquals(violations.size(), 1);
    String actualMessage = violations.iterator().next().getMessage();
    String expectedMessage = getBetweenLengthMessage(10, 10);
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void phoneNumberNonDigitsValidationTest() {
    String number = "123456789a";
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber(number);
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertEquals(violations.size(), 1);
    String actualMessage = violations.iterator().next().getMessage();
    String expectedMessage = getNumericMessage(number);
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void phoneExtensionValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setExtension("1234567");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertTrue(violations.isEmpty());
  }

  @Test
  public void phoneExtensionNullValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertTrue(violations.isEmpty());
  }

  @Test
  public void phoneExtensionMoreThen7ValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setExtension("12345678");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertEquals(violations.size(), 1);
    String actualMessage = violations.iterator().next().getMessage();
    String expectedMessage = getBetweenLengthMessage(0, 7);
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void phoneExtensionHasNonDigitCharsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    String extension = "a";
    phone.setExtension(extension);
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertEquals(violations.size(), 1);
    String actualMessage = violations.iterator().next().getMessage();
    String expectedMessage = getNumericMessage(extension);
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  public void phoneTypeValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertTrue(violations.isEmpty());
  }

  @Test
  public void phoneTypeIsNullValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    Set<ConstraintViolation<PhoneDTO>> violations = validate(phone);
    assertEquals(violations.size(), 1);
    String actualMessage = violations.iterator().next().getMessage();
    assertEquals(Constraint.NOT_NULL_MESSAGE, actualMessage);
  }
}