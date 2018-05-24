package gov.ca.cwds.cals.util;

import com.google.common.base.Objects;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.service.dto.PersonPhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.EmploymentDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.service.mapper.RFA1aFormMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * Cals-API utils class.
 *
 * @author CALS API Team
 */
public final class Utils {

  private Utils() {
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
      return (null != phone
          && StringUtils.isNotBlank(phone.getNumber())
          && !"null".equalsIgnoreCase(phone.getNumber()));
    }
  }

  public static class PlacementHome {

    public static final String WATER_BODY = "water body";
    public static final String WEAPON_IN_HOME_BODY = "weapon in home";

    private PlacementHome() {
    }

    public static String getHazardsDescription(ResidenceDTO residence) {
      if (residence.getWeaponInHome() && residence.getBodyOfWaterExist()) {
        return WATER_BODY + ", " + WEAPON_IN_HOME_BODY;
      }
      if (residence.getWeaponInHome() && !residence.getBodyOfWaterExist()) {
        return WEAPON_IN_HOME_BODY;
      }
      if (!residence.getWeaponInHome() && residence.getBodyOfWaterExist()) {
        return WATER_BODY;
      }
      return " ";
    }

    /**
     * Overloaded method for composing facility name according to applicants list.
     *
     * @param applicantsList applicants list
     * @return facility name
     */
    public static String composeFacilityNameByApplicantsList(List<RFA1aApplicant> applicantsList) {
      return composeFacilityName(
          applicantsList.stream().map(RFA1aFormMapper.INSTANCE::toApplicantDTO)
              .collect(Collectors.toList()));
    }


    /**
     * Composes facility name according to applicantDTOs list.
     *
     * <p>
     * <b>Rules for Facility / Family name</b>
     *
     * Reads Applicant Name(s) as Last Name, First Name of Applicant #1 and Last Name,
     * First Name of Applicant #2. (ex. Smith, John & Jones, Jane)
     * Last Name is separated from First Name by a comma.
     * Applicant Names are separated from each other by "and" or "&".
     * When the Last Name is the same for Applicant #1 and Applicant #2,
     * name reads "Common Last Name, First Name Applicant #1 & First Name Applicant #2"
     * (ex. Smith, John & Jane).
     * </p>
     *
     * @param applicantsList applicantsDTOs list
     * @return facility name
     */
    public static String composeFacilityName(List<ApplicantDTO> applicantsList) {
      // Assume that Facility/Family name composed from the first 2 applicants
      return Optional.ofNullable(applicantsList).map(applicants -> {
        Optional<ApplicantDTO> firstApplicant = getApplicantBuIndex(applicants, 0);
        Optional<ApplicantDTO> secondApplicant = getApplicantBuIndex(applicants, 1);

        StringBuilder firstPartSb = firstApplicant
            .map(PlacementHome::composeFirstPartOfFacilityName)
            .orElse(new StringBuilder());

        StringBuilder secondPartSb = secondApplicant.map(applicantDTO ->
            composeSecondPartOfFacilityName(firstApplicant, applicantDTO)
        ).orElse(new StringBuilder());

        if (firstPartSb.length() > 0 && secondPartSb.length() > 0) {
          firstPartSb.append(" & ");
        }
        firstPartSb.append(secondPartSb);

        return firstPartSb.toString();
      }).orElse(null);
    }

    private static StringBuilder composeSecondPartOfFacilityName(
        Optional<ApplicantDTO> firstApplicant,
        ApplicantDTO secondApplicant) {
      StringBuilder sbForSecondApplicant = new StringBuilder();
      Optional<String> secondLastName = Optional.ofNullable(secondApplicant.getLastName());
      Optional<String> secondFirstName = Optional.ofNullable(secondApplicant.getFirstName());
      if (firstApplicant.isPresent()
          && secondLastName.isPresent()
          && !secondLastName.get().equals(firstApplicant.get().getLastName())) {
        sbForSecondApplicant.append(secondLastName.get());
        if (secondFirstName.isPresent()) {
          sbForSecondApplicant.append(", ");
        }
      }
      secondFirstName.ifPresent(sbForSecondApplicant::append);
      return sbForSecondApplicant;
    }

    private static StringBuilder composeFirstPartOfFacilityName(ApplicantDTO applicantDTO) {
      StringBuilder sb = new StringBuilder();
      Optional<String> lastName = Optional.ofNullable(applicantDTO.getLastName());
      Optional<String> firstName = Optional.ofNullable(applicantDTO.getFirstName());
      lastName.ifPresent(ln -> {
        sb.append(ln);
        if (firstName.isPresent()) {
          sb.append(", ");
        }
      });
      firstName.ifPresent(sb::append);
      return sb;
    }

    private static Optional<ApplicantDTO> getApplicantBuIndex(List<ApplicantDTO> applicants,
        int index) {
      return applicants.size() > index ? Optional.ofNullable(applicants.get(index))
          : Optional.empty();
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
      return StringUtils.joinWith(
          Constants.SPACE, applicant.getFirstName(), applicant.getLastName());
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

  public static class County {

    private County() {
    }

    public static String getFlag(
        List<CountyType> counties, int expectedId, String selectedValue, String rejectedValue) {
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
