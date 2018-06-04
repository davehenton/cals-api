package gov.ca.cwds.cals.persistence.model.calsns.tracking;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.CalsBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;


import static gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate.*;

/**
 * Tracking Template entity.
 *
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S1948", "squid:S2160"}) //JsonNode is serializable, Equals in parent

@NamedQuery(
    name = NAMED_QUERY_FIND_BY_TYPE,
    query = "FROM TrackingTemplate WHERE templateType = :type"
)
@NamedQuery(
    name = NAMED_QUERY_FIND_BY_COUNTY_AND_TYPE,
    query = "FROM TrackingTemplate WHERE templateType = :type AND county = :county"
)

@NamedQuery(
    name = NAMED_QUERY_FIND_ALL,
    query = "FROM TrackingTemplate"
)

@NamedQuery(
    name = NAMED_QUERY_FIND_BY_NULL_COUNTY,
    query = "FROM TrackingTemplate WHERE county is NULL"
)

@NamedQuery(
    name = NAMED_QUERY_FIND_BY_COUNTY_CWS_ID,
    query = "FROM TrackingTemplate WHERE county.cwsId = ?"
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name = "tracking_template")
public class TrackingTemplate extends CalsBaseEntity {

  private static final long serialVersionUID = -5126230421420049092L;

  public static final String NAMED_QUERY_FIND_BY_TYPE = "tracking.template.find.by.type";
  public static final String NAMED_QUERY_FIND_BY_COUNTY_AND_TYPE
      = "tracking.template.find.by.county.type";
  public static final String NAMED_QUERY_FIND_ALL
      = "gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate.findAll";

  public static final String NAMED_QUERY_FIND_BY_NULL_COUNTY = "tracking.template.find.by.null.county";
  public static final String NAMED_QUERY_FIND_BY_COUNTY_CWS_ID = "tracking.template.find.by.county.cwsId";
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
