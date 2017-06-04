package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.cms.Client;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.rest.api.Response;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {
    private ClientDao clientDao;
    private FacilityChildMapper facilityChildMapper;

    @Inject
    public FacilityChildCollectionService(ClientDao clientDao, FacilityChildMapper facilityChildMapper) {
        this.clientDao = clientDao;
        this.facilityChildMapper = facilityChildMapper;
    }

    @Override
    public Response find(Serializable params) {
        Response resp = null;
        FacilityChildParameterObject parameterObject = (FacilityChildParameterObject) params;
        List<Client> clients = clientDao.findAll(parameterObject);
        if (!CollectionUtils.isEmpty(clients)) {
            List<FacilityChildDTO> facilityChildDTOs = facilityChildMapper.toFacilityChildDTO(clients);
            FacilityChildrenDTO facilityChildrenDTO = new FacilityChildrenDTO();
            facilityChildrenDTO.setChildren(facilityChildDTOs);
            resp = facilityChildrenDTO;
        }
        return resp;
    }
}
