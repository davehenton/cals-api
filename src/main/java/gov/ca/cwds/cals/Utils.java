package gov.ca.cwds.cals;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import com.google.common.base.Objects;
import gov.ca.cwds.cals.auth.PerryUserIdentity;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


  }

  public static class Id {

    private static final Logger LOG = LoggerFactory.getLogger(Id.class);

    public static final String DEFAULT_USER_ID = "0X5";
    private static final Object monitor = new Object();
    private static String lastId;

    private Id() {
    }

    public static String generate() {
      synchronized (monitor) {
        String generated = null;
        do {
          generated = CmsKeyIdGenerator.generate(getStaffPersonId());
          try {
            monitor.wait(10L);
          } catch (InterruptedException e) {
            LOG.warn("Interrupted: " + e.getMessage(), e);
            Thread.currentThread().interrupt();
          }
        } while (lastId != null && lastId.equals(generated));

        lastId = generated;
      }
      return lastId;
    }

    public static String getStaffPersonId() {
      Subject currentUser = SecurityUtils.getSubject();
      String staffPersonId = DEFAULT_USER_ID; //Default value
      if (currentUser.getPrincipals() != null) {
        @SuppressWarnings("rawtypes")
        List principals = currentUser.getPrincipals().asList();
        if (principals.size() > 1 && principals.get(1) instanceof PerryUserIdentity) {
          PerryUserIdentity currentUserInfo = (PerryUserIdentity) principals.get(1);
          if (currentUserInfo.getStaffId() != null) {
            staffPersonId = currentUserInfo.getStaffId();
          }
        }
      }
      return staffPersonId;
    }

  }

  public static class Applicant {

    private Applicant() {
    }

    public static String getCaliforniaDriverLicense(ApplicantDTO applicant, String defaultValue) {
      if (applicant.getDriverLicenseState() != null) {
        Long stateId = applicant.getDriverLicenseState().getId();
        if (Constants.StateTypes.CALIFORNIA_STATE_ID.equals(stateId)) {
          return applicant.getDriverLicenseNumber();
        }
      }
      return defaultValue;
    }

    public static boolean isPrimary(RFA1aFormDTO form, ApplicantDTO applicant) {
      List<ApplicantDTO> applicants = form.getApplicants();
      if (applicants.size() == 0) {
        throw new IllegalStateException("No applicants in application (id: " + form.getId() + ")");
      }

      ApplicantDTO expectedPrimaryApplicant = null;
      Long minId = applicants.get(0).getId();
      for (ApplicantDTO applicantDTO : applicants) {
        Long id = applicantDTO.getId();
        if (minId > id) {
          minId = id;
        }
        if (Objects.equal(id, applicant.getId())) {
          expectedPrimaryApplicant = applicantDTO;
        }
      }

      if (expectedPrimaryApplicant == null) {
        throw new IllegalStateException(
            "Applicant (id: " + applicant.getId() + ") not found in application (id: " + form.getId() + ")");
      }

      return Objects.equal(applicant.getId(), minId);
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
      String[] numberAndName = StringUtils.split(addressDTO.getStreetAddress(), null, 2);
      String number = numberAndName[0];
      if (!StringUtils.isNumeric(number)) {
        number = null;
      }
      return number;
    }

    public static String getStreetName(RFAAddressDTO addressDTO) {
      String[] numberAndName = StringUtils.split(addressDTO.getStreetAddress(), null, 2);
      return numberAndName[numberAndName.length - 1];
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
