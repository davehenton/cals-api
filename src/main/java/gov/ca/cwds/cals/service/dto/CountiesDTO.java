package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.rest.api.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class CountiesDTO extends BaseDTO implements Response {

    private static final long serialVersionUID = 6849073315397309087L;

    private List<CountyDTO> counties = new ArrayList<>();

    public List<CountyDTO> getCounties() {
        return counties;
    }

    public void setCounties(List<CountyDTO> counties) {
        this.counties = new ArrayList<>(counties);
    }
    
}
