package gov.ca.cwds.cals.web.rest.system;

import static gov.ca.cwds.cals.Constants.API.SYSTEM_INFORMATION;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Constants.UnitOfWork;
import gov.ca.cwds.cals.service.dto.system.HealthCheckResultDTO;
import gov.ca.cwds.cals.service.dto.system.SystemInformationDTO;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.InputStream;
import java.util.Properties;
import java.util.SortedMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
@Api(value = SYSTEM_INFORMATION)
@Path(SYSTEM_INFORMATION)
@Produces(MediaType.APPLICATION_JSON)
public class SystemInformationResource {

  private static final Logger LOG = LoggerFactory.getLogger(SystemInformationResource.class);

  private static final String VERSION_PROPERTIES_FILE = "version.properties";
  private static final String BUILD_VERSION = "build.version";
  private static final String BUILD_NUMBER = "build.number";

  private String applicationName;
  private Environment environment;
  private String version;
  private String buildNumber;

  /**
   * Constructor
   *
   * @param applicationName The name of the application
   */
  @Inject
  public SystemInformationResource(@Named("app.name") String applicationName,
      Environment environment) {
    this.applicationName = applicationName;
    this.environment = environment;
    Properties versionProperties = getVersionProperties();
    this.version = versionProperties.getProperty(BUILD_VERSION);
    this.buildNumber = versionProperties.getProperty(BUILD_NUMBER);
  }

  private Properties getVersionProperties() {
    Properties versionProperties = new Properties();
    try {
      InputStream is = ClassLoader.getSystemResourceAsStream(VERSION_PROPERTIES_FILE);
      versionProperties.load(is);
    } catch (Exception e) {
      LOG.error("Can't read version.properties", e);
    }
    return versionProperties;
  }

  /**
   * Get the name of the application.
   *
   * @return the application data
   */
  @GET
  @ApiOperation(value = "Returns System Information", response = SystemInformationDTO.class)
  public SystemInformationDTO get() {
    SystemInformationDTO systemInformationDTO = new SystemInformationDTO();
    systemInformationDTO.setApplication(applicationName);
    systemInformationDTO.setVersion(version);
    systemInformationDTO.setBuildNumber(buildNumber);

    SortedMap<String, HealthCheck.Result> healthChecks = environment.healthChecks()
        .runHealthChecks();
    systemInformationDTO
        .setCalsns(getHealthCheckResultDTO(healthChecks.get(Constants.UnitOfWork.CALSNS)));
    systemInformationDTO
        .setCwscms(getHealthCheckResultDTO(healthChecks.get(Constants.UnitOfWork.CMS)));
    systemInformationDTO
        .setFas(getHealthCheckResultDTO(healthChecks.get(Constants.UnitOfWork.FAS)));

    systemInformationDTO.setFasFfa(
        getHealthCheckResultDTO(healthChecks.get(UnitOfWork.FAS_FFA)));
    systemInformationDTO
        .setLis(getHealthCheckResultDTO(healthChecks.get(Constants.UnitOfWork.LIS)));
    systemInformationDTO.setDeadlocks(getHealthCheckResultDTO(healthChecks.get("deadlocks")));

    return systemInformationDTO;
  }

  public HealthCheckResultDTO getHealthCheckResultDTO(HealthCheck.Result result) {
    HealthCheckResultDTO healthCheckResultDTO = new HealthCheckResultDTO();
    healthCheckResultDTO.setResult(result);
    return healthCheckResultDTO;
  }
}
