package gov.ca.cwds.cals.persistence.model.lisfas;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;

/**
 * @author CWDS CALS API Team
 */
@NamedQuery(name = LisTableFile.FIND_COUNTY_QUERY,
    query = "FROM LisTableFile WHERE tblCoNbr = :" + LisTableFile.FIND_COUNTY_QUERY_PARAM_NAME)
@NamedQuery(name = LisTableFile.FIND_FACILITY_STATUS_QUERY,
    query = "FROM LisTableFile WHERE tblFacStatusCode = :" + LisTableFile.FIND_FACILITY_STATUS_QUERY_PARAM_NAME)
@NamedQuery(name = LisTableFile.FIND_FACILITY_TYPE_QUERY,
    query = "FROM LisTableFile WHERE tblFacTypeCode = :" + LisTableFile.FIND_FACILITY_TYPE_QUERY_PARAM_NAME)
@NamedQuery(name = LisTableFile.FIND_VISIT_REASON_QUERY,
    query = "FROM LisTableFile WHERE tblVisitReasonCode = :" + LisTableFile.FIND_VISIT_REASON_QUERY_PARAM_NAME)
@Entity
@Table(name = "lis_table_file")
public class LisTableFile implements PersistentObject {

  private static final long serialVersionUID = 3362198377330883827L;
  public static final String FIND_COUNTY_QUERY_PARAM_NAME = "countyCode";
  public static final String FIND_COUNTY_QUERY_SUFFIX = ".findCounty";
  public static final String LIS_TABLE_FILE = "LisTableFile";

  public static final String FIND_COUNTY_QUERY = LIS_TABLE_FILE + FIND_COUNTY_QUERY_SUFFIX;

  public static final String FIND_FACILITY_TYPE_QUERY_PARAM_NAME = "facilityTypeCode";
  public static final String FIND_FACILITY_TYPE_QUERY_SUFFIX = ".findFacilityType";
  public static final String FIND_FACILITY_TYPE_QUERY =
      LIS_TABLE_FILE + FIND_FACILITY_TYPE_QUERY_SUFFIX;

  public static final String FIND_FACILITY_STATUS_QUERY_PARAM_NAME = "facilityStatusCode";
  public static final String FIND_FACILITY_STATUS_QUERY_SUFFIX = ".findFacilityStatus";
  public static final String FIND_FACILITY_STATUS_QUERY =
      LIS_TABLE_FILE + FIND_FACILITY_STATUS_QUERY_SUFFIX;

  public static final String FIND_VISIT_REASON_QUERY_PARAM_NAME = "visitReasonCode";
  public static final String FIND_VISIT_REASON_QUERY_SUFFIX = ".findVisitReason";
  public static final String FIND_VISIT_REASON_QUERY =
      LIS_TABLE_FILE + FIND_VISIT_REASON_QUERY_SUFFIX;

  private Integer isnLisTableFile;
  private Integer tblCoNbr;
  private String tblCoDesc;
  private Integer tblFacStatusCode;
  private String tblFacStatusDesc;
  private Integer tblFacTypeCode;
  private String tblFacTypeDesc;
  private Integer tblVisitReasonCode;
  private String tblVisitReasonDesc;

  @Id
  @Column(name = "isn_lis_table_file", nullable = false)
  public Integer getIsnLisTableFile() {
    return isnLisTableFile;
  }

  public void setIsnLisTableFile(Integer isnLisTableFile) {
    this.isnLisTableFile = isnLisTableFile;
  }

  @Basic
  @Column(name = "tbl_co_nbr")
  public Integer getTblCoNbr() {
    return tblCoNbr;
  }

  public void setTblCoNbr(Integer tblCoNbr) {
    this.tblCoNbr = tblCoNbr;
  }

  @Basic
  @Column(name = "tbl_co_desc", length = 20)
  public String getTblCoDesc() {
    return tblCoDesc;
  }

  public void setTblCoDesc(String tblCoDesc) {
    this.tblCoDesc = tblCoDesc;
  }

  @Basic
  @Column(name = "tbl_fac_status_code")
  public Integer getTblFacStatusCode() {
    return tblFacStatusCode;
  }

  public void setTblFacStatusCode(Integer tblFacStatusCode) {
    this.tblFacStatusCode = tblFacStatusCode;
  }

  @Basic
  @Column(name = "tbl_fac_status_desc", length = 70)
  public String getTblFacStatusDesc() {
    return tblFacStatusDesc;
  }

  public void setTblFacStatusDesc(String tblFacStatusDesc) {
    this.tblFacStatusDesc = tblFacStatusDesc;
  }

  @Basic
  @Column(name = "tbl_fac_type_code")
  public Integer getTblFacTypeCode() {
    return tblFacTypeCode;
  }

  public void setTblFacTypeCode(Integer tblFacTypeCode) {
    this.tblFacTypeCode = tblFacTypeCode;
  }

  @Basic
  @Column(name = "tbl_fac_type_desc", length = 70)
  public String getTblFacTypeDesc() {
    return tblFacTypeDesc;
  }

  public void setTblFacTypeDesc(String tblFacTypeDesc) {
    this.tblFacTypeDesc = tblFacTypeDesc;
  }

  @Basic
  @Column(name = "tbl_visit_reason_code", nullable = true)
  public Integer getTblVisitReasonCode() {
    return tblVisitReasonCode;
  }

  public void setTblVisitReasonCode(Integer tblVisitReasonCode) {
    this.tblVisitReasonCode = tblVisitReasonCode;
  }

  @Basic
  @Column(name = "tbl_visit_reason_desc", nullable = true, length = 70)
  public String getTblVisitReasonDesc() {
    return tblVisitReasonDesc;
  }

  public void setTblVisitReasonDesc(String tblVisitReasonDesc) {
    this.tblVisitReasonDesc = tblVisitReasonDesc;
  }

  @Override
  @Transient
  public Serializable getPrimaryKey() {
    return getIsnLisTableFile();
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

}
