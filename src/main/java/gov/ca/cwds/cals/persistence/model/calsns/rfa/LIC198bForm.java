package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity.PARAM_ENTITY_ID;
import static gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity.PARAM_FORM_ID;

import gov.ca.cwds.cals.service.dto.rfa.lic198b.LIC198bFormDTO;
import gov.ca.cwds.data.persistence.PersistentObject;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

/**
 * @author CWDS TPT-2 Team
 */

@NamedQuery(
    name = LIC198bForm.NAMED_QUERY_FIND_ALL_BY_FORM,
    query = "FROM LIC198bForm f WHERE f.formId = :" + PARAM_FORM_ID
)
@NamedQuery(
    name = LIC198bForm.NAMED_QUERY_FIND_BY_FORMA_ID_AND_FORMB_ID,
    query =
        "FROM LIC198bForm f WHERE f.formId = :"
            + PARAM_FORM_ID
            + " AND f.id = :"
            + PARAM_ENTITY_ID
)

@Entity
@Table(name = "lic_198b")
public class LIC198bForm extends RFAExternalEntity<LIC198bFormDTO> implements PersistentObject {

  private static final long serialVersionUID = -2741917901843591475L;

  public static final String NAMED_QUERY_FIND_ALL_BY_FORM =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm.find.all.forFormId";
  public static final String NAMED_QUERY_FIND_BY_FORMA_ID_AND_FORMB_ID =
      "gov.ca.cwds.cals.persistence.model.calsns.rfa.LIC198bForm.find.by.id";

  @OneToOne(mappedBy = "lic198bForm")
  private RFA1aApplicant applicant;

  @OneToOne(mappedBy = "lic198bForm")
  private RFA1aOtherAdult otherAdult;

  @Type(type = "LIC198bFormJsonType")
  private LIC198bFormDTO form;


  public RFA1aApplicant getApplicant() {
    return applicant;
  }

  public void setApplicant(RFA1aApplicant applicant) {
    this.applicant = applicant;
  }

  public RFA1aOtherAdult getOtherAdult() {
    return otherAdult;
  }

  public void setOtherAdult(RFA1aOtherAdult otherAdult) {
    this.otherAdult = otherAdult;
  }

  public LIC198bFormDTO getForm() {
    return form;
  }

  public void setForm(LIC198bFormDTO form) {
    this.form = form;
  }

  @Override
  public LIC198bFormDTO getEntityDTO() {
    return form;
  }

  @Override
  public void setEntityDTO(LIC198bFormDTO entityDTO) {
    this.form = entityDTO;
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
