package gov.ca.cwds.cals.service.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Person entity.
 */

public class PersonDTO extends BaseDTO {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String gender;

    private Integer age;

    private LocalDate dateOfBirth;

    private String ssn;

    private Long ethnicityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getEthnicityId() {
        return ethnicityId;
    }

    public void setEthnicityId(Long personEthnicityId) {
        this.ethnicityId = personEthnicityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonDTO personDTO = (PersonDTO) o;

        if ( ! Objects.equals(id, personDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
