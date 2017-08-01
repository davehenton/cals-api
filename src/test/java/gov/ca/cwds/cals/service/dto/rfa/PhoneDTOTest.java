package gov.ca.cwds.cals.service.dto.rfa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    assertFalse(violations.isEmpty());
  }

  @Test
  public void phoneNumberMoreThan10digitsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("12345678901");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertFalse(violations.isEmpty());
  }

  @Test
  public void phoneNumberNonDigitsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("123456789a");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertFalse(violations.isEmpty());
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
    assertFalse(violations.isEmpty());
  }

  @Test
  public void phoneExtensionHasNonDigitCharsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setExtension("a");
    phone.setPhoneType(new PhoneNumberType());
    Set<ConstraintViolation<PhoneDTO>> violations = removeDbSessionViolation(validate(phone));
    assertFalse(violations.isEmpty());
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
    assertFalse(violations.isEmpty());
  }
}