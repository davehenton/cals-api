package gov.ca.cwds.cals.service.tracking.builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.rest.api.ApiException;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class TrackingBuilder {

  public JsonNode build(RFA1aForm rfa1a, List<TrackingTemplate> templates) {
    try {
      Binding binding = new Binding();
      binding.setVariable("templates", templates);
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
