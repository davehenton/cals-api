package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@NamedQuery(
    name = "ReplicatedPlacementHome.findUpdated",
    query = "SELECT ph FROM ReplicatedPlacementHome ph "
      + " WHERE ph.licenseNo IS NULL AND ph.replicationDate > ?"
)
@Entity
@javax.persistence.Table(name = "PLC_HM_T")
public class ReplicatedPlacementHome extends BasePlacementHome {

  private String replicationOperation;

  private Date replicationDate;

  private ReplicatedCountyLicenseCase countyLicenseCase;

  @Column(name = "IBMSNAP_OPERATION", updatable = false)
  public String getReplicationOperation() {
    return replicationOperation;
  }

  public void setReplicationOperation(String replicationOperation) {
    this.replicationOperation = replicationOperation;
  }

  @Type(type = "timestamp")
  @Column(name = "IBMSNAP_LOGMARKER", updatable = false)
  public Date getReplicationDate() {
    return replicationDate;
  }

  public void setReplicationDate(Date replicationDate) {
    this.replicationDate = replicationDate;
  }

  @Override
  @NotFound(action = NotFoundAction.IGNORE)
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FKCNTY_CST", referencedColumnName = "IDENTIFIER")
  public ReplicatedCountyLicenseCase getCountyLicenseCase() {
    return countyLicenseCase;
  }

  public void setCountyLicenseCase(ReplicatedCountyLicenseCase countyLicenseCase) {
    this.countyLicenseCase = countyLicenseCase;
  }
}
