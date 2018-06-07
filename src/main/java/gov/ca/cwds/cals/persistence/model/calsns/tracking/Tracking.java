package gov.ca.cwds.cals.persistence.model.calsns.tracking;

import static gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking.NAMED_QUERY_FIND_BY_LICENSE_NUMBER;
import static gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking.NAMED_QUERY_FIND_BY_RFA_1A_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.tracking.Tracking.NAMED_QUERY_FIND_BY_RFA_1A_ID_AND_TRACKING_ID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.CalsBaseEntity;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    name = NAMED_QUERY_FIND_BY_RFA_1A_ID_AND_TRACKING_ID,
    query = "FROM Tracking WHERE rfa1aId = :rfa1aId AND id = :id"
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
  public static final String NAMED_QUERY_FIND_BY_LICENSE_NUMBER
      = "tracking.find.by.license.number";
  public static final String NAMED_QUERY_FIND_BY_RFA_1A_ID_AND_TRACKING_ID
      = "tracking.find.by.rfa1a.id.and.tracking.id";


  @Column(name = "facility_name")
  private String facilityName;

  @OneToOne
  @JoinColumn(name = "rfa_1a_id", insertable = false, updatable = false)
  @JsonIgnore
  private RFA1aForm rfa1aForm;

  @JsonProperty("rfa_1a_id")
  @Column(name = "rfa_1a_id", unique = true)
  private Long rfa1aId;

  @Column(name = "license_number", unique = true, length = 10)
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

  public RFA1aForm getRfa1aForm() {
    return rfa1aForm;
  }

  public void setRfa1aForm(RFA1aForm rfa1aForm) {
    this.rfa1aForm = rfa1aForm;
  }
}
