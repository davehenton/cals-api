package gov.ca.cwds.cals.service.dto;

/**
 * @author CWDS CALS API Team
 */

public class HyperlinkDTO extends BaseDTO {

    public HyperlinkDTO() {}

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
