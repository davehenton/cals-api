package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainObject;
import io.swagger.annotations.ApiModelProperty;
import org.glassfish.jersey.linking.InjectLink;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.glassfish.jersey.linking.InjectLink.Style.RELATIVE_PATH;

/**
 * {@link DomainObject} representing a facility.
 *
 * @author CWDS CALS API Team
 */
public class FacilityDTO extends DomainObject implements Serializable, Request, Response {

    private static final long serialVersionUID = 1L;

    @JsonProperty("href")
    @InjectLink(value = Constants.API.FACILITIES + "/${instance.id}", style=RELATIVE_PATH)
    @ApiModelProperty(required = true, readOnly = false, value = "Link to current resource", example = Constants.API.FACILITIES + "/193600010")
    URI href;

    @JsonProperty("id")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Facility ID", example = "193600010")
    private Long id;

    @JsonProperty("type")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Facility Type")
    private DictionaryDTO type;

    @JsonProperty("name")
    @NotNull
    @Size(max = 50)
    @ApiModelProperty(required = true, readOnly = false, value = "Facility Name", example = "LITTLE ROADRUNNERS INFANT CARE")
    private String name;

    @JsonProperty("licensee_name")
    @NotNull
    @Size(max = 50)
    @ApiModelProperty(required = false, readOnly = false, value = "Licensee Name", example = "BROWN, KIMBERLY")
    private String licenseeName;

    @JsonProperty("license_type")
    @ApiModelProperty(required = false, readOnly = false, value = "Licensee Type", example = "A")
    private String licenseeType;

    @JsonProperty("assigned_worker")
    @ApiModelProperty(required = false, readOnly = false, value = "Assigned Worker", example = "PARKER, TONY")
    private String assignedWorker;

    @JsonProperty("district_office")
    @ApiModelProperty(required = false, readOnly = false, value = "District Office", example = "MISSION VALLEY")
    private DistrictOfficeDTO districtOffice;

    @JsonProperty("license_number")
    @NotNull
    @ApiModelProperty(required = false, readOnly = false, value = "License Number", example = "193600161")
    private Long licenseNumber;

    @JsonProperty("status")
    @NotNull
    @ApiModelProperty(required = false, readOnly = false, value = "Facility Status")
    private DictionaryDTO status;

    @JsonProperty("capacity")
    @NotNull
    @ApiModelProperty(required = false, readOnly = false, value = "Capacity", example = "10")
    private Integer capacity;

    @JsonProperty("license_effective_date")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd", example = "2000-01-01")
    private LocalDate licenseEffectiveDate;

    @JsonProperty("original_application_recieved_date")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd", example = "2000-01-01")
    private LocalDate originalApplicationRecievedDate;

    @JsonProperty("last_visit_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd", example = "2000-01-01")
    private LocalDate lastVisitDate;

    @JsonProperty("email_address")
    @Size(max = 50)
    @ApiModelProperty(required = false, readOnly = false, value = "Email Address", example = "name@mail.com")
    private String emailAddress;

    @JsonProperty("last_visit_reason")
    @ApiModelProperty(required = false, readOnly = false, value = "Last Visit Reason")
    private DictionaryDTO lastVisitReason;

    @JsonProperty("county")
    @NotNull
    @ApiModelProperty(required = false, readOnly = false, value = "County")
    private DictionaryDTO county;

    @JsonProperty("children")
    @NotNull
    @ApiModelProperty(required = true, readOnly = true, value = "Children hyperlink", example =
            Constants.API.FACILITIES + "/193600010/" + Constants.API.CHILDREN)
    private HyperlinkDTO children;

    @JsonProperty("complains")
    @NotNull
    @ApiModelProperty(required = true, readOnly = true, value = "Complains hyperlink", example =
            Constants.API.FACILITIES + "/193600010/" + Constants.API.COMPLAINS)
    private HyperlinkDTO complains;

    @JsonProperty("phones")
    @ApiModelProperty(required = true, readOnly = true, value = "Facility Phones")
    private List<PhoneDTO> phone;

    @JsonProperty("addresses")
    @ApiModelProperty(required = true, readOnly = true, value = "Facility Addresses")
    private List<FacilityAddressDTO> address;

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DictionaryDTO getType() {
        return type;
    }

    public void setType(DictionaryDTO type) {
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

    public String getAssignedWorker() {
        return assignedWorker;
    }

    public void setAssignedWorker(String assignedWorker) {
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

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
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

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
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

    public DictionaryDTO getCounty() {
        return county;
    }

    public void setCounty(DictionaryDTO county) {
        this.county = county;
    }

    public HyperlinkDTO getChildren() {
        return children;
    }

    public void setChildren(HyperlinkDTO children) {
        this.children = children;
    }

    public HyperlinkDTO getComplains() {
        return complains;
    }

    public void setComplains(HyperlinkDTO complains) {
        this.complains = complains;
    }

    public List<FacilityAddressDTO> getAddress() {
        return address;
    }

    public void setAddress(List<FacilityAddressDTO> address) {
        this.address = address;
    }

    public List<PhoneDTO> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneDTO> phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "FacilityDTO{" +
                "href=" + href +
                ", id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", licenseeName='" + licenseeName + '\'' +
                ", licenseeType='" + licenseeType + '\'' +
                ", assignedWorker='" + assignedWorker + '\'' +
                ", districtOffice=" + districtOffice +
                ", licenseNumber=" + licenseNumber +
                ", status=" + status +
                ", capacity=" + capacity +
                ", licenseEffectiveDate=" + licenseEffectiveDate +
                ", originalApplicationRecievedDate=" + originalApplicationRecievedDate +
                ", lastVisitDate=" + lastVisitDate +
                ", emailAddress='" + emailAddress + '\'' +
                ", lastVisitReason=" + lastVisitReason +
                ", county=" + county +
                ", children=" + children +
                ", complains=" + complains +
                ", address=" + address +
                ", phone=" + phone +
                '}';
    }
}