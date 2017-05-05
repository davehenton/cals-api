package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class FacilityStatusDTO implements Serializable {

    public FacilityStatusDTO() {}

    @JsonProperty("code")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Facility Status Code", example = "3")
    private String code;

    @JsonProperty("description")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Facility Status Description", example = "LICENSED")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FacilityStatusDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
