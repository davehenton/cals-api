package gov.ca.cwds.cals.service.dto.placementhome.otherchildren;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cals.service.dto.formsapi.FormNameAware;
import gov.ca.cwds.dto.BaseDTO;
import java.util.ArrayList;

/**
 * @author CWDS TPT-2 Team
 */
@SuppressWarnings({"squid:S3437", "squid:S2160"}) //LocalDate is serializable
public class OtherChildrenDTO extends BaseDTO implements FormNameAware {
  private static final String PH_PAGE_OTHER_CHILDREN= "PH_page_Other_Children";
  
  @JsonProperty("children")
  private ArrayList<OtherChildDTO> children;

  public ArrayList<OtherChildDTO> getChildren() {
    return children;
  }

  public void setChildren(
      ArrayList<OtherChildDTO> children) {
    this.children = children;
  }

  @Override
  public String formName() {
    return PH_PAGE_OTHER_CHILDREN;
  }
}
