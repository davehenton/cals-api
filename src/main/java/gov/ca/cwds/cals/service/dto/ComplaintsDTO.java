package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.api.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintsDTO extends BaseDTO implements Response {

    @JsonProperty("complaints")
    private List<ComplaintDTO> complaints = new ArrayList<>();

    public List<ComplaintDTO> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ComplaintDTO> complaints) {
        this.complaints = complaints;
    }

}
