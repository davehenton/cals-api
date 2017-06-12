package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A StateType.
 */
@Entity
@Table(name = "state_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StateType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Dictionary dictionary;

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CountyType> counties = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public StateType dictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Set<CountyType> getCounties() {
        return counties;
    }

    public StateType counties(Set<CountyType> countyTypes) {
        this.counties = countyTypes;
        return this;
    }

    public StateType addCounties(CountyType countyType) {
        this.counties.add(countyType);
        countyType.setState(this);
        return this;
    }

    public StateType removeCounties(CountyType countyType) {
        this.counties.remove(countyType);
        countyType.setState(null);
        return this;
    }

    public void setCounties(Set<CountyType> countyTypes) {
        this.counties = countyTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StateType stateType = (StateType) o;
        if (stateType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stateType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StateType{" +
            "id=" + getId() +
            "}";
    }
}
