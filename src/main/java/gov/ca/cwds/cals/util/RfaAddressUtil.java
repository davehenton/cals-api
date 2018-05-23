package gov.ca.cwds.cals.util;

import gov.ca.cwds.cals.Constants.ExpectedExceptionMessages;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.rest.exception.ExpectedException;
import java.util.Optional;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Alexander Serbin on 5/22/2018.
 */
public final class RfaAddressUtil {

  private RfaAddressUtil() {
  }

  public static RFAAddressDTO getByType(RFA1aFormDTO rfa1aFormDTO, String type) {
    ResidenceDTO residence = rfa1aFormDTO.getResidence();
    if (residence == null) {
      return null;
    }
    Optional<RFAAddressDTO> address =
        residence
            .getAddresses()
            .stream()
            .filter(a -> type.equals(a.getType().getValue()))
            .findAny();
    return address.orElse(null);
  }

  public static String getStreetNumber(RFAAddressDTO addressDTO) {
    return getStreetAddressByPartIndex(addressDTO, 0);
  }

  public static String getStreetName(RFAAddressDTO addressDTO) {
    return getStreetAddressByPartIndex(addressDTO, 1);
  }

  public static String getSilentStreetName(RFAAddressDTO addressDTO) {
    try {
      return getStreetAddressByPartIndex(addressDTO, 1);
    } catch (Exception e) {
      return null;
    }
  }

  private static String getStreetAddressByPartIndex(RFAAddressDTO address, int partIndex) {
    String[] numberAndName = StringUtils.split(address.getStreetAddress(), null, 2);
    if (numberAndName.length != 2) {
      throw new ExpectedException(
          ExpectedExceptionMessages.CANNOT_PARSE_STREET_ADDRESS, Response.Status.BAD_REQUEST);
    }
    return numberAndName[partIndex];
  }

}
