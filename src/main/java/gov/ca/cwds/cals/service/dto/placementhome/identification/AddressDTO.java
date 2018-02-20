
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SimpleDictionary;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.GenericSimpleDictionaryImpl;
import gov.ca.cwds.cals.service.dto.formsapi.FormNameAware;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;


/**
 * Placement Home ID Address
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "street_no",
    "street_name",
    "city",
    "state",
    "zip",
    "zip_ext",
    "geo_region",
    "county",
    "foreign_country",
    "foreign_zip",
    "foreign_addr_desc",
    "comment"
})
public class AddressDTO implements FormNameAware {

  public static final String PH_PAGE_ID_ADDRESS = "PH_page_ID_Address";
  /**
   * Street No.
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("street_no")
  private String streetNo;
  /**
   * Street Name.
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("street_name")
  private String streetName;
  /**
   * City
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("city")
  private String city;
  /**
   * State
   * <p>
   */
  @JsonProperty("state")
  private StateDTO state;
  /**
   * ZIP
   * <p>
   */
  @JsonProperty("zip")
  private String zip;
  /**
   * ZIP Ext.
   * <p>
   */
  @JsonProperty("zip_ext")
  private String zipExt;
  /**
   * Geographic Region
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("geo_region")
  private String geoRegion;
  @JsonProperty("county")
  private GenericSimpleDictionaryImpl county;
  @JsonProperty("foreign_country")
  private GenericSimpleDictionaryImpl foreignCountry;
  /**
   * Foreign ZIP
   * <p>
   */
  @JsonProperty("foreign_zip")
  private String foreignZip;
  /**
   * Foreign Address Description (Province name, etc)
   * <p>
   */
  @JsonProperty("foreign_addr_desc")
  private String foreignAddrDesc;
  /**
   * Comment
   * <p>
   */
  @RemoveTrailingSpaces
  @JsonProperty("comment")
  private String comment;

  /**
   * Street No.
   * <p>
   */
  @JsonProperty("street_no")
  public String getStreetNo() {
    return streetNo;
  }

  /**
   * Street No.
   * <p>
   */
  @JsonProperty("street_no")
  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  /**
   * Street Name.
   * <p>
   */
  @JsonProperty("street_name")
  public String getStreetName() {
    return streetName;
  }

  /**
   * Street Name.
   * <p>
   */
  @JsonProperty("street_name")
  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  /**
   * City
   * <p>
   */
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  /**
   * City
   * <p>
   */
  @JsonProperty("city")
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * State
   * <p>
   */
  @JsonProperty("state")
  public StateDTO getState() {
    return state;
  }

  /**
   * State
   * <p>
   */
  @JsonProperty("state")
  public void setState(StateDTO state) {
    this.state = state;
  }

  /**
   * ZIP
   * <p>
   */
  @JsonProperty("zip")
  public String getZip() {
    return zip;
  }

  /**
   * ZIP
   * <p>
   */
  @JsonProperty("zip")
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * ZIP Ext.
   * <p>
   */
  @JsonProperty("zip_ext")
  public String getZipExt() {
    return zipExt;
  }

  /**
   * ZIP Ext.
   * <p>
   */
  @JsonProperty("zip_ext")
  public void setZipExt(String zipExt) {
    this.zipExt = zipExt;
  }

  /**
   * Geographic Region
   * <p>
   */
  @JsonProperty("geo_region")
  public String getGeoRegion() {
    return geoRegion;
  }

  /**
   * Geographic Region
   * <p>
   */
  @JsonProperty("geo_region")
  public void setGeoRegion(String geoRegion) {
    this.geoRegion = geoRegion;
  }

  @JsonProperty("county")
  public SimpleDictionary getCounty() {
    return county;
  }

  @JsonProperty("county")
  public void setCounty(GenericSimpleDictionaryImpl county) {
    this.county = county;
  }

  @JsonProperty("foreign_country")
  public SimpleDictionary getForeignCountry() {
    return foreignCountry;
  }

  @JsonProperty("foreign_country")
  public void setForeignCountry(GenericSimpleDictionaryImpl foreignCountry) {
    this.foreignCountry = foreignCountry;
  }

  /**
   * Foreign ZIP
   * <p>
   */
  @JsonProperty("foreign_zip")
  public String getForeignZip() {
    return foreignZip;
  }

  /**
   * Foreign ZIP
   * <p>
   */
  @JsonProperty("foreign_zip")
  public void setForeignZip(String foreignZip) {
    this.foreignZip = foreignZip;
  }

  /**
   * Foreign Address Description (Province name, etc)
   * <p>
   */
  @JsonProperty("foreign_addr_desc")
  public String getForeignAddrDesc() {
    return foreignAddrDesc;
  }

  /**
   * Foreign Address Description (Province name, etc)
   * <p>
   */
  @JsonProperty("foreign_addr_desc")
  public void setForeignAddrDesc(String foreignAddrDesc) {
    this.foreignAddrDesc = foreignAddrDesc;
  }

  /**
   * Comment
   * <p>
   */
  @JsonProperty("comment")
  public String getComment() {
    return comment;
  }

  /**
   * Comment
   * <p>
   */
  @JsonProperty("comment")
  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String formName() {
    return PH_PAGE_ID_ADDRESS;
  }
}
