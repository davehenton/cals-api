package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author CWDS CALS API Team
 */
public class CountyDTO extends DictionaryDTO {


    private String lisCode;

    @ApiModelProperty(readOnly = true, value = "LIS (legacy) Dictionary Code", example = "01")
    @JsonProperty("lis_code")
    public String getLisCode() {
        return lisCode;
    }

    public void setLisCode(String lisCode) {
        this.lisCode = lisCode;
    }
}
