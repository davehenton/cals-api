package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LicenseType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.CheckReferentialIntegrity;
import java.util.List;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160")//Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AdoptionHistoryDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = 1466581714306274681L;

  @Valid
  private FosterCareLicensesQ1 fosterCareLicensesQ1;

  @Valid
  private ApplicationsForAdoptionQ2 applicationsForAdoptionQ2;

  @Valid
  private FacilityOperationLicensesQ3 facilityOperationLicensesQ3;

  @Valid
  private EmploymentInFacilitiesQ4 employmentInFacilitiesQ4;

  @Valid
  private DenialHistoryQ5 denialHistoryQ5;

  @Valid
  private SuspensionRevocationHistoryQ6 suspensionRevocationHistoryQ6;

  private boolean wasSubjectForExclusionOrderQ7;

  public FosterCareLicensesQ1 getFosterCareLicensesQ1() {
    return fosterCareLicensesQ1;
  }

  public void setFosterCareLicensesQ1(
      FosterCareLicensesQ1 fosterCareLicensesQ1) {
    this.fosterCareLicensesQ1 = fosterCareLicensesQ1;
  }

  public ApplicationsForAdoptionQ2 getApplicationsForAdoptionQ2() {
    return applicationsForAdoptionQ2;
  }

  public void setApplicationsForAdoptionQ2(
      ApplicationsForAdoptionQ2 applicationsForAdoptionQ2) {
    this.applicationsForAdoptionQ2 = applicationsForAdoptionQ2;
  }

  public AdoptionHistoryDTO.FacilityOperationLicensesQ3 getFacilityOperationLicensesQ3() {
    return facilityOperationLicensesQ3;
  }

  public void setFacilityOperationLicensesQ3(
      AdoptionHistoryDTO.FacilityOperationLicensesQ3 facilityOperationLicensesQ3) {
    this.facilityOperationLicensesQ3 = facilityOperationLicensesQ3;
  }

  public EmploymentInFacilitiesQ4 getEmploymentInFacilitiesQ4() {
    return employmentInFacilitiesQ4;
  }

  public void setEmploymentInFacilitiesQ4(
      EmploymentInFacilitiesQ4 employmentInFacilitiesQ4) {
    this.employmentInFacilitiesQ4 = employmentInFacilitiesQ4;
  }

  public DenialHistoryQ5 getDenialHistoryQ5() {
    return denialHistoryQ5;
  }

  public void setDenialHistoryQ5(
      DenialHistoryQ5 denialHistoryQ5) {
    this.denialHistoryQ5 = denialHistoryQ5;
  }

  public SuspensionRevocationHistoryQ6 getSuspensionRevocationHistoryQ6() {
    return suspensionRevocationHistoryQ6;
  }

  public void setSuspensionRevocationHistoryQ6(
      SuspensionRevocationHistoryQ6 suspensionRevocationHistoryQ6) {
    this.suspensionRevocationHistoryQ6 = suspensionRevocationHistoryQ6;
  }

  public boolean isWasSubjectForExclusionOrderQ7() {
    return wasSubjectForExclusionOrderQ7;
  }

  public void setWasSubjectForExclusionOrderQ7(boolean wasSubjectForExclusionOrderQ7) {
    this.wasSubjectForExclusionOrderQ7 = wasSubjectForExclusionOrderQ7;
  }

  /**
   * @author CWDS CALS API Team
   */
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class FosterCareLicensesQ1 extends BaseDTO {

    private static final long serialVersionUID = 2997447845465265427L;

    private boolean wasPreviouslyLicensed;

    private List<AgencyDTO> agencies;

    public boolean isWasPreviouslyLicensed() {
      return wasPreviouslyLicensed;
    }

    public void setWasPreviouslyLicensed(boolean wasPreviouslyLicensed) {
      this.wasPreviouslyLicensed = wasPreviouslyLicensed;
    }

    public List<AgencyDTO> getAgencies() {
      return agencies;
    }

    public void setAgencies(List<AgencyDTO> agencies) {
      this.agencies = agencies;
    }
  }

  /**
   * @author CWDS CALS API Team
   */
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class ApplicationsForAdoptionQ2 extends BaseDTO {

    private static final long serialVersionUID = -5376871646650659340L;

    private boolean haveAppliedForAdoption;

    private List<String> facilities;

    public boolean isHaveAppliedForAdoption() {
      return haveAppliedForAdoption;
    }

    public void setHaveAppliedForAdoption(boolean haveAppliedForAdoption) {
      this.haveAppliedForAdoption = haveAppliedForAdoption;
    }

    public List<String> getFacilities() {
      return facilities;
    }

    public void setFacilities(List<String> facilities) {
      this.facilities = facilities;
    }

  }

  /**
   * @author CWDS CALS API Team
   */
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class FacilityOperationLicensesQ3 extends BaseDTO {

    private static final long serialVersionUID = 6565787215494617722L;

    private boolean wasPreviouslyLicensed;

    private List<AgencyDTO> agencies;

    public boolean isWasPreviouslyLicensed() {
      return wasPreviouslyLicensed;
    }

    public void setWasPreviouslyLicensed(boolean wasPreviouslyLicensed) {
      this.wasPreviouslyLicensed = wasPreviouslyLicensed;
    }

    public List<AgencyDTO> getAgencies() {
      return agencies;
    }

    public void setAgencies(List<AgencyDTO> agencies) {
      this.agencies = agencies;
    }
  }

  /**
   * @author CWDS CALS API Team
   */
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class EmploymentInFacilitiesQ4 extends BaseDTO {

    private static final long serialVersionUID = 3828070517329879462L;

    private boolean wasEmployedOrVolunteered;

    private List<String> facilities;

    public boolean isWasEmployedOrVolunteered() {
      return wasEmployedOrVolunteered;
    }

    public void setWasEmployedOrVolunteered(boolean wasEmployedOrVolunteered) {
      this.wasEmployedOrVolunteered = wasEmployedOrVolunteered;
    }

    public List<String> getFacilities() {
      return facilities;
    }

    public void setFacilities(List<String> facilities) {
      this.facilities = facilities;
    }
  }

  /**
   * @author CWDS CALS API Team
   */
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class DenialHistoryQ5 extends BaseDTO {

    private static final long serialVersionUID = 6797171388514466929L;

    private boolean hadDenials;

    private List<AgencyDTO> agencies;

    public boolean isHadDenials() {
      return hadDenials;
    }

    public void setHadDenials(boolean hadDenials) {
      this.hadDenials = hadDenials;
    }

    public List<AgencyDTO> getAgencies() {
      return agencies;
    }

    public void setAgencies(List<AgencyDTO> agencies) {
      this.agencies = agencies;
    }
  }

  /**
   * @author CWDS CALS API Team
   */
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class SuspensionRevocationHistoryQ6 extends BaseDTO {

    private static final long serialVersionUID = -4077489037820146583L;

    private boolean hadSuspensionsRevocations;

    private List<AgencyDTO> agencies;

    public boolean isHadSuspensionsRevocations() {
      return hadSuspensionsRevocations;
    }

    public void setHadSuspensionsRevocations(boolean hadSuspensionsRevocations) {
      this.hadSuspensionsRevocations = hadSuspensionsRevocations;
    }

    public List<AgencyDTO> getAgencies() {
      return agencies;
    }

    public void setAgencies(List<AgencyDTO> agencies) {
      this.agencies = agencies;
    }
  }

  /**
   * @author CWDS CALS API Team
   */
  public static class AgencyDTO extends BaseDTO {
    private String name;

    @CheckReferentialIntegrity
    private LicenseType type;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public LicenseType getType() {
      return type;
    }

    public void setType(LicenseType type) {
      this.type = type;
    }
  }
}
