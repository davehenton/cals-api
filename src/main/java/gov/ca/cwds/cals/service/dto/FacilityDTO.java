package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.Identifiable;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import gov.ca.cwds.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainObject;
import io.swagger.annotations.ApiModelProperty;
import org.glassfish.jersey.linking.InjectLink;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static gov.ca.cwds.rest.api.domain.DomainObject.TIME_FORMAT;
import static org.glassfish.jersey.linking.InjectLink.Style.RELATIVE_PATH;

/**
 * {@link DomainObject} representing a facility.
 *
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class FacilityDTO extends BaseDTO implements Request, Response, Identifiable<String> {

  private static final long serialVersionUID = 2L;

  @JsonProperty("href")
  @InjectLink(value = Constants.API.FACILITIES + "/${instance.id}", style = RELATIVE_PATH)
  @ApiModelProperty(required = true, value = "Link to current resource", example =
      Constants.API.FACILITIES + "/193600010")
  private URI href;

  @RemoveTrailingSpaces
  @JsonProperty("id")
  @NotNull
  @ApiModelProperty(required = true, value = "Facility ID", example = "193600010")
  private String id;

  @JsonProperty("capacity_last_changed")
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate capacityLastChanged;

  @JsonProperty("type")
  @NotNull
  @ApiModelProperty(required = true, value = "Facility Type")
  private FacilityTypeDTO type;

  @RemoveTrailingSpaces
  @JsonProperty("name")
  @NotNull
  @Size(max = 50)
  @ApiModelProperty(required = true, value = "Facility Name", example = "LITTLE ROADRUNNERS INFANT CARE")
  private String name;

  @RemoveTrailingSpaces
  @JsonProperty("licensee_name")
  @NotNull
  @Size(max = 50)
  @ApiModelProperty(value = "Licensee Name", example = "BROWN, KIMBERLY")
  private String licenseeName;

  @RemoveTrailingSpaces
  @JsonProperty("license_type")
  @ApiModelProperty(value = "Licensee Type", example = "A")
  private String licenseeType;

  @JsonProperty("assigned_worker")
  @ApiModelProperty(value = "Assigned Worker")
  private DictionaryDTO assignedWorker;

  @JsonProperty("district_office")
  @ApiModelProperty(value = "District Office", example = "MISSION VALLEY")
  private DistrictOfficeDTO districtOffice;

  @RemoveTrailingSpaces
  @JsonProperty("license_number")
  @NotNull
  @ApiModelProperty(value = "License Number", example = "193600161")
  private String licenseNumber;

  @JsonProperty("status")
  @NotNull
  @ApiModelProperty(value = "Facility Status")
  private DictionaryDTO status;

  @JsonProperty("capacity")
  @NotNull
  @ApiModelProperty(value = "Capacity", example = "10")
  private Integer capacity;

  @JsonProperty("license_effective_date")
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate licenseEffectiveDate;

  @JsonProperty("original_application_recieved_date")
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate originalApplicationRecievedDate;

  @JsonProperty("last_visit_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd HH:mm:ss", example = "2000-01-01 00:00:00")
  private LocalDateTime lastVisitDate;

  @JsonProperty("last_deferred_visit_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd HH:mm:ss", example = "2000-01-01 00:00:00")
  private LocalDateTime lastDeferredVisitDate;

  @RemoveTrailingSpaces
  @JsonProperty("email_address")
  @Size(max = 50)
  @ApiModelProperty(value = "Email Address", example = "name@mail.com")
  private String emailAddress;

  @JsonProperty("last_visit_reason")
  @ApiModelProperty(value = "Last Visit Reason")
  private DictionaryDTO lastVisitReason;

  @JsonProperty("last_deferred_visit_reason")
  @ApiModelProperty(value = "Last Deferred Visit Reason")
  private DictionaryDTO lastDeferredVisitReason;

  @JsonProperty("county")
  @NotNull
  @ApiModelProperty(value = "County")
  private CountyDTO county;

  @JsonProperty("phones")
  @ApiModelProperty(required = true, readOnly = true, value = "Facility Phones")
  private ArrayList<PersonPhoneDTO> phone;

  @JsonProperty("addresses")
  @ApiModelProperty(required = true, readOnly = true, value = "Facility Addresses")
  private ArrayList<FacilityAddressDTO> address;

  @JsonProperty("visits")
  @ApiModelProperty(required = true, readOnly = true, value = "Facility Visits")
  private List<FacilityVisitDTO> visits;

  @JsonProperty("annual_visit_year")
  @ApiModelProperty(required = true, readOnly = true, value = "18")
  private Integer annualVisitYear;

  @JsonProperty("prelicensing_visit_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(required = true, readOnly = true, value = "2000-01-01 00:00:00")
  private LocalDateTime prelicensingVisitDate;

  /**
   * Source of the facility. Either CMS or LIS
   */
  @JsonProperty("facility_source")
  @NotNull
  @ApiModelProperty(required = true, readOnly = true, value = "Facility Source")
  private String facilitySource;

  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public FacilityTypeDTO getType() {
    return type;
  }

  public void setType(FacilityTypeDTO type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLicenseeName() {
    return licenseeName;
  }

  public void setLicenseeName(String licenseeName) {
    this.licenseeName = licenseeName;
  }

  public String getLicenseeType() {
    return licenseeType;
  }

  public void setLicenseeType(String licenseeType) {
    this.licenseeType = licenseeType;
  }

  public DictionaryDTO getAssignedWorker() {
    return assignedWorker;
  }

  public void setAssignedWorker(DictionaryDTO assignedWorker) {
    this.assignedWorker = assignedWorker;
  }

  public DistrictOfficeDTO getDistrictOffice() {
    return districtOffice;
  }

  public void setDistrictOffice(DistrictOfficeDTO districtOffice) {
    this.districtOffice = districtOffice;
  }

  public DictionaryDTO getStatus() {
    return status;
  }

  public void setStatus(DictionaryDTO status) {
    this.status = status;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public LocalDate getLicenseEffectiveDate() {
    return licenseEffectiveDate;
  }

  public void setLicenseEffectiveDate(LocalDate licenseEffectiveDate) {
    this.licenseEffectiveDate = licenseEffectiveDate;
  }

  public LocalDate getOriginalApplicationRecievedDate() {
    return originalApplicationRecievedDate;
  }

  public void setOriginalApplicationRecievedDate(LocalDate originalApplicationRecievedDate) {
    this.originalApplicationRecievedDate = originalApplicationRecievedDate;
  }

  public LocalDateTime getLastVisitDate() {
    return lastVisitDate;
  }

  public void setLastVisitDate(LocalDateTime lastVisitDate) {
    this.lastVisitDate = lastVisitDate;
  }

  public LocalDateTime getLastDeferredVisitDate() {
    return lastDeferredVisitDate;
  }

  public void setLastDeferredVisitDate(LocalDateTime lastDeferredVisitDate) {
    this.lastDeferredVisitDate = lastDeferredVisitDate;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public DictionaryDTO getLastVisitReason() {
    return lastVisitReason;
  }

  public void setLastVisitReason(DictionaryDTO lastVisitReason) {
    this.lastVisitReason = lastVisitReason;
  }

  public DictionaryDTO getLastDeferredVisitReason() {
    return lastDeferredVisitReason;
  }

  public void setLastDeferredVisitReason(DictionaryDTO lastDeferredVisitReason) {
    this.lastDeferredVisitReason = lastDeferredVisitReason;
  }

  public CountyDTO getCounty() {
    return county;
  }

  public void setCounty(CountyDTO county) {
    this.county = county;
  }

  public List<FacilityAddressDTO> getAddress() {
    return address;
  }

  public void setAddress(List<FacilityAddressDTO> address) {
    this.address = new ArrayList<>(address);
  }

  public List<FacilityVisitDTO> getVisits() {
    return visits;
  }

  public void setVisits(List<FacilityVisitDTO> visits) {
    this.visits = visits;
  }

  public Integer getAnnualVisitYear() {
    return annualVisitYear;
  }

  public void setAnnualVisitYear(Integer annualVisitYear) {
    this.annualVisitYear = annualVisitYear;
  }

  public LocalDateTime getPrelicensingVisitDate() {
    return prelicensingVisitDate;
  }

  public void setPrelicensingVisitDate(LocalDateTime prelicensingVisitDate) {
    this.prelicensingVisitDate = prelicensingVisitDate;
  }

  public List<PersonPhoneDTO> getPhone() {
    return phone;
  }

  public void setPhone(List<PersonPhoneDTO> phone) {
    this.phone = new ArrayList<>(phone);
  }

  public String getFacilitySource() {
    return facilitySource;
  }

  public void setFacilitySource(String facilitySource) {
    this.facilitySource = facilitySource;
  }

  public LocalDate getCapacityLastChanged() {
    return capacityLastChanged;
  }

  public void setCapacityLastChanged(LocalDate capacityLastChanged) {
    this.capacityLastChanged = capacityLastChanged;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FacilityDTO facilityDTO = (FacilityDTO) o;

    return Objects.equals(id, facilityDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
