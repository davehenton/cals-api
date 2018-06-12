package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import gov.ca.cwds.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the Phone entity.
 */

public class PersonPhoneDTO extends BaseDTO {

    private static final long serialVersionUID = 845164274506455774L;

    @JsonInclude(Include.NON_NULL)
    private Long id;

    @RemoveTrailingSpaces
    @JsonProperty("relation")
    @ApiModelProperty(required = false, value = "Phone Relation", example = "Alternative")
    private String relation;

    @RemoveTrailingSpaces
    @JsonProperty("type")
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(required = false, value = "Phone type", example = "Cell")
    private String type;

    @RemoveTrailingSpaces
    @NotNull
    @Size(max = 16)
    @JsonProperty("number")
    @ApiModelProperty(required = true, value = "Phone number", example = "(494) 823-8588")
    private String number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonPhoneDTO personPhoneDTO = (PersonPhoneDTO) o;

        return Objects.equals(id, personPhoneDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
