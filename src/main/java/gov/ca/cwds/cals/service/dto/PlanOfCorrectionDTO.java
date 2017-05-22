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
public class PlanOfCorrectionDTO extends BaseDTO {
    private static final long serialVersionUID = 5725428128974549904L;

    private LocalDateTime pocDueDate;

    private String pocSectionViolated;

    private String pocCorrectionPlan;

    private LocalDateTime pocDateCleared;

    private String pocComment;

    private String pomCorrectionPlanCont;

    private String pomCommentCont;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
    @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
    public LocalDateTime getPocDueDate() {
        return pocDueDate;
    }

    public void setPocDueDate(LocalDateTime pocDueDate) {
        this.pocDueDate = pocDueDate;
    }

    public String getPocSectionViolated() {
        return pocSectionViolated;
    }

    public void setPocSectionViolated(String pocSectionViolated) {
        this.pocSectionViolated = pocSectionViolated;
    }

    public String getPocCorrectionPlan() {
        return pocCorrectionPlan;
    }

    public void setPocCorrectionPlan(String pocCorrectionPlan) {
        this.pocCorrectionPlan = pocCorrectionPlan;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
    @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
    public LocalDateTime getPocDateCleared() {
        return pocDateCleared;
    }

    public void setPocDateCleared(LocalDateTime pocDateCleared) {
        this.pocDateCleared = pocDateCleared;
    }

    public String getPocComment() {
        return pocComment;
    }

    public void setPocComment(String pocComment) {
        this.pocComment = pocComment;
    }

    public String getPomCorrectionPlanCont() {
        return pomCorrectionPlanCont;
    }

    public void setPomCorrectionPlanCont(String pomCorrectionPlanCont) {
        this.pomCorrectionPlanCont = pomCorrectionPlanCont;
    }

    public String getPomCommentCont() {
        return pomCommentCont;
    }

    public void setPomCommentCont(String pomCommentCont) {
        this.pomCommentCont = pomCommentCont;
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
