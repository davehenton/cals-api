package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.api.domain.DomainObject;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 *  {@link DomainObject} represent Facility
 *
 *  @author CALS API Team
 *
 */
public class Facility extends DomainObject implements Request, Response {

    private static final long serialVersionUID = 3090955786152575097L;

    @ApiModelProperty(example = "ABC1234567")
    private String id;

    @ApiModelProperty(value = "get", example = "get")
    private String type;

    @ApiModelProperty(example = "Facility Name")
    private String name;

    @JsonProperty(value = "licensee_name")
    @ApiModelProperty(example = "get")
    private String licenseeName;

    @JsonProperty(value = "assigned_worker")
    @ApiModelProperty(example = "John Doh")
    private String assignedWorker;

    @JsonProperty(value = "district_office")
    @ApiModelProperty(example = "")
    private String districtOffice;

    @JsonProperty(value = "license_type")
    @ApiModelProperty
    private String liceneeType;

    @JsonProperty(value = "license_number")
    @ApiModelProperty
    private String licenseNumber;

    @JsonProperty(value = "license_status")
    @ApiModelProperty
    private String licenseStatus;

    @ApiModelProperty
    private int capacity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "license_effective_date")
    @gov.ca.cwds.rest.validation.Date(required = true)
    @ApiModelProperty(required = true, readOnly = true, value = "yyyy-MM-dd", example = "2016-01-01")
    private String licenseEffectiveDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "original_application_recieved_date")
    @gov.ca.cwds.rest.validation.Date(required = true)
    @ApiModelProperty(required = true, readOnly = true, value = "yyyy-MM-dd", example = "2016-01-01")
    private String originalApplicationRecievedDate;

    /**
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return facility type
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return facility name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return licensee name
     */
    public String getLicenseeName() {
        return licenseeName;
    }

    public void setLicenseeName(String licenseeName) {
        this.licenseeName = licenseeName;
    }

    /**
     *
     * @return assigned worker
     */
    public String getAssignedWorker() {
        return assignedWorker;
    }

    public void setAssignedWorker(String assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    /**
     *
     * @return office district
     */
    public String getDistrictOffice() {
        return districtOffice;
    }

    public void setDistrictOffice(String districtOffice) {
        this.districtOffice = districtOffice;
    }

    /**
     *
     * @return license name
     */
    public String getLiceneeType() {
        return liceneeType;
    }

    public void setLiceneeType(String liceneeType) {
        this.liceneeType = liceneeType;
    }

    /**
     *
     * @return license number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     *
     * @return license status
     */
    public String getLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    /**
     *
     * @return facility capacity
     */
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return license effective date
     */
    public String getLicenseEffectiveDate() {
        return licenseEffectiveDate;
    }

    public void setLicenseEffectiveDate(String licenseEffectiveDate) {
        this.licenseEffectiveDate = licenseEffectiveDate;
    }

    /**
     *
     * @return original application received date
     */
    public String getOriginalApplicationRecievedDate() {
        return originalApplicationRecievedDate;
    }

    public void setOriginalApplicationRecievedDate(String originalApplicationRecievedDate) {
        this.originalApplicationRecievedDate = originalApplicationRecievedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        if (capacity != facility.capacity) return false;
        if (!id.equals(facility.id)) return false;
        if (type != null ? !type.equals(facility.type) : facility.type != null) return false;
        if (name != null ? !name.equals(facility.name) : facility.name != null) return false;
        if (licenseeName != null ? !licenseeName.equals(facility.licenseeName) : facility.licenseeName != null)
            return false;
        if (assignedWorker != null ? !assignedWorker.equals(facility.assignedWorker) : facility.assignedWorker != null)
            return false;
        if (districtOffice != null ? !districtOffice.equals(facility.districtOffice) : facility.districtOffice != null)
            return false;
        if (liceneeType != null ? !liceneeType.equals(facility.liceneeType) : facility.liceneeType != null)
            return false;
        if (licenseNumber != null ? !licenseNumber.equals(facility.licenseNumber) : facility.licenseNumber != null)
            return false;
        if (licenseStatus != null ? !licenseStatus.equals(facility.licenseStatus) : facility.licenseStatus != null)
            return false;
        if (licenseEffectiveDate != null ? !licenseEffectiveDate.equals(facility.licenseEffectiveDate) : facility.licenseEffectiveDate != null)
            return false;
        return originalApplicationRecievedDate != null ? originalApplicationRecievedDate.equals(facility.originalApplicationRecievedDate) : facility.originalApplicationRecievedDate == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (licenseeName != null ? licenseeName.hashCode() : 0);
        result = 31 * result + (assignedWorker != null ? assignedWorker.hashCode() : 0);
        result = 31 * result + (districtOffice != null ? districtOffice.hashCode() : 0);
        result = 31 * result + (liceneeType != null ? liceneeType.hashCode() : 0);
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (licenseStatus != null ? licenseStatus.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (licenseEffectiveDate != null ? licenseEffectiveDate.hashCode() : 0);
        result = 31 * result + (originalApplicationRecievedDate != null ? originalApplicationRecievedDate.hashCode() : 0);
        return result;
    }
}
