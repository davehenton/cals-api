package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.api.Response;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.util.List;

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

    public LocalDateTime getRepresentativeSignatureDate() {
        return representativeSignatureDate;
    }

    public void setRepresentativeSignatureDate(LocalDateTime representativeSignatureDate) {
        this.representativeSignatureDate = representativeSignatureDate;
    }

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
