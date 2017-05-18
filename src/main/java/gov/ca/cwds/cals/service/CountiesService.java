package gov.ca.cwds.cals.service;

import gov.ca.cwds.cals.service.dto.CountiesDTO;
import gov.ca.cwds.cals.service.dto.CountyDTO;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author CWDS CALS API Team
 */
public class CountiesService implements CrudsService {



    @Override
    public Response find(Serializable serializable) {

        CountiesDTO counties = new CountiesDTO();

        // Fake implementation
        // TODO rework

        ArrayList<CountyDTO> countyDTOS = new ArrayList<>();

        CountyDTO dto0 = new CountyDTO();
        dto0.setCode("0");
        dto0.setDescription("Desc0");

        CountyDTO dto1 = new CountyDTO();
        dto1.setCode("1");
        dto1.setDescription("Desc1");

        CountyDTO dto2 = new CountyDTO();
        dto2.setCode("2");
        dto2.setDescription("Desc2");

        countyDTOS.add(dto0);
        countyDTOS.add(dto1);
        countyDTOS.add(dto2);

        counties.setCounties(countyDTOS);

        return counties;
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
