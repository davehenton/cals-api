package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrityForEach;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team.
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResidenceDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = -7762404097883966587L;

  @ApiModelProperty(value = "List of Addresses")
  @Valid
  private List<RFAAddressDTO> addresses;

  @ApiModelProperty(value = "Is Physical Mailing Similar", example = "false")
  private Boolean physicalMailingSimilar;

  @ApiModelProperty(value = "Residence Ownership Type")
  @CheckReferentialIntegrity(enrich = true)
  private ResidenceOwnershipType residenceOwnership;

  @ApiModelProperty(value = "Is Weapon In Home", example = "false")
  private Boolean weaponInHome;

  @ApiModelProperty(value = "Is Body Of Water Exist", example = "true")
  private Boolean bodyOfWaterExist;

  @ApiModelProperty(value = "Body Of Water Description", example = "Description here")
  private String bodyOfWaterDescription;

  @ApiModelProperty(value = "Is Others Using Residence As Mailing", example = "true")
  private Boolean othersUsingResidenceAsMailing;

  @ApiModelProperty(value = "Other People Using Residence As Mailing")
  @Valid
  private Set<PersonNameDTO> otherPeopleUsingResidenceAsMailing;

  @ApiModelProperty(value = "Directions To Home", example = "Directions here")
  private String directionsToHome;

  @ApiModelProperty(value = "Home Languages")
  @CheckReferentialIntegrityForEach(enrich = true)
  private Set<LanguageType> homeLanguages;

  public List<RFAAddressDTO> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<RFAAddressDTO> addresses) {
    this.addresses = addresses;
  }

  public Boolean getPhysicalMailingSimilar() {
    return physicalMailingSimilar;
  }

  public void setPhysicalMailingSimilar(Boolean physicalMailingSimilar) {
    this.physicalMailingSimilar = physicalMailingSimilar;
  }

  public ResidenceOwnershipType getResidenceOwnership() {
    return residenceOwnership;
  }

  public void setResidenceOwnership(ResidenceOwnershipType residenceOwnership) {
    this.residenceOwnership = residenceOwnership;
  }

  public Boolean getWeaponInHome() {
    return weaponInHome;
  }

  public void setWeaponInHome(Boolean weaponInHome) {
    this.weaponInHome = weaponInHome;
  }

  public Boolean getBodyOfWaterExist() {
    return bodyOfWaterExist;
  }

  public void setBodyOfWaterExist(Boolean bodyOfWaterExist) {
    this.bodyOfWaterExist = bodyOfWaterExist;
  }

  public String getBodyOfWaterDescription() {
    return bodyOfWaterDescription;
  }

  public void setBodyOfWaterDescription(String bodyOfWaterDescription) {
    this.bodyOfWaterDescription = bodyOfWaterDescription;
  }

  public Boolean getOthersUsingResidenceAsMailing() {
    return othersUsingResidenceAsMailing;
  }

  public void setOthersUsingResidenceAsMailing(Boolean othersUsingResidenceAsMailing) {
    this.othersUsingResidenceAsMailing = othersUsingResidenceAsMailing;
  }

  public Set<PersonNameDTO> getOtherPeopleUsingResidenceAsMailing() {
    return otherPeopleUsingResidenceAsMailing;
  }

  public void setOtherPeopleUsingResidenceAsMailing(
      Set<PersonNameDTO> otherPeopleUsingResidenceAsMailing) {
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

  public void setHomeLanguages(Set<LanguageType> homeLanguages) {
    this.homeLanguages = homeLanguages;
  }
}
