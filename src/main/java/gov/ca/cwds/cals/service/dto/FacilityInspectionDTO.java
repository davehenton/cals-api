package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.validation.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;
import static gov.ca.cwds.rest.api.domain.DomainObject.TIME_FORMAT;

/**
 * @author CWDS CALS API Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FacilityInspectionDTO extends BaseDTO implements Response {

    private static final long serialVersionUID = 3722343378274527122L;
    
    private String id;

    private String href;

    private LocalDateTime representativeSignatureDate;

    private LocalDateTime form809PrintDate;

    private List<PlanOfCorrectionDTO> pocs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
    @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
    public LocalDateTime getRepresentativeSignatureDate() {
        return representativeSignatureDate;
    }

    public void setRepresentativeSignatureDate(LocalDateTime representativeSignatureDate) {
        this.representativeSignatureDate = representativeSignatureDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT + " " + TIME_FORMAT)
    @Date(format = DATE_FORMAT + " " + TIME_FORMAT)
    public LocalDateTime getForm809PrintDate() {
        return form809PrintDate;
    }

    public void setForm809PrintDate(LocalDateTime form809PrintDate) {
        this.form809PrintDate = form809PrintDate;
    }

    public List<PlanOfCorrectionDTO> getPocs() {
        return pocs;
    }

    public void setPocs(List<PlanOfCorrectionDTO> pocs) {
        this.pocs = pocs;
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
