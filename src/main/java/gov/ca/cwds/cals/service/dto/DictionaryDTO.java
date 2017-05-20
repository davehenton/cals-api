package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;

/**
 * @author CWDS CALS API Team
 */

public class DictionaryDTO extends BaseDTO {

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
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
