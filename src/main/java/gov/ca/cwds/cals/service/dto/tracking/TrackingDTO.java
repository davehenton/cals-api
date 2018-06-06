package gov.ca.cwds.cals.service.dto.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "facility_name",
    "license_number",
    "rfa_1a_id",
    "tracking_documents",
    "create_datetime",
    "create_user_id",
    "update_datetime",
    "update_user_id"
})
public class TrackingDTO implements Serializable {

  private final static long serialVersionUID = 860713739682155937L;

  @JsonProperty("id")
  private Integer id;
  @JsonProperty("facility_name")
  private String facilityName;
  @JsonProperty("license_number")
  private String licenseNumber;
  @JsonProperty("rfa_1a_id")
  private Integer rfa1aId;
  @JsonProperty("tracking_documents")
  private TrackingDocumentsDTO trackingDocuments;
  @JsonProperty("create_datetime")
  private LocalDateTime createDatetime;
  @JsonProperty("create_user_id")
  private String createUserId;
  @JsonProperty("update_datetime")
  private LocalDateTime updateDatetime;
  @JsonProperty("update_user_id")
  private String updateUserId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFacilityName() {
    return facilityName;
  }

  public void setFacilityName(String facilityName) {
    this.facilityName = facilityName;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public Integer getRfa1aId() {
    return rfa1aId;
  }

  public void setRfa1aId(Integer rfa1aId) {
    this.rfa1aId = rfa1aId;
  }

  public TrackingDocumentsDTO getTrackingDocuments() {
    return trackingDocuments;
  }

  public void setTrackingDocuments(TrackingDocumentsDTO trackingDocuments) {
    this.trackingDocuments = trackingDocuments;
  }

  public LocalDateTime getCreateDatetime() {
    return createDatetime;
  }

  public void setCreateDatetime(LocalDateTime createDatetime) {
    this.createDatetime = createDatetime;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public LocalDateTime getUpdateDatetime() {
    return updateDatetime;
  }

  public void setUpdateDatetime(LocalDateTime updateDatetime) {
    this.updateDatetime = updateDatetime;
  }

  public String getUpdateUserId() {
    return updateUserId;
  }

  public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
  }

}
