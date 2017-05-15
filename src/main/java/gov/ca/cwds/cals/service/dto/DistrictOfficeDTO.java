package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author CWDS CALS API Team
 */
public class DistrictOfficeDTO extends BaseDTO {

    public DistrictOfficeDTO() {}

    @JsonProperty("number")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "District Office Number", example = "21")
    private String number;

    @JsonProperty("name")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "District Office Name", example = "NO. CAL AC/SC")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
