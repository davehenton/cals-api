package gov.ca.cwds.cals.service.dto.rfa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import gov.ca.cwds.cals.service.BeanValidationTestSupport;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class PhoneDTOTest extends BeanValidationTestSupport<PhoneDTO> {

  @Test
  public void phoneValidExtansionValidationTest() {
    // Valid PhoneDTO
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("(555) 555-5555");
    phone.setExtension("5555555");
    Set<ConstraintViolation<PhoneDTO>> violations = validate(phone);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void phoneExtansionMoreThen7ValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("(555) 555-5555");
    // more then 7 digits
    phone.setExtension("55555555");
    Set<ConstraintViolation<PhoneDTO>> violations = validate(phone);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void phoneExtansionHasNonDigitCharsValidationTest() {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber("(555) 555-5555");
    // has nondigit char digits
    phone.setExtension("a");
    Set<ConstraintViolation<PhoneDTO>> violations = validate(phone);
    assertFalse(violations.isEmpty());
  }

}