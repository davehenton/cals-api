package gov.ca.cwds.cals.service.dto.tracking;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static gov.ca.cwds.rest.api.domain.DomainObject.TIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.rest.validation.Date;
import io.swagger.annotations.ApiModelProperty;
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
public class TrackingDTO implements Serializable, RequestResponse {

  private final static long serialVersionUID = 860713739682155937L;

  @JsonProperty("id")
  private Long id;
  @JsonProperty("facility_name")
  private String facilityName;
  @JsonProperty("license_number")
  private String licenseNumber;
  @JsonProperty("rfa_1a_id")
  private Long rfa1aId;
  @JsonProperty("tracking_documents")
  private TrackingDocumentsDTO trackingDocuments;

  @JsonProperty("create_datetime")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd HH:mm:ss", example = "2000-01-01 00:00:00")
  private LocalDateTime createDatetime;

  @JsonProperty("create_user_id")
  private String createUserId;

  @JsonProperty("update_datetime")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
  @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
  @ApiModelProperty(value = "yyyy-MM-dd HH:mm:ss", example = "2000-01-01 00:00:00")
  private LocalDateTime updateDatetime;

  @JsonProperty("update_user_id")
  private String updateUserId;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public Long getRfa1aId() {
    return rfa1aId;
  }

  public void setRfa1aId(Long rfa1aId) {
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
