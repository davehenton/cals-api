package gov.ca.cwds.cals.web.rest.rfa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leonid.marushevskiy on 6/30/2017.
 */
public class LoggingFilter implements ClientRequestFilter, ClientResponseFilter {
  private final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    LOG.info("Test URL: {}, API Request: {}", requestContext.getUri(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestContext.getEntity()));
  }

  @Override
  public void filter(ClientRequestContext clientRequestContext,
      ClientResponseContext clientResponseContext) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    LOG.info("API Response: {}, Status: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientResponseContext.getEntityStream()), clientResponseContext.getStatus());
  }
}
