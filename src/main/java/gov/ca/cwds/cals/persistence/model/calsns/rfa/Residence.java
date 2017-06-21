package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;

/**
 * @author CWDS CALS API Team.
 */
public class Residence extends BaseDTO implements Response {

  private static final long serialVersionUID = 1L;

  @JsonProperty("physical_address")
  @ApiModelProperty(required = false, readOnly = false, value = "Physical Address")
  private Address physicalAddress;

  @JsonProperty("mailing_address")
  @ApiModelProperty(required = false, readOnly = false, value = "Mailing Address")
  private Address mailingAddress;

  @JsonProperty("is_physical_mailing_similar")
  @ApiModelProperty(required = false, readOnly = false, value = "Is Physical Mailing Similar", example = "false")
  private boolean isPhysicalMailingSimilar;

  @JsonProperty("residence_ownership")
  @ApiModelProperty(required = false, readOnly = false, value = "Residence Ownership Type")
  private ResidenceOwnershipType residenceOwnership;

  @JsonProperty("is_weapon_in_home")
  @ApiModelProperty(required = false, readOnly = false, value = "Is Weapon In Home", example = "false")
  private boolean isWeaponInHome;

  @JsonProperty("is_body_of_water_exist")
  @ApiModelProperty(required = false, readOnly = false, value = "Is Body Of Water Exist", example = "true")
  private boolean isBodyOfWaterExist;

  @JsonProperty("body_of_water_description")
  @ApiModelProperty(required = false, readOnly = false, value = "Body Of Water Description", example = "Description here")
  private String bodyOfWaterDescription;

  @JsonProperty("is_others_using_residence_as_mailing")
  @ApiModelProperty(required = false, readOnly = false, value = "Is Others Using Residence As Mailing", example = "true")
  private boolean isOthersUsingResidenceAsMailing;

  @JsonProperty("other_people_using_residence_as_mailing")
  @ApiModelProperty(required = false, readOnly = false, value = "Other People Using Residence As Mailing")
  private Set<PersonName> otherPeopleUsingResidenceAsMailing;

  @JsonProperty("directions_to_home")
  @ApiModelProperty(required = false, readOnly = false, value = "Directions To Home", example = "Directions here")
  private String directionsToHome;

  @JsonProperty("home_languages")
  @ApiModelProperty(required = false, readOnly = false, value = "Home Languages")
  private Set<LanguageType> homeLanguages;
}
