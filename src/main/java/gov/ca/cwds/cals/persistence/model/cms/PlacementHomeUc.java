package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 *
 * The converted (uppercase) name of the PLACEMENT_HOME to be used for Placement Home Search and
 * Match.
 */
@Entity
@Table(name = "PLCHM_UC")
@SuppressWarnings({"squid:S3437"}) //LocalDate is serializable
public class PlacementHomeUc implements PersistentObject {


  private static final long serialVersionUID = -773561573288145518L;

  /**
   * PKPLC_HMT - Mandatory Foreign key that DEFINES_THE_SEARCH_KEY_FOR a PLACEMENT_HOME.
   */
  @Id
  @Column(name = "PKPLC_HMT", nullable = false, length = 10)
  private String pkplcHmt;

  /**
   * CITY_NAME - The name of the city where the PLACEMENT HOME is  located.
   */
  @Basic
  @Column(name = "CITY_NM", nullable = false, length = 20)
  private String cityNm;

  /**
   * GEOGRAPHIC_REGION_TEXT_CODE - Represents the geographical region (e.g., NW, SE, NE, etc.) in
   * which the PLACEMENT HOME is located.  The geographic regions are county specific within each
   * county.
   */
  @Basic
  @Column(name = "GEO_RGNTCD", nullable = false, length = 2)
  private String geoRgntcd;

  /**
   * LA_VENDOR_ID - A vendor id assigned by Los Angeles county (and  unique within the county) to a
   * PLACEMENT HOME.  This is only used for the APPS interface program.
   */
  @Basic
  @Column(name = "LA_VNDR_ID", nullable = false, length = 6)
  private String laVndrId;

  /**
   * LAST_UPDATE_ID - The ID (a sequential 3 digit base 62 number generated by the system) of the
   * STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
   */
  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  /**
   * LAST_UPDATE_TIMESTAMP - The date and time of the most recent update of an occurrence of this
   * entity type.
   */
  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  /**
   * LICENSE_NUMBER - The number identifying a specific License issued by either the State of
   * California (CCL) or a county to a specific PLACEMENT HOME.  This number is at times referred to
   * as the facility number.
   */
  @Basic
  @Column(name = "LICENSE_NO", nullable = false, length = 9)
  private String licenseNo;

  /**
   * NAME - The name of the PLACEMENT HOME, if applicable (e.g., the name of a group home).
   */
  @Basic
  @Column(name = "FACLTY_NM", nullable = false, length = 50)
  private String facltyNm;

  /**
   * STREET_NUMBER - The uppercase version of the street or house number associated with the street
   * name as part of the PLACEMENT HOME's address.  This may include the fractional or alphabetic
   * modifier, e.g., A-17, 119-10, 39.2, 100 1/2, etc.
   */
  @Basic
  @Column(name = "STREET_NO", nullable = false, length = 10)
  private String streetNo;

  /**
   * STREET_NAME - The uppercase version of the actual name of the street associated with the
   * PLACEMENT HOME's address.
   */
  @Basic
  @Column(name = "STREET_NM", nullable = false, length = 40)
  private String streetNm;

  public String getPkplcHmt() {
    return pkplcHmt;
  }

  public void setPkplcHmt(String pkplcHmt) {
    this.pkplcHmt = pkplcHmt;
  }

  public String getCityNm() {
    return cityNm;
  }

  public void setCityNm(String cityNm) {
    this.cityNm = cityNm;
  }

  public String getGeoRgntcd() {
    return geoRgntcd;
  }

  public void setGeoRgntcd(String geoRgntcd) {
    this.geoRgntcd = geoRgntcd;
  }

  public String getLaVndrId() {
    return laVndrId;
  }

  public void setLaVndrId(String laVndrId) {
    this.laVndrId = laVndrId;
  }

  public String getLstUpdId() {
    return lstUpdId;
  }

  public void setLstUpdId(String lstUpdId) {
    this.lstUpdId = lstUpdId;
  }

  public LocalDateTime getLstUpdTs() {
    return lstUpdTs;
  }

  public void setLstUpdTs(LocalDateTime lstUpdTs) {
    this.lstUpdTs = lstUpdTs;
  }

  public String getLicenseNo() {
    return licenseNo;
  }

  public void setLicenseNo(String licenseNo) {
    this.licenseNo = licenseNo;
  }

  public String getFacltyNm() {
    return facltyNm;
  }

  public void setFacltyNm(String facltyNm) {
    this.facltyNm = facltyNm;
  }

  public String getStreetNo() {
    return streetNo;
  }

  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  public String getStreetNm() {
    return streetNm;
  }

  public void setStreetNm(String streetNm) {
    this.streetNm = streetNm;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public Serializable getPrimaryKey() {
    return getPkplcHmt();
  }
}