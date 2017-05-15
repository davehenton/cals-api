package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.model.fas.ComplaintReportLic802;
import gov.ca.cwds.cals.persistence.dao.ComplaintReportLic802Dao;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintService implements CrudsService {

    private ComplaintReportLic802Dao complaintDao;
    private ComplaintMapper complaintMapper;

    @Inject
    public ComplaintService(ComplaintReportLic802Dao complaintDao,
            ComplaintMapper complaintMapper) {
        this.complaintDao = complaintDao;
        this.complaintMapper = complaintMapper;
    }

    @Override
    public Response find(Serializable complaintId) {
        ComplaintReportLic802 complaint = complaintDao.find(complaintId);
        return complaintMapper.entityToDTO(complaint);
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
