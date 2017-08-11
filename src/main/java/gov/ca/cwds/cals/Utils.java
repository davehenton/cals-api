package gov.ca.cwds.cals;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;

import gov.ca.cwds.cals.auth.PerryUserIdentity;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.dto.AddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAAddressDTO;
import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.CharSequenceUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

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

    public Phone() {
    }
  }

  public static class Id {

    public static final String DEFAULT_USER_ID = "0X5";

    private Id() {
    }

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

    public static Long getResidentialStateId(ResidenceDTO residenceDTO) {
      Optional<Serializable> stateTypeId =
          Optional.of(residenceDTO)
              .map(ResidenceDTO::getResidentialAddress)
              .map(RFAAddressDTO::getState)
              .map(StateType::getPrimaryKey);

      return (Long) stateTypeId.orElse(null);
    }
  }

  public static class Address {
    public static String getStreetNumber(AddressDTO addressDTO) {
      String[] numberAndName = StringUtils.split(addressDTO.getStreetAddress(), null, 2);
      String number = numberAndName[0];
      if (!StringUtils.isNumeric(number)) {
        number = null;
      }
      return number;

    }

    public static String getStreetName(AddressDTO addressDTO) {
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
