package gov.ca.cwds.cals.service.validation.business;

import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import java.util.HashSet;
import java.util.Set;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author CWDS CALS API Team
 */

public class DroolsService {

  public Set<String> validate(Object obj, DroolsValidationConfiguration<?> configuration) {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    KieSession kSession = null;
    try {
      kSession = kc.newKieSession(configuration.getDroolsSessionName());
      kSession.insert(obj);
      Set<String> validationMessages = new HashSet<>();
      kSession.setGlobal("validationMessages", validationMessages);
      kSession.getAgenda().getAgendaGroup(configuration.getAgendaGroup()).setFocus();
      kSession.fireAllRules();
      return validationMessages;
    } finally {
      if (kSession != null) {
        kSession.dispose();
      }
    }
  }


}
