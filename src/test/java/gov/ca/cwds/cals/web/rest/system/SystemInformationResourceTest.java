package gov.ca.cwds.cals.web.rest.system;

import gov.ca.cwds.cals.BaseCalsApiIntegrationTest;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.system.HealthCheckResultDTO;
import gov.ca.cwds.cals.service.dto.system.SystemInformationDTO;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SystemInformationResourceTest extends BaseCalsApiIntegrationTest {

  @Test
  public void testSystemInformationGet() throws IOException {
    SystemInformationDTO systemInformationDTO = clientTestRule.target(Constants.API.SYSTEM_INFORMATION).
        request(MediaType.APPLICATION_JSON).get(SystemInformationDTO.class);
    assertEquals("CWDS CALS API", systemInformationDTO.getApplication());
    assertNotNull(systemInformationDTO.getVersion());
    assertNotNull(systemInformationDTO.getDeadlocks());

    assertDataSource(systemInformationDTO.getCalsns());
    assertDataSource(systemInformationDTO.getCwscms());
    assertDataSource(systemInformationDTO.getFas());
    assertDataSource(systemInformationDTO.getLis());
  }

  public void assertDataSource(HealthCheckResultDTO healthCheckResultDTO) {
    assertNotNull(healthCheckResultDTO);
    assertTrue(healthCheckResultDTO.isHealthy());
  }
}