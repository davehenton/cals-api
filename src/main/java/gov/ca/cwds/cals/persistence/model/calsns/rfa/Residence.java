package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Residence extends BaseDTO implements Request, Response {

  private static final long serialVersionUID = 1L;

  @JsonProperty("physical_address")
  @ApiModelProperty(value = "Physical Address")
  private Address physicalAddress;

  @JsonProperty("is_physical_mailing_similar")
  @ApiModelProperty(value = "Is Physical Mailing Similar", example = "false")
  private boolean physicalMailingSimilar;

  @JsonProperty("mailing_address")
  @ApiModelProperty(value = "Mailing Address")
  private Address mailingAddress;

  @JsonProperty("residence_ownership")
  @ApiModelProperty(value = "Residence Ownership Type")
  private ResidenceOwnershipType residenceOwnership;

  @JsonProperty("is_weapon_in_home")
  @ApiModelProperty(value = "Is Weapon In Home", example = "false")
  private boolean weaponInHome;

  @JsonProperty("is_body_of_water_exist")
  @ApiModelProperty(value = "Is Body Of Water Exist", example = "true")
  private boolean bodyOfWaterExist;

  @JsonProperty("body_of_water_description")
  @ApiModelProperty(value = "Body Of Water Description", example = "Description here")
  private String bodyOfWaterDescription;

  @JsonProperty("is_others_using_residence_as_mailing")
  @ApiModelProperty(value = "Is Others Using Residence As Mailing", example = "true")
  private boolean othersUsingResidenceAsMailing;

  @JsonProperty("other_people_using_residence_as_mailing")
  @ApiModelProperty(value = "Other People Using Residence As Mailing")
  private Set<PersonName> otherPeopleUsingResidenceAsMailing;

  @JsonProperty("directions_to_home")
  @ApiModelProperty(value = "Directions To Home", example = "Directions here")
  private String directionsToHome;

  @JsonProperty("home_languages")
  @ApiModelProperty(value = "Home Languages")
  private Set<LanguageType> homeLanguages;

  public Address getPhysicalAddress() {
    return physicalAddress;
  }

  public void setPhysicalAddress(Address physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

  public boolean isPhysicalMailingSimilar() {
    return physicalMailingSimilar;
  }

  public void setPhysicalMailingSimilar(boolean physicalMailingSimilar) {
    this.physicalMailingSimilar = physicalMailingSimilar;
  }

  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(Address mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public ResidenceOwnershipType getResidenceOwnership() {
    return residenceOwnership;
  }

  public void setResidenceOwnership(
      ResidenceOwnershipType residenceOwnership) {
    this.residenceOwnership = residenceOwnership;
  }

  public boolean isWeaponInHome() {
    return weaponInHome;
  }

  public void setWeaponInHome(boolean weaponInHome) {
    this.weaponInHome = weaponInHome;
  }

  public boolean isBodyOfWaterExist() {
    return bodyOfWaterExist;
  }

  public void setBodyOfWaterExist(boolean bodyOfWaterExist) {
    this.bodyOfWaterExist = bodyOfWaterExist;
  }

  public String getBodyOfWaterDescription() {
    return bodyOfWaterDescription;
  }

  public void setBodyOfWaterDescription(String bodyOfWaterDescription) {
    this.bodyOfWaterDescription = bodyOfWaterDescription;
  }

  public boolean isOthersUsingResidenceAsMailing() {
    return othersUsingResidenceAsMailing;
  }

  public void setOthersUsingResidenceAsMailing(boolean othersUsingResidenceAsMailing) {
    this.othersUsingResidenceAsMailing = othersUsingResidenceAsMailing;
  }

  public Set<PersonName> getOtherPeopleUsingResidenceAsMailing() {
    return otherPeopleUsingResidenceAsMailing;
  }

  public void setOtherPeopleUsingResidenceAsMailing(
      Set<PersonName> otherPeopleUsingResidenceAsMailing) {
    this.otherPeopleUsingResidenceAsMailing = otherPeopleUsingResidenceAsMailing;
  }

  public String getDirectionsToHome() {
    return directionsToHome;
  }

  public void setDirectionsToHome(String directionsToHome) {
    this.directionsToHome = directionsToHome;
  }

  public Set<LanguageType> getHomeLanguages() {
    return homeLanguages;
  }

  public void setHomeLanguages(
      Set<LanguageType> homeLanguages) {
    this.homeLanguages = homeLanguages;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
