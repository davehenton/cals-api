package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
 * A Applicant.
 */
@Entity
@Table(name = "applicant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Applicant implements Serializable {

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
    private HouseholdAdult householdPerson;

    @OneToOne
    @JoinColumn(unique = true)
    private EducationLevelType educationHighestLevel;

    @OneToMany(mappedBy = "applicant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RelationHistoryRecord> relationHistoryRecords = new HashSet<>();

    @ManyToOne
    private Application application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public Applicant createUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public ZonedDateTime getCreateDateTime() {
        return createDateTime;
    }

    public Applicant createDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public void setCreateDateTime(ZonedDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public Applicant updateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public ZonedDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public Applicant updateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

    public void setUpdateDateTime(ZonedDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public HouseholdAdult getHouseholdPerson() {
        return householdPerson;
    }

    public Applicant householdPerson(HouseholdAdult householdAdult) {
        this.householdPerson = householdAdult;
        return this;
    }

    public void setHouseholdPerson(HouseholdAdult householdAdult) {
        this.householdPerson = householdAdult;
    }

    public EducationLevelType getEducationHighestLevel() {
        return educationHighestLevel;
    }

    public Applicant educationHighestLevel(EducationLevelType educationLevelType) {
        this.educationHighestLevel = educationLevelType;
        return this;
    }

    public void setEducationHighestLevel(EducationLevelType educationLevelType) {
        this.educationHighestLevel = educationLevelType;
    }

    public Set<RelationHistoryRecord> getRelationHistoryRecords() {
        return relationHistoryRecords;
    }

    public Applicant relationHistoryRecords(Set<RelationHistoryRecord> relationHistoryRecords) {
        this.relationHistoryRecords = relationHistoryRecords;
        return this;
    }

    public Applicant addRelationHistoryRecords(RelationHistoryRecord relationHistoryRecord) {
        this.relationHistoryRecords.add(relationHistoryRecord);
        relationHistoryRecord.setApplicant(this);
        return this;
    }

    public Applicant removeRelationHistoryRecords(RelationHistoryRecord relationHistoryRecord) {
        this.relationHistoryRecords.remove(relationHistoryRecord);
        relationHistoryRecord.setApplicant(null);
        return this;
    }

    public void setRelationHistoryRecords(Set<RelationHistoryRecord> relationHistoryRecords) {
        this.relationHistoryRecords = relationHistoryRecords;
    }

    public Application getApplication() {
        return application;
    }

    public Applicant application(Application application) {
        this.application = application;
        return this;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Applicant applicant = (Applicant) o;
        if (applicant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Applicant{" +
            "id=" + getId() +
            ", createUserId='" + getCreateUserId() + "'" +
            ", createDateTime='" + getCreateDateTime() + "'" +
            ", updateUserId='" + getUpdateUserId() + "'" +
            ", updateDateTime='" + getUpdateDateTime() + "'" +
            "}";
    }
}
