package gov.ca.cwds.cals;

import gov.ca.cwds.cals.auth.PerryUserIdentity;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Optional;

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

    public static String formatNumber(PhoneDTO phone) {
      String number = phone.getNumber();
      if (phone.getExtension() != null) {
        number = number + " ext. " + phone.getExtension();
      }
      return number;
    }

    private Phone() {
    }
  }

  public static class Id {

    public static final String DEFAULT_USER_ID = "0X5";

    public static String generate() {
      return CmsKeyIdGenerator.generate(getStaffPersonId());
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

    private Id() {
    }
  }

  public static class Address {

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

    private Address() {
    }
  }

  public static class BooleanToString {
    public static String resolve(Boolean flag, String selectedValue, String rejectedValue) {
      if (flag == null) {
        return null;
      }
      if (flag) {
        return selectedValue;
      }
      return rejectedValue;
    }

    private BooleanToString() {
    }
  }
}
