package gov.ca.cwds.cals;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import com.google.common.base.Objects;
import gov.ca.cwds.cals.Constants.ExpectedExceptionMessages;
import gov.ca.cwds.cals.auth.PerryUserIdentity;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;
import gov.ca.cwds.rest.exception.ExpectedException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;
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
