package gov.ca.cwds.cals.service.dto.tracking;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.validation.Date;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingItemDTO extends BaseItemDTO {

  private final static long serialVersionUID = 1963821034441888055L;

  @JsonProperty("expiration_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @Date
  @ApiModelProperty(value = "yyyy-MM-dd", example = "2000-01-01")
  private LocalDate expirationDate;


  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

}
