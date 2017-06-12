package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
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
 * A CountyType.
 */
@Entity
@Table(name = "county_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CountyType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "fips_code", length = 3, nullable = false)
    private String fipsCode;

    @OneToOne
    @JoinColumn(unique = true)
    private Dictionary dictionary;

    @ManyToOne
    private StateType state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public CountyType fipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
        return this;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public CountyType dictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public StateType getState() {
        return state;
    }

    public CountyType state(StateType stateType) {
        this.state = stateType;
        return this;
    }

    public void setState(StateType stateType) {
        this.state = stateType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountyType countyType = (CountyType) o;
        if (countyType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), countyType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CountyType{" +
            "id=" + getId() +
            ", fipsCode='" + getFipsCode() + "'" +
            "}";
    }
}
