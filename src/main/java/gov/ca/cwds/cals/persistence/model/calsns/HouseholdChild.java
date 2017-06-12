package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HouseholdChild.
 */
@Entity
@Table(name = "household_child")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HouseholdChild implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "is_financially_supported", nullable = false)
    private Boolean isFinanciallySupported;

    @NotNull
    @Column(name = "is_adopted", nullable = false)
    private Boolean isAdopted;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

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
    private GenderType gender;

    @OneToMany(mappedBy = "child")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicantToChildRelation> relationsToApplicants = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Household household;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsFinanciallySupported() {
        return isFinanciallySupported;
    }

    public HouseholdChild isFinanciallySupported(Boolean isFinanciallySupported) {
        this.isFinanciallySupported = isFinanciallySupported;
        return this;
    }

    public void setIsFinanciallySupported(Boolean isFinanciallySupported) {
        this.isFinanciallySupported = isFinanciallySupported;
    }

    public Boolean isIsAdopted() {
        return isAdopted;
    }

    public HouseholdChild isAdopted(Boolean isAdopted) {
        this.isAdopted = isAdopted;
        return this;
    }

    public void setIsAdopted(Boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public HouseholdChild dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public HouseholdChild createUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public ZonedDateTime getCreateDateTime() {
        return createDateTime;
    }

    public HouseholdChild createDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public void setCreateDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public HouseholdChild updateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public ZonedDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public HouseholdChild updateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

    public void setUpdateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public GenderType getGender() {
        return gender;
    }

    public HouseholdChild gender(GenderType genderType) {
        this.gender = genderType;
        return this;
    }

    public void setGender(GenderType genderType) {
        this.gender = genderType;
    }

    public Set<ApplicantToChildRelation> getRelationsToApplicants() {
        return relationsToApplicants;
    }

    public HouseholdChild relationsToApplicants(Set<ApplicantToChildRelation> applicantToChildRelations) {
        this.relationsToApplicants = applicantToChildRelations;
        return this;
    }

    public HouseholdChild addRelationsToApplicant(ApplicantToChildRelation applicantToChildRelation) {
        this.relationsToApplicants.add(applicantToChildRelation);
        applicantToChildRelation.setChild(this);
        return this;
    }

    public HouseholdChild removeRelationsToApplicant(ApplicantToChildRelation applicantToChildRelation) {
        this.relationsToApplicants.remove(applicantToChildRelation);
        applicantToChildRelation.setChild(null);
        return this;
    }

    public void setRelationsToApplicants(Set<ApplicantToChildRelation> applicantToChildRelations) {
        this.relationsToApplicants = applicantToChildRelations;
    }

    public Household getHousehold() {
        return household;
    }

    public HouseholdChild household(Household household) {
        this.household = household;
        return this;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseholdChild householdChild = (HouseholdChild) o;
        if (householdChild.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), householdChild.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HouseholdChild{" +
            "id=" + getId() +
            ", isFinanciallySupported='" + isIsFinanciallySupported() + "'" +
            ", isAdopted='" + isIsAdopted() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", createUserId='" + getCreateUserId() + "'" +
            ", createDateTime='" + getCreateDateTime() + "'" +
            ", updateUserId='" + getUpdateUserId() + "'" +
            ", updateDateTime='" + getUpdateDateTime() + "'" +
            "}";
    }
}
