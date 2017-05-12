package gov.ca.cwds.cals.service;

import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;
import java.time.LocalDate;

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintService implements CrudsService {

    @Override
    public Response find(Serializable serializable) {
        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setId(1234567l);
        complaintDTO.setComplaintDate(LocalDate.of(2004, 2, 13));
        complaintDTO.setAssignedWorker("assigned_worker");
        complaintDTO.setControlNumber("123");
        complaintDTO.setPriorityLevel(3);
        complaintDTO.setStatus("status1");
        complaintDTO.setApprovalDate(LocalDate.of(2004, 3, 15));
        return complaintDTO;
    }

    @Override
    public Response delete(Serializable serializable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response create(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response update(Serializable serializable, Request request) {
        throw new UnsupportedOperationException();
    }

}
