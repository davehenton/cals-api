package gov.ca.cwds.cals.service.dto;

import java.io.Serializable;

/**
 * @author CWDS CALS API Team
 */

public class HyperlinkDTO implements Serializable {

    public HyperlinkDTO(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    private String href;

}
