package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ApplicantToChildRelation.
 */
@Entity
@Table(name = "applicant_to_child_relation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicantToChildRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "create_user_id", length = 50, nullable = false)
    private String createUserId;

    @NotNull
    @Column(name = "create_date_time", nullable = false)
    private ZonedDateTime createDateTime;

    @NotNull
    @Size(max = 50)
    @Column(name = "update_user_id", length = 50, nullable = false)
    private String updateUserId;

    @NotNull
    @Column(name = "update_date_time", nullable = false)
    private ZonedDateTime updateDateTime;

    @OneToOne
    @JoinColumn(unique = true)
    private RelationshipType relationtype;

    @ManyToOne
    private Applicant applicant;

    @ManyToOne
    private HouseholdChild child;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public ApplicantToChildRelation createUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public ZonedDateTime getCreateDateTime() {
        return createDateTime;
    }

    public ApplicantToChildRelation createDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public void setCreateDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public ApplicantToChildRelation updateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public ZonedDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public ApplicantToChildRelation updateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

    public void setUpdateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public RelationshipType getRelationtype() {
        return relationtype;
    }

    public ApplicantToChildRelation relationtype(RelationshipType relationshipType) {
        this.relationtype = relationshipType;
        return this;
    }

    public void setRelationtype(RelationshipType relationshipType) {
        this.relationtype = relationshipType;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public ApplicantToChildRelation applicant(Applicant applicant) {
        this.applicant = applicant;
        return this;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public HouseholdChild getChild() {
        return child;
    }

    public ApplicantToChildRelation child(HouseholdChild householdChild) {
        this.child = householdChild;
        return this;
    }

    public void setChild(HouseholdChild householdChild) {
        this.child = householdChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicantToChildRelation applicantToChildRelation = (ApplicantToChildRelation) o;
        if (applicantToChildRelation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicantToChildRelation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicantToChildRelation{" +
            "id=" + getId() +
            ", createUserId='" + getCreateUserId() + "'" +
            ", createDateTime='" + getCreateDateTime() + "'" +
            ", updateUserId='" + getUpdateUserId() + "'" +
            ", updateDateTime='" + getUpdateDateTime() + "'" +
            "}";
    }
}
