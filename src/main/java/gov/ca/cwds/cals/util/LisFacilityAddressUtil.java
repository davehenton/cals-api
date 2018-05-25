package gov.ca.cwds.cals.util;

import static gov.ca.cwds.cals.Constants.NULL_STRING;

import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Alexander Serbin on 5/22/2018.
 */
public final class LisFacilityAddressUtil {

  private LisFacilityAddressUtil() {
  }

  public static boolean checkIfLisResidentialAddressIsValid(final LisFacFile lisFacFile) {
    return checkIfLisResidentialAddressIsNotBlank(lisFacFile)
        && checkIfLisResidentialAddressIsNotNullString(lisFacFile);
  }

  public static boolean checkIfLisMailAddressIsValid(final LisFacFile lisFacFile) {
    return checkIfLisMailAddressIsNotBlank(lisFacFile)
        && checkIfLisMailAddressIsNotNullString(lisFacFile);
  }

  private static boolean checkIfLisResidentialAddressIsNotBlank(final LisFacFile lisFacFile) {
    return !StringUtils.isAllBlank(
        lisFacFile.getFacResStreetAddr(),
        lisFacFile.getFacResCity(),
        lisFacFile.getFacResState(),
        lisFacFile.getFacResZipCode());
  }

  private static boolean checkIfLisResidentialAddressIsNotNullString(
      final LisFacFile lisFacFile) {
    return (StringUtils.isNotBlank(lisFacFile.getFacResStreetAddr())
        && !NULL_STRING.equalsIgnoreCase(lisFacFile.getFacResStreetAddr()))
        || (StringUtils.isNotBlank(lisFacFile.getFacResCity())
        && !NULL_STRING.equalsIgnoreCase(lisFacFile.getFacResCity()))
        || (StringUtils.isNotBlank(lisFacFile.getFacResState())
        && !NULL_STRING.equalsIgnoreCase(lisFacFile.getFacResState()))
        || (StringUtils.isNotBlank(lisFacFile.getFacResZipCode())
        && !NULL_STRING.equalsIgnoreCase(lisFacFile.getFacResZipCode()));
  }

  private static boolean checkIfLisMailAddressIsNotBlank(final LisFacFile lisFacFile) {
    return !StringUtils.isAllBlank(
        lisFacFile.getFacMailStreetAddr(),
        lisFacFile.getFacMailCity(),
        lisFacFile.getFacMailState(),
        lisFacFile.getFacMailZipCode());
  }

  private static boolean checkIfLisMailAddressIsNotNullString(final LisFacFile lisFacFile) {
    return (StringUtils.isNotBlank(lisFacFile.getFacMailStreetAddr())
        && !lisFacFile.getFacMailStreetAddr().equalsIgnoreCase(NULL_STRING))
        || (StringUtils.isNotBlank(lisFacFile.getFacMailCity())
        && !lisFacFile.getFacMailCity().equalsIgnoreCase(NULL_STRING))
        || (StringUtils.isNotBlank(lisFacFile.getFacMailState())
        && !lisFacFile.getFacMailState().equalsIgnoreCase(NULL_STRING))
        || (StringUtils.isNotBlank(lisFacFile.getFacMailState())
        && !lisFacFile.getFacMailZipCode().equalsIgnoreCase(NULL_STRING));
  }

}
