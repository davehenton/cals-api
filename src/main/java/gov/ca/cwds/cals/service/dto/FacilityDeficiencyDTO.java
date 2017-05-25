package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.validation.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static gov.ca.cwds.rest.api.domain.DomainObject.TIME_FORMAT;

/**
 * @author CWDS CALS API Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FacilityDeficiencyDTO extends BaseDTO {
    private static final long serialVersionUID = -3548530142566196513L;

    private String deficiencyType;

    private String deficiencyTypeDescription;

    private LocalDateTime pocDate;

    private String facSectionViolated;

    private String deficiency;

    private String correctionPlan;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeficiencyType() {
        return deficiencyType;
    }

    public void setDeficiencyType(String deficiencyType) {
        this.deficiencyType = deficiencyType;
    }

    public String getDeficiencyTypeDescription() {
        return deficiencyTypeDescription;
    }

    public void setDeficiencyTypeDescription(String deficiencyTypeDescription) {
        this.deficiencyTypeDescription = deficiencyTypeDescription;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
    @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
    public LocalDateTime getPocDate() {
        return pocDate;
    }

    public void setPocDate(LocalDateTime pocDate) {
        this.pocDate = pocDate;
    }

    public String getFacSectionViolated() {
        return facSectionViolated;
    }

    public void setFacSectionViolated(String facSectionViolated) {
        this.facSectionViolated = facSectionViolated;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(String deficiency) {
        this.deficiency = deficiency;
    }

    public String getCorrectionPlan() {
        return correctionPlan;
    }

    public void setCorrectionPlan(String correctionPlan) {
        this.correctionPlan = correctionPlan;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
