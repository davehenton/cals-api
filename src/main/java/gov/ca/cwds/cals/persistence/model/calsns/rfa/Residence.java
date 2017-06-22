package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.LanguageType;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.ResidenceOwnershipType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;

/**
 * @author CWDS CALS API Team.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Residence extends BaseDTO implements Response {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(required = false, readOnly = false, value = "Physical Address")
  private Address physicalAddress;

  @ApiModelProperty(required = false, readOnly = false, value = "Mailing Address")
  private Address mailingAddress;

  @ApiModelProperty(required = false, readOnly = false, value = "Is Physical Mailing Similar", example = "false")
  private boolean isPhysicalMailingSimilar;

  @ApiModelProperty(required = false, readOnly = false, value = "Residence Ownership Type")
  private ResidenceOwnershipType residenceOwnership;

  @ApiModelProperty(required = false, readOnly = false, value = "Is Weapon In Home", example = "false")
  private boolean isWeaponInHome;

  @ApiModelProperty(required = false, readOnly = false, value = "Is Body Of Water Exist", example = "true")
  private boolean isBodyOfWaterExist;

  @ApiModelProperty(required = false, readOnly = false, value = "Body Of Water Description", example = "Description here")
  private String bodyOfWaterDescription;

  @ApiModelProperty(required = false, readOnly = false, value = "Is Others Using Residence As Mailing", example = "true")
  private boolean isOthersUsingResidenceAsMailing;

  @ApiModelProperty(required = false, readOnly = false, value = "Other People Using Residence As Mailing")
  private Set<PersonName> otherPeopleUsingResidenceAsMailing;

  @ApiModelProperty(required = false, readOnly = false, value = "Directions To Home", example = "Directions here")
  private String directionsToHome;

  @ApiModelProperty(required = false, readOnly = false, value = "Home Languages")
  private Set<LanguageType> homeLanguages;
}
