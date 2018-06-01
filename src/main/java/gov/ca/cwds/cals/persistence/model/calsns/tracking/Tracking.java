package gov.ca.cwds.cals.persistence.model.calsns.tracking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFABaseEntity;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

/**
 * Tracking entity.
 *
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S1948",  "squid:S2160"}) //JsonNode is serializable, Equals in parent
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name = "tracking")
public class Tracking extends RFABaseEntity implements Request, Response {

  private static final long serialVersionUID = -3834894061508509293L;

  @Column(name="facility_name")
  private String facilityName;

  @JsonProperty("rfa_1a_id")
  @Column(name="rfa_1a_id", unique = true)
  private Long rfa1aId;

  @Column(name="license_number", unique = true)
  private String licenseNumber;

  @JsonProperty("tracking_documents")
  @NotNull
  @Type(type = "JsonNode")
  @Column(name="tracking_json")
  private JsonNode trackingJson;

  public String getFacilityName() {
    return facilityName;
  }

  public void setFacilityName(String facilityName) {
    this.facilityName = facilityName;
  }

  public Long getRfa1aId() {
    return rfa1aId;
  }

  public void setRfa1aId(Long rfa1aId) {
    this.rfa1aId = rfa1aId;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public JsonNode getTrackingJson() {
    return trackingJson;
  }

  public void setTrackingJson(JsonNode trackingJson) {
    this.trackingJson = trackingJson;
  }
}
