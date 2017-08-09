package gov.ca.cwds.cals.service.dto.rfa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrity;
import gov.ca.cwds.cals.service.validation.field.CheckReferentialIntegrityForEach;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;

/**
 * @author CWDS CALS API Team.
 */
@SuppressWarnings("squid:S2160") //Default reflection hashcode and equals resides in BaseDTO
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResidenceDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = -7762404097883966587L;

  public static final String RESIDENTIAL = "Residential";

  @ApiModelProperty(value = "List of Addresses")
  @Valid
  private List<RFAAddressDTO> addresses = new ArrayList<>();

  @ApiModelProperty(value = "Is Physical Mailing Similar", example = "false")
  private boolean physicalMailingSimilar;

  @ApiModelProperty(value = "Residence Ownership Type")
  @CheckReferentialIntegrity
  private ResidenceOwnershipType residenceOwnership;

  @ApiModelProperty(value = "Is Weapon In Home", example = "false")
  private boolean weaponInHome;

  @ApiModelProperty(value = "Is Body Of Water Exist", example = "true")
  private boolean bodyOfWaterExist;

  @ApiModelProperty(value = "Body Of Water Description", example = "Description here")
  private String bodyOfWaterDescription;

  @ApiModelProperty(value = "Is Others Using Residence As Mailing", example = "true")
  private boolean othersUsingResidenceAsMailing;

  @ApiModelProperty(value = "Other People Using Residence As Mailing")
  @Valid
  private Set<PersonNameDTO> otherPeopleUsingResidenceAsMailing;

  @ApiModelProperty(value = "Directions To Home", example = "Directions here")
  private String directionsToHome;

  @ApiModelProperty(value = "Home Languages")
  @CheckReferentialIntegrityForEach
  private Set<LanguageType> homeLanguages;

  public List<RFAAddressDTO> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<RFAAddressDTO> addresses) {
    this.addresses = addresses;
  }

  public boolean isPhysicalMailingSimilar() {
    return physicalMailingSimilar;
  }

  public void setPhysicalMailingSimilar(boolean physicalMailingSimilar) {
    this.physicalMailingSimilar = physicalMailingSimilar;
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

  public void setHomeLanguages(
      Set<LanguageType> homeLanguages) {
    this.homeLanguages = homeLanguages;
  }

  @JsonIgnore
  public RFAAddressDTO getResidentialAddress() {
    Optional<RFAAddressDTO> residentialAddress =
        this.addresses
            .stream()
            .filter(address -> RESIDENTIAL.equals(address.getType().getValue()))
            .findAny();
    return residentialAddress
        .orElseThrow(() -> new IllegalStateException("Residential address must be present"));
  }

  @JsonIgnore
  public String getResidentialStreet() {
    String streetAddress = getResidentialAddress().getStreetAddress();
    return streetAddress.substring(streetAddress.indexOf(' ') + 1);
  }

  @JsonIgnore
  public String getResidentialStreetNumber() {
    String streetAddress = getResidentialAddress().getStreetAddress();
    return streetAddress.substring(0, streetAddress.indexOf(' '));
  }


}
