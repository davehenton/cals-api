package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.model.fas.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.FacilityComplaintParameterObject;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;
import java.util.Optional;

import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.FACILITY_NOT_FOUND_BY_ID;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.COMPLAINT_NOT_FOUND_BY_ID;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintService implements CrudsService {

    private LisFacFileDao lisFacFileDao;
    private ComplaintMapper complaintMapper;

    @Inject
    public ComplaintService(LisFacFileDao lisFacFileDao,
            ComplaintMapper complaintMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.complaintMapper = complaintMapper;
    }

    @Override
    public Response find(Serializable parametersObject) {
        FacilityComplaintParameterObject parameterObject = null;
        if (parametersObject instanceof FacilityComplaintParameterObject) {
            parameterObject = (FacilityComplaintParameterObject) parametersObject;
            LisFacFile facility = lisFacFileDao.find(parameterObject.getFacilityId());
            if (facility == null) {
                throw new UserFriendlyException(FACILITY_NOT_FOUND_BY_ID, NOT_FOUND);
            }
            final FacilityComplaintParameterObject finalParameterObject = parameterObject;
            Optional<ComplaintReportLic802> complaintReportLic802 = facility.getComplaints().stream()
                    .filter((complaint) -> complaint.getOriginalunidkey().equals(finalParameterObject.getComplaintId()))
                    .findFirst();
            return complaintMapper.entityToDTO(complaintReportLic802
                    .orElseThrow(() -> new UserFriendlyException(COMPLAINT_NOT_FOUND_BY_ID, NOT_FOUND)));
        }
        throw new IllegalStateException("FacilityComplaintParameterObject is expected here");
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
