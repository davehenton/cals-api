package gov.ca.cwds.cals.persistence.model.cms.rs;

import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2
 */
@Entity
@Table(name = "CNTY_CST")
public class ReplicatedCountyLicenseCase extends BaseCountyLicenseCase {

  private String replicationOperation;

  private Date replicationDate;

  private List<ReplicatedLicensingVisit> licensingVisits;

  private ReplicatedStaffPerson staffPerson;

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
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "FKCNTY_CST", referencedColumnName = "IDENTIFIER", nullable = true)
  @OrderBy("visitDate DESC")
  public List<ReplicatedLicensingVisit> getLicensingVisits() {
    return licensingVisits;
  }

  public void setLicensingVisits(List<ReplicatedLicensingVisit> licensingVisits) {
    this.licensingVisits = licensingVisits;
  }

  @Override
  @NotFound(action = NotFoundAction.IGNORE)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FKSTFPERST", referencedColumnName = "IDENTIFIER")
  public ReplicatedStaffPerson getStaffPerson() {
    return staffPerson;
  }

  public void setStaffPerson(ReplicatedStaffPerson fkstfperst) {
    this.staffPerson = fkstfperst;
  }
}
