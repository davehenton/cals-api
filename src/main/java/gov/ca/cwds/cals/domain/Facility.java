package gov.ca.cwds.cals.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String type;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String licenseeName;

    private String licenseeType;

    private String assignedWorker;

    private String districtOffice;

    @NotNull
    private Long licenseNumber;

    private String licenseStatus;

    @NotNull
    private Integer capacity;

    @NotNull
    private LocalDate licenseEffectiveDate;

    @NotNull
    private LocalDate originalApplicationRecievedDate;

    private LocalDate lastVisitDate;

    @Size(max = 50)
    private String emailAddress;

    private String lastVisitReason;

    @NotNull
    private String county;

//    private Set<FacilityAddress> addresses = new HashSet<>();

//    private Set<FacilityPhone> phones = new HashSet<>();

//    private Set<FacilityChild> children = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Facility type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Facility name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseeName() {
        return licenseeName;
    }

    public Facility licenseeName(String licenseeName) {
        this.licenseeName = licenseeName;
        return this;
    }

    public void setLicenseeName(String licenseeName) {
        this.licenseeName = licenseeName;
    }

    public String getLicenseeType() {
        return licenseeType;
    }

    public Facility licenseeType(String licenseeType) {
        this.licenseeType = licenseeType;
        return this;
    }

    public void setLicenseeType(String licenseeType) {
        this.licenseeType = licenseeType;
    }

    public String getAssignedWorker() {
        return assignedWorker;
    }

    public Facility assignedWorker(String assignedWorker) {
        this.assignedWorker = assignedWorker;
        return this;
    }

    public void setAssignedWorker(String assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    public String getDistrictOffice() {
        return districtOffice;
    }

    public Facility districtOffice(String districtOffice) {
        this.districtOffice = districtOffice;
        return this;
    }

    public void setDistrictOffice(String districtOffice) {
        this.districtOffice = districtOffice;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public Facility licenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseStatus() {
        return licenseStatus;
    }

    public Facility licenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
        return this;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Facility capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDate getLicenseEffectiveDate() {
        return licenseEffectiveDate;
    }

    public Facility licenseEffectiveDate(LocalDate licenseEffectiveDate) {
        this.licenseEffectiveDate = licenseEffectiveDate;
        return this;
    }

    public void setLicenseEffectiveDate(LocalDate licenseEffectiveDate) {
        this.licenseEffectiveDate = licenseEffectiveDate;
    }

    public LocalDate getOriginalApplicationRecievedDate() {
        return originalApplicationRecievedDate;
    }

    public Facility originalApplicationRecievedDate(LocalDate originalApplicationRecievedDate) {
        this.originalApplicationRecievedDate = originalApplicationRecievedDate;
        return this;
    }

    public void setOriginalApplicationRecievedDate(LocalDate originalApplicationRecievedDate) {
        this.originalApplicationRecievedDate = originalApplicationRecievedDate;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public Facility lastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
        return this;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Facility emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLastVisitReason() {
        return lastVisitReason;
    }

    public Facility lastVisitReason(String lastVisitReason) {
        this.lastVisitReason = lastVisitReason;
        return this;
    }

    public void setLastVisitReason(String lastVisitReason) {
        this.lastVisitReason = lastVisitReason;
    }

    public String getCounty() {
        return county;
    }

    public Facility county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Facility facility = (Facility) o;
        if (facility.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, facility.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", type='" + type + "'" +
                ", name='" + name + "'" +
                ", licenseeName='" + licenseeName + "'" +
                ", licenseeType='" + licenseeType + "'" +
                ", assignedWorker='" + assignedWorker + "'" +
                ", districtOffice='" + districtOffice + "'" +
                ", licenseNumber='" + licenseNumber + "'" +
                ", licenseStatus='" + licenseStatus + "'" +
                ", capacity='" + capacity + "'" +
                ", licenseEffectiveDate='" + licenseEffectiveDate + "'" +
                ", originalApplicationRecievedDate='" + originalApplicationRecievedDate + "'" +
                ", lastVisitDate='" + lastVisitDate + "'" +
                ", emailAddress='" + emailAddress + "'" +
                ", lastVisitReason='" + lastVisitReason + "'" +
                ", county='" + county + "'" +
                '}';
    }
}
