package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class FacilityTypeDTO implements Serializable {

    public FacilityTypeDTO() {}

    @JsonProperty("code")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Facility Type Code", example = "810")
    private String code;

    @JsonProperty("description")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Facility Type Description", example = "FAMILY DAY CARE")
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacilityTypeDTO that = (FacilityTypeDTO) o;

        if (!code.equals(that.code)) {
            return false;
        }
        if (!description.equals(that.description)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "FacilityTypeDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
