
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.service.dto.FacilityTypeDTO;
import gov.ca.cwds.cals.service.dto.formsapi.FormNameAware;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Placement Home ID information
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "primary_scp",
    "fac_type",
    "operated_by",
    "sub_type",
    "license_number",
    "age_range",
    "capacity",
    "prim_contact",
    "backup_contact",
    "options"
})
public class CommonInfoDTO implements FormNameAware {

  public static final String PH_PAGE_ID_COMMON_INFO = "PH_page_ID_common_info";
  /**
   * Name
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("name")
  private String name;
  /**
   * Primary Substitute Care Provider
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("primary_scp")
  private String primaryScp;
  @JsonProperty("fac_type")
  private FacilityTypeDTO facType;
  @JsonProperty("operated_by")
  private OperatedByDTO operatedBy;
  @JsonProperty("sub_type")
  private SubTypeDTO subType;
  /**
   * License Number
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("license_number")
  private String licenseNumber;
  /**
   * Age Range
   * <p>
   */
  @JsonProperty("age_range")
  private AgeRangeDTO ageRange;
  @JsonProperty("capacity")
  private CapacityDTO capacity;
  @JsonProperty("prim_contact")
  private PrimaryContactDTO primContact;
  /**
   * Backup Contact
   * <p>
   */
  @JsonProperty("backup_contact")
  private BackupContactDTO backupContact;
  @JsonProperty("options")
  private OptionsDTO options;

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Primary Substitute Care Provider
   * <p>
   */
  @JsonProperty("primary_scp")
  public String getPrimaryScp() {
    return primaryScp;
  }

  /**
   * Primary Substitute Care Provider
   * <p>
   */
  @JsonProperty("primary_scp")
  public void setPrimaryScp(String primaryScp) {
    this.primaryScp = primaryScp;
  }

  @JsonProperty("fac_type")
  public FacilityTypeDTO getFacType() {
    return facType;
  }

  @JsonProperty("fac_type")
  public void setFacType(FacilityTypeDTO facType) {
    this.facType = facType;
  }

  @JsonProperty("operated_by")
  public OperatedByDTO getOperatedBy() {
    return operatedBy;
  }

  @JsonProperty("operated_by")
  public void setOperatedBy(OperatedByDTO operatedBy) {
    this.operatedBy = operatedBy;
  }

  @JsonProperty("sub_type")
  public SubTypeDTO getSubType() {
    return subType;
  }

  @JsonProperty("sub_type")
  public void setSubType(SubTypeDTO subType) {
    this.subType = subType;
  }

  /**
   * License Number
   * <p>
   */
  @JsonProperty("license_number")
  public String getLicenseNumber() {
    return licenseNumber;
  }

  /**
   * License Number
   * <p>
   */
  @JsonProperty("license_number")
  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  /**
   * Age Range
   * <p>
   */
  @JsonProperty("age_range")
  public AgeRangeDTO getAgeRange() {
    return ageRange;
  }

  /**
   * Age Range
   * <p>
   */
  @JsonProperty("age_range")
  public void setAgeRange(AgeRangeDTO ageRange) {
    this.ageRange = ageRange;
  }

  @JsonProperty("capacity")
  public CapacityDTO getCapacity() {
    return capacity;
  }

  @JsonProperty("capacity")
  public void setCapacity(CapacityDTO capacity) {
    this.capacity = capacity;
  }

  @JsonProperty("prim_contact")
  public PrimaryContactDTO getPrimContact() {
    return primContact;
  }

  @JsonProperty("prim_contact")
  public void setPrimContact(PrimaryContactDTO primContact) {
    this.primContact = primContact;
  }

  /**
   * Backup Contact
   * <p>
   */
  @JsonProperty("backup_contact")
  public BackupContactDTO getBackupContact() {
    return backupContact;
  }

  /**
   * Backup Contact
   * <p>
   */
  @JsonProperty("backup_contact")
  public void setBackupContact(BackupContactDTO backupContact) {
    this.backupContact = backupContact;
  }

  @JsonProperty("options")
  public OptionsDTO getOptions() {
    return options;
  }

  @JsonProperty("options")
  public void setOptions(OptionsDTO options) {
    this.options = options;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("name", name).append("primaryScp", primaryScp)
        .append("facType", facType).append("operatedBy", operatedBy).append("subType", subType)
        .append("licenseNumber", licenseNumber).append("ageRange", ageRange)
        .append("capacity", capacity).append("primContact", primContact)
        .append("backupContact", backupContact).append("options", options).toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(operatedBy).append(primContact).append(primaryScp)
        .append(ageRange).append(backupContact).append(subType).append(licenseNumber).append(name)
        .append(capacity).append(options).append(facType).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CommonInfoDTO)) {
      return false;
    }
    CommonInfoDTO rhs = ((CommonInfoDTO) other);
    return new EqualsBuilder().append(operatedBy, rhs.operatedBy)
        .append(primContact, rhs.primContact).append(primaryScp, rhs.primaryScp)
        .append(ageRange, rhs.ageRange).append(backupContact, rhs.backupContact)
        .append(subType, rhs.subType).append(licenseNumber, rhs.licenseNumber)
        .append(name, rhs.name).append(capacity, rhs.capacity).append(options, rhs.options)
        .append(facType, rhs.facType).isEquals();
  }

  @Override
  public String formName() {
    return PH_PAGE_ID_COMMON_INFO;
  }
}
