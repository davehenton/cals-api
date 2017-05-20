package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileDao;
import gov.ca.cwds.cals.service.mapper.ComplaintMapper;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintsCollectionService implements CrudsService {

    private LisFacFileDao lisFacFileDao;
    private ComplaintMapper complaintMapper;

    @Inject
    public ComplaintsCollectionService(LisFacFileDao lisFacFileDao,
            ComplaintMapper complaintMapper) {
        this.lisFacFileDao = lisFacFileDao;
        this.complaintMapper = complaintMapper;
    }

    @Override
    public Response find(Serializable facilityId) {
//        LisFacFile lisFacFile = lisFacFileDao.find(facilityId);
//        return complaintMapper.complaintsListToComplaintsDTOList(lisFacFile);
        return null;
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
