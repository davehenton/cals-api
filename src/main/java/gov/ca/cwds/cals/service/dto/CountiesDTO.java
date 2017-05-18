package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class CountiesDTO extends BaseDTO implements Request, Response {

    private int count;

    private List<CountyDTO> counties = new ArrayList();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CountyDTO> getCounties() {
        return counties;
    }

    public void setCounties(List<CountyDTO> counties) {
        this.counties = counties;
    }
    
}
