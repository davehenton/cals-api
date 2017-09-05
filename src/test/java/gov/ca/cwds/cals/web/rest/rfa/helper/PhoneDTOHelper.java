package gov.ca.cwds.cals.web.rest.rfa.helper;

import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneNumberType;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;

/**
 * @author CWDS CALS API Team
 */

public final class PhoneDTOHelper {

  private PhoneDTOHelper() {
  }

  public static PhoneNumberType createPhoneNumberType() {
    return createPhoneNumberType(3L, "Work");
  }

  public static PhoneNumberType createPhoneNumberType(Long id, String value) {
    PhoneNumberType phoneType = new PhoneNumberType();
    phoneType.setId(id);
    phoneType.setValue(value);
    return phoneType;
  }

  public static PhoneDTO createPhone() {
    return createPhone("1234567890", "1234567", false, createPhoneNumberType());
  }

  public static PhoneDTO createPhoneNoExtension() {
    return createPhone("1234567890", null, false, createPhoneNumberType());
  }

  public static PhoneDTO createPhone(String number, String extension, boolean preferred,
      PhoneNumberType phoneType) {
    PhoneDTO phone = new PhoneDTO();
    phone.setNumber(number);
    phone.setExtension(extension);
    phone.setPreferred(false);
    phone.setPhoneType(phoneType);
    return phone;
  }


}
