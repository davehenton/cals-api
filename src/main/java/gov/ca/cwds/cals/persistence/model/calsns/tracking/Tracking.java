package gov.ca.cwds.cals.persistence.model.calsns.tracking;

import static gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking.NAMED_QUERY_FIND_BY_LICENSE_NUMBER;
import static gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking.NAMED_QUERY_FIND_BY_RFA_1A_ID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.CalsBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * Tracking entity.
 *
 * @author CWDS TPT-2 Team
 */

@NamedQuery(
    name = NAMED_QUERY_FIND_BY_RFA_1A_ID,
    query = "FROM Tracking WHERE rfa1aId = :rfa1aId"
)
@NamedQuery(
    name = NAMED_QUERY_FIND_BY_LICENSE_NUMBER,
    query = "FROM Tracking WHERE licenseNumber = :licenseNumber"
)

@SuppressWarnings({"squid:S1948", "squid:S2160"}) //JsonNode is serializable, Equals in parent
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name = "tracking")
public class Tracking extends CalsBaseEntity implements RequestResponse {

  private static final long serialVersionUID = -3834894061508509293L;

  public static final String NAMED_QUERY_FIND_BY_RFA_1A_ID = "tracking.find.by.rfa1a.id";
  public static final String NAMED_QUERY_FIND_BY_LICENSE_NUMBER = "tracking.find.by.license.number";

  @Column(name = "facility_name")
  private String facilityName;

  @JsonProperty("rfa_1a_id")
  @Column(name = "rfa_1a_id", unique = true)
  private Long rfa1aId;

  @Column(name = "license_number", unique = true)
  private String licenseNumber;

  @JsonProperty("tracking_documents")
  @NotNull
  @Type(type = "JsonNode")
  @Column(name = "tracking_json")
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
