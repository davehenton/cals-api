package gov.ca.cwds.cals.service.tracking.builder;

import com.fasterxml.jackson.databind.JsonNode;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.rest.api.ApiException;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TrackingBuilder {

  /**
   * @param rfa1a rfa form
   * @param templates tracking templates for current user's county
   * @param defaultTemplates default tracking templates
   * @return tracking json for provided rfa
   */
  public JsonNode build(RFA1aForm rfa1a,
      List<TrackingTemplate> templates,
      List<TrackingTemplate> defaultTemplates) {
    try {
      Binding binding = new Binding();
      binding.setVariable("templates", templates);
      binding.setVariable("defaultTemplates", defaultTemplates);
      binding.setVariable("rfa1a", rfa1a);
      GroovyShell groovyShell = new GroovyShell(binding);
      try (InputStream is = getClass().getResourceAsStream("/tracking/tracking.groovy")) {
        return (JsonNode) groovyShell.evaluate(new InputStreamReader(is));
      }
    } catch (Exception e) {
      throw new ApiException(e);
    }
  }

}
