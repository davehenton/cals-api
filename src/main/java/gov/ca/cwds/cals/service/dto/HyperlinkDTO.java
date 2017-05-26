package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.cals.service.mapper.RemoveTrailingSpaces;

/**
 * @author CWDS CALS API Team
 */

public class HyperlinkDTO extends BaseDTO {

    public HyperlinkDTO() {}

    @RemoveTrailingSpaces
    private String href;

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
