package gov.ca.cwds.cals.service.dto.system;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SystemInformationDTO extends BaseDTO {

  private String application;
  private String version;
  private String buildNumber;
  private HealthCheckResultDTO calsns;
  private HealthCheckResultDTO cwscms;
  private HealthCheckResultDTO fas;
  private HealthCheckResultDTO lis;
  private HealthCheckResultDTO deadlocks;

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBuildNumber() {
    return buildNumber;
  }

  public void setBuildNumber(String buildNumber) {
    this.buildNumber = buildNumber;
  }

  public HealthCheckResultDTO getCalsns() {
    return calsns;
  }

  public void setCalsns(HealthCheckResultDTO calsns) {
    this.calsns = calsns;
  }

  public HealthCheckResultDTO getCwscms() {
    return cwscms;
  }

  public void setCwscms(HealthCheckResultDTO cwscms) {
    this.cwscms = cwscms;
  }

  public HealthCheckResultDTO getFas() {
    return fas;
  }

  public void setFas(HealthCheckResultDTO fas) {
    this.fas = fas;
  }

  public HealthCheckResultDTO getLis() {
    return lis;
  }

  public void setLis(HealthCheckResultDTO lis) {
    this.lis = lis;
  }

  public HealthCheckResultDTO getDeadlocks() {
    return deadlocks;
  }

  public void setDeadlocks(HealthCheckResultDTO deadlocks) {
    this.deadlocks = deadlocks;
  }
}
