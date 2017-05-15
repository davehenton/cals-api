package gov.ca.cwds.cals.service;

import gov.ca.cwds.cals.service.dto.ComplaintDTO;
import gov.ca.cwds.cals.service.dto.ComplaintsDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintsCollectionService implements CrudsService {

    @Override
    public Response find(Serializable serializable) {
        ComplaintDTO complaint1 = new ComplaintDTO();
        complaint1.setId(1234567l);
        complaint1.setComplaintDate(LocalDate.of(2004, 2, 13));
        complaint1.setAssignedWorker("assigned_worker");
        complaint1.setControlNumber("123");
        complaint1.setPriorityLevel(3);
        complaint1.setStatus("status1");
        complaint1.setApprovalDate(LocalDate.of(2004, 3, 15));

        ComplaintDTO complaint2 = new ComplaintDTO();
        complaint2.setId(1234567l);
        complaint2.setComplaintDate(LocalDate.of(2005, 2, 13));
        complaint2.setAssignedWorker("assigned_worker2");
        complaint2.setControlNumber("456");
        complaint2.setPriorityLevel(2);
        complaint2.setStatus("status2");
        complaint2.setApprovalDate(LocalDate.of(2005, 5, 11));

        ComplaintsDTO complaintsDTO = new ComplaintsDTO();
        complaintsDTO.getComplaints().add(complaint1);
        complaintsDTO.getComplaints().add(complaint2);

        return complaintsDTO;
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
