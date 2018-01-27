package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.dto.BaseDTO;
import gov.ca.cwds.rest.api.Response;
import java.util.Set;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2160") //reflection equals hashcode is used in superclass
public class ComplaintsDTO extends BaseDTO implements Response {

    private static final long serialVersionUID = -5045346074331689606L;

    @JsonProperty("complaints")
    private Set<ComplaintDTO> complaints;

    public Set<ComplaintDTO> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<ComplaintDTO> complaints) {
        this.complaints = complaints;
    }

}
