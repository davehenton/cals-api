package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import java.net.URI;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2 Team
 */
public class RFA1aPDFGenerationService {

  private static final Logger LOG = LoggerFactory.getLogger(RFA1aPDFGenerationService.class);

  private static final String RFA_1A_DMS_FORM_TEMPLATE_NAME = "cals-rfa-1a-form-template";
  private static final String RFA_1A_DMS_FORM_MAPPING_NAME = "cals-rfa-1a-form-mapping";

  @Inject
  @Named("dms.uri")
  private String dmsURI;

  @Inject
  private RFA1aFormService rfa1aFormService;

  @Inject
  private Client client;

  public String generatePDF(Long formId) {
    String docId = null;
    RFA1aFormDTO rfa1aFormDTO = rfa1aFormService.find(new RFA1aFormsParameterObject(formId, true));
    if (rfa1aFormDTO != null) {
      docId = generatePDF(rfa1aFormDTO);
    }
    return docId;
  }

  public Response getFormPdf(String documentId) {
    long startTime = System.currentTimeMillis();
    Response response = loadPDFFormDMS(documentId);
    LOG.info("Getting PDF time: {}", (System.currentTimeMillis() - startTime));
    return response;
  }

  private String generatePDF(RFA1aFormDTO rfa1aFormDTO) {
    long startTime = System.currentTimeMillis();
    String documentId = callDmsForGeneratePDF(rfa1aFormDTO);
    LOG.info("Generating PDF time: {}", (System.currentTimeMillis() - startTime));
    return documentId;
  }

  private String callDmsForGeneratePDF(RFA1aFormDTO dto) {
    URI uri = UriBuilder.fromUri(dmsURI)
        .path("/documents/templates")
        .path("/" + RFA_1A_DMS_FORM_TEMPLATE_NAME)
        .path("/documents")
        .queryParam("mapping", RFA_1A_DMS_FORM_MAPPING_NAME)
        .build();
    client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
    client.property(ClientProperties.READ_TIMEOUT,    20000);
    return client.target(uri).request()
        .post(Entity.entity(dto, MediaType.APPLICATION_JSON_TYPE), String.class);
  }

  private Response loadPDFFormDMS(String documentId) {
    URI uri = UriBuilder.fromUri(dmsURI)
        .path("/documents")
        .path("/" + documentId)
        .build();
    return client.target(uri).request().get();
  }

}
