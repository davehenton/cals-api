package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;
import gov.ca.cwds.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2160") //reflection equals hashcode is used in superclass
public class HyperlinkDTO extends BaseDTO {

    private static final long serialVersionUID = -9070129634987372402L;

    @RemoveTrailingSpaces
    private String href;

    public HyperlinkDTO() {}

    public HyperlinkDTO(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
