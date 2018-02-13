
package gov.ca.cwds.cals.service.dto.placementhome.identification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.SimpleDictionary;


/**
 * Placement Home ID Disaster Emergency Contact Information
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "phone",
    "alt_phone",
    "email",
    "street_no",
    "street_name",
    "city",
    "state",
    "zip",
    "zip_ext",
    "foreign_country",
    "foreign_zip",
    "foreign_addr_desc"
})
public class EmergencyContactDTO {

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  private String name;
  @JsonProperty("phone")
  private PhoneDTO phone;
  @JsonProperty("alt_phone")
  private PhoneDTO altPhone;
  /**
   * E-mail address
   * <p>
   */
  @JsonProperty("email")
  private String email;
  /**
   * Street No.
   * <p>
   */
  @JsonProperty("street_no")
  private String streetNo;
  /**
   * Street Name.
   * <p>
   */
  @JsonProperty("street_name")
  private String streetName;
  /**
   * City
   * <p>
   */
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
  @JsonProperty("foreign_country")
  private SimpleDictionary foreignCountry;
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
   * Name
   * <p>
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * Name
   * <p>
   */
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("phone")
  public PhoneDTO getPhone() {
    return phone;
  }

  @JsonProperty("phone")
  public void setPhone(PhoneDTO phone) {
    this.phone = phone;
  }

  @JsonProperty("alt_phone")
  public PhoneDTO getAltPhone() {
    return altPhone;
  }

  @JsonProperty("alt_phone")
  public void setAltPhone(PhoneDTO altPhone) {
    this.altPhone = altPhone;
  }

  /**
   * E-mail address
   * <p>
   */
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  /**
   * E-mail address
   * <p>
   */
  @JsonProperty("email")
  public void setEmail(String email) {
    this.email = email;
  }

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

  @JsonProperty("foreign_country")
  public SimpleDictionary getForeignCountry() {
    return foreignCountry;
  }

  @JsonProperty("foreign_country")
  public void setForeignCountry(SimpleDictionary foreignCountry) {
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

}
