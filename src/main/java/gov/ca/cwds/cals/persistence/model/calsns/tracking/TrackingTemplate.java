package gov.ca.cwds.cals.persistence.model.calsns.tracking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFABaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

/**
 * Tracking Template entity.
 *
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S1948", "squid:S2160"}) //JsonNode is serializable, Equals in parent
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name = "tracking_template")
public class TrackingTemplate extends RFABaseEntity {

  private static final long serialVersionUID = -5126230421420049092L;

  @ManyToOne
  @JoinColumn(name = "county_code")
  private CountyType county;

  @NotNull
  @Column(name = "template_type", nullable = false)
  private String templateType;

  @NotNull
  @Type(type = "JsonNode")
  @Column(name = "template_json", nullable = false)
  private JsonNode templateJson;

  public CountyType getCounty() {
    return county;
  }

  public void setCounty(CountyType county) {
    this.county = county;
  }

  public String getTemplateType() {
    return templateType;
  }

  public void setTemplateType(String templateType) {
    this.templateType = templateType;
  }

  public JsonNode getTemplateJson() {
    return templateJson;
  }

  public void setTemplateJson(JsonNode templateJson) {
    this.templateJson = templateJson;
  }
}
