package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class DictionaryDTO implements Serializable {

    public DictionaryDTO() {}

    @JsonProperty("code")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Dictionary Status Code", example = "1")
    private String code;

    @JsonProperty("description")
    @NotNull
    @ApiModelProperty(required = true, readOnly = false, value = "Dictionary Status Description", example = "Some dictionary item description")
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
        return getClass().getSimpleName() + "{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
