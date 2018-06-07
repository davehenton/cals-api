package gov.ca.cwds.cals.service.tracking.builder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.cals.service.dto.tracking.TrackingDocumentsDTO;
import gov.ca.cwds.rest.api.ApiException;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TrackingDocumentsBuilder {

  private ObjectMapper objectMapper;

  public TrackingDocumentsBuilder() {
    objectMapper = new ObjectMapper();
    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  }

  /**
   * @param rfa1a rfa form
   * @param templates tracking templates for current user's county
   * @param defaultTemplates default tracking templates
   * @return tracking json for provided rfa
   */
  public TrackingDocumentsDTO build(RFA1aForm rfa1a,
      List<TrackingTemplate> templates,
      List<TrackingTemplate> defaultTemplates) {
    try {
      Binding binding = new Binding();
      binding.setVariable("templates", templates);
      binding.setVariable("defaultTemplates", defaultTemplates);
      binding.setVariable("rfa1a", rfa1a);
      GroovyShell groovyShell = new GroovyShell(binding);
      try (InputStream is = getClass().getResourceAsStream("/tracking/tracking.groovy")) {
        JsonNode trackingDocumentsJson = (JsonNode) groovyShell.evaluate(new InputStreamReader(is));
        return objectMapper.convertValue(trackingDocumentsJson, TrackingDocumentsDTO.class);
      }
    } catch (Exception e) {
      throw new ApiException(e);
    }
  }

  public TrackingDocumentsDTO build(JsonNode jsonNode) {
    return objectMapper.convertValue(jsonNode, TrackingDocumentsDTO.class);
  }

  public JsonNode buildJson(TrackingDocumentsDTO trackingDocumentsDTO) {
    return objectMapper.convertValue(trackingDocumentsDTO, JsonNode.class);
  }

}
