package gov.ca.cwds.cals.util;

import com.google.common.base.Objects;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.ExpectedExceptionMessages;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.*;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.rest.exception.ExpectedException;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static gov.ca.cwds.cals.Constants.NULL_STRING;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

/**
 * @author CALS API Team
 */
public final class Utils {

  private Utils() {
  }

  public static FacilityParameterObject createFacilityParameterObject(String facilityNumber) {
    FacilityParameterObject parameterObject;
    try {
      Integer licenseNumber = Integer.valueOf(facilityNumber);
      parameterObject = new FacilityParameterObject(licenseNumber, LIS);
    } catch (NumberFormatException e) {
      parameterObject = new FacilityParameterObject(facilityNumber, CMS);
    }
    return parameterObject;
  }

  public static FacilityParameterObject createExpandedFacilityParameterObject(String facilityNumber) {
    FacilityParameterObject parameterObject = createFacilityParameterObject(facilityNumber);
    parameterObject.setExpanded(Boolean.TRUE);
    return parameterObject;
  }

  public static class Phone {

    private Phone() {
    }

    public static String formatNumber(PhoneDTO phone) {
      String number = phone.getNumber();
      if (phone.getExtension() != null) {
        number = number + " ext. " + phone.getExtension();
      }
      return number;
    }

    public static boolean checkIfPhoneDTOIsValid(PersonPhoneDTO phone) {
      return (null != phone && StringUtils.isNotBlank(phone.getNumber()) && !phone.getNumber()
          .equalsIgnoreCase("null"));
    }
  }

  public static class PlacementHome {

    public static final String WATER_BODY = "water body";
    public static final String WEAPON_IN_HOME_BODY = "weapon in home";

    private PlacementHome() {
    }

    public static String getHazardsDescription(ResidenceDTO residence) {
      if (residence.isWeaponInHome() && residence.isBodyOfWaterExist()) {
        return WATER_BODY + ", " + WEAPON_IN_HOME_BODY;
      }
      if (residence.isWeaponInHome() && !residence.isBodyOfWaterExist()) {
        return WEAPON_IN_HOME_BODY;
      }
      if (!residence.isWeaponInHome() && residence.isBodyOfWaterExist()) {
        return WATER_BODY;
      }
      return " ";
    }

  }

  public static class Applicant {

    private Applicant() {
    }

    public static BigDecimal getAnnualIncome(ApplicantDTO applicant) {
      if (applicant.getEmployment() != null) {
        EmploymentDTO employmentDTO = applicant.getEmployment();
        return employmentDTO.getIncome() != null
            && employmentDTO.getIncomeType() != null
            && "monthly".equals(employmentDTO.getIncomeType().getValue())
            ? employmentDTO.getIncome().multiply(new BigDecimal(12))
            : employmentDTO.getIncome() != null ? employmentDTO.getIncome() : new BigDecimal(0);
      }
      return new BigDecimal(0);
    }

    public static String getCaliforniaDriverLicense(ApplicantDTO applicant, String defaultValue) {
      if (applicant.getDriverLicenseState() != null) {
        String stateId = applicant.getDriverLicenseState().getId();
        if (Constants.StateTypes.CALIFORNIA_STATE_ID.equals(stateId)) {
          return applicant.getDriverLicenseNumber();
        }
      }
      return defaultValue;
    }

    public static String getFirstLastName(ApplicantDTO applicant) {
      return StringUtils
          .joinWith(Constants.SPACE, applicant.getFirstName(), applicant.getLastName());
    }

    public static ApplicantDTO getPrimary(RFA1aFormDTO form) {
      List<ApplicantDTO> applicants = form.getApplicants();
      if (applicants.isEmpty()) {
        return null;
      }
      ApplicantDTO primaryApplicant = applicants.get(0);
      for (ApplicantDTO applicant : applicants) {
        Long id = applicant.getId();
        if (primaryApplicant.getId() > id) {
          primaryApplicant = applicant;
        }
      }
      return primaryApplicant;
    }

    public static boolean isPrimary(RFA1aFormDTO form, ApplicantDTO applicant) {
      ApplicantDTO primary = getPrimary(form);
      Long primaryId = null;
      if (primary != null) {
        primaryId = primary.getId();
      }

      return Objects.equal(applicant.getId(), primaryId);
    }
  }

  public static class Address {

    private Address() {
    }

    public static RFAAddressDTO getByType(RFA1aFormDTO rfa1aFormDTO, String type) {
      ResidenceDTO residence = rfa1aFormDTO.getResidence();
      if (residence == null) {
        return null;
      }
      Optional<RFAAddressDTO> address =
          residence.getAddresses()
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
        throw new ExpectedException(ExpectedExceptionMessages.CANNOT_PARSE_STREET_ADDRESS,
            Response.Status.BAD_REQUEST);
      }
      return numberAndName[partIndex];
    }

    public static boolean checkIfLisResidentialAddressIsValid(final LisFacFile lisFacFile) {
      return checkIfLisResidentialAddressIsNotBlank(lisFacFile) &&
          checkIfLisResidentialAddressIsNotNullString(lisFacFile);
    }

    public static boolean checkIfLisMailAddressIsValid(final LisFacFile lisFacFile) {
      return checkIfLisMailAddressIsNotBlank(lisFacFile) &&
          checkIfLisMailAddressIsNotNullString(lisFacFile);
    }

    private static boolean checkIfLisResidentialAddressIsNotBlank(final LisFacFile lisFacFile) {
      return !StringUtils
          .isAllBlank(lisFacFile.getFacResStreetAddr(), lisFacFile.getFacResCity(),
              lisFacFile.getFacResState(), lisFacFile.getFacResZipCode());
    }

    private static boolean checkIfLisResidentialAddressIsNotNullString(
        final LisFacFile lisFacFile) {
      return !lisFacFile.getFacResStreetAddr().equalsIgnoreCase(NULL_STRING) ||
          !lisFacFile.getFacResCity().equalsIgnoreCase(NULL_STRING) ||
          !lisFacFile.getFacResState().equalsIgnoreCase(NULL_STRING) ||
          !lisFacFile.getFacResZipCode().equalsIgnoreCase(NULL_STRING);
    }

    private static boolean checkIfLisMailAddressIsNotBlank(final LisFacFile lisFacFile) {
      return !StringUtils
          .isAllBlank(lisFacFile.getFacMailStreetAddr(), lisFacFile.getFacMailCity(),
              lisFacFile.getFacMailState(), lisFacFile.getFacMailZipCode());
    }

    private static boolean checkIfLisMailAddressIsNotNullString(
        final LisFacFile lisFacFile) {
      return !lisFacFile.getFacMailStreetAddr().equalsIgnoreCase(NULL_STRING) ||
          !lisFacFile.getFacMailCity().equalsIgnoreCase(NULL_STRING) ||
          !lisFacFile.getFacMailState().equalsIgnoreCase(NULL_STRING) ||
          !lisFacFile.getFacMailZipCode().equalsIgnoreCase(NULL_STRING);
    }
  }

  public static class County {
    private County() {
    }

    public static String getFlag(List<CountyType> counties, int expectedId, String selectedValue, String rejectedValue) {
      for (CountyType county : counties) {
        if (county.getCwsId() == expectedId) {
          return selectedValue;
        }
      }
      return rejectedValue;
    }
  }

  public static class BooleanToString {

    private BooleanToString() {
    }

    public static String resolve(Boolean flag, String selectedValue, String rejectedValue) {
      if (flag == null) {
        return null;
      }
      if (flag) {
        return selectedValue;
      }
      return rejectedValue;
    }

  }

}
