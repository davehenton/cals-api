package gov.ca.cwds.cals.rest.api.domain;

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

    //"id": 10,
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "ABC1234567")
    private String id;

    //"type": "get",
    @ApiModelProperty(required = false, readOnly = false, value = "get", example = "get")
    private String type;

    //"name": fac_name,
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "Facility Name")
    private String name;

    //"licensee_name": fac_licensee_name,
    @JsonProperty(value = "licensee_name")
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "get")
    private String licenseeName;

    //"assigned_worker": assigned_worker,
    @JsonProperty(value = "assigned_worker")
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "John Doh")
    private String assignedWorker;

    //"district_office": district_office,
    @JsonProperty(value = "district_office")
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "")
    private String districtOffice;

    //"license_type": type,
    @JsonProperty(value = "license_type")
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "")
    private String liceneeType;

    //"license_number": fac_nbr,
    @JsonProperty(value = "license_number")
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "")
    private String licenseNumber;

    //"license_status": status,
    @JsonProperty(value = "license_status")
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "")
    private String licenseStatus;

    //"capacity": fac_capacity,
    @ApiModelProperty(required = false, readOnly = false, value = "", example = "")
    private int capacity;


    //"license_effective_date": fac_lic_eff_date,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "license_effective_date")
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = true)
    @ApiModelProperty(required = true, readOnly = true, value = "yyyy-MM-dd", example = "2016-01-01")
    private String licenseEffectiveDate;


    //"original_application_recieved_date":fac_orig_appl_rec_date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "original_application_recieved_date")
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = true)
    @ApiModelProperty(required = true, readOnly = true, value = "yyyy-MM-dd", example = "2016-01-01")
    private String originalApplicationRecievedDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getAssignedWorker() {
        return assignedWorker;
    }

    public void setAssignedWorker(String assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    public String getDistrictOffice() {
        return districtOffice;
    }

    public void setDistrictOffice(String districtOffice) {
        this.districtOffice = districtOffice;
    }

    public String getLiceneeType() {
        return liceneeType;
    }

    public void setLiceneeType(String liceneeType) {
        this.liceneeType = liceneeType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLicenseEffectiveDate() {
        return licenseEffectiveDate;
    }

    public void setLicenseEffectiveDate(String licenseEffectiveDate) {
        this.licenseEffectiveDate = licenseEffectiveDate;
    }

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
