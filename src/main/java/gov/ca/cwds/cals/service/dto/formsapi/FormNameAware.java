package gov.ca.cwds.cals.service.dto.formsapi;

import org.apache.commons.lang3.NotImplementedException;

/**
 * @author CWDS TPT-2 Team
 */
@FunctionalInterface
public interface FormNameAware {

  String formName();
  
  default String formVersion() {
    throw new NotImplementedException("Not implemented yet");
  }
}
