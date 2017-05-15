package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the FacilityChild entity.
 */

public class FacilityChildDTO extends BaseDTO implements Request, Response {

    private Long id;

    private LocalDate dateOfPlacement;

    private PersonDTO assignedWorker;

    @NotNull
    private String countyOfOrigin;

    private Long facilityId;

    private PersonDTO person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDateOfPlacement() {
        return dateOfPlacement;
    }

    public void setDateOfPlacement(LocalDate dateOfPlacement) {
        this.dateOfPlacement = dateOfPlacement;
    }

    public PersonDTO getAssignedWorker() {
        return assignedWorker;
    }

    public void setAssignedWorker(PersonDTO assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    public String getCountyOfOrigin() {
        return countyOfOrigin;
    }

    public void setCountyOfOrigin(String countyOfOrigin) {
        this.countyOfOrigin = countyOfOrigin;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacilityChildDTO facilityChildDTO = (FacilityChildDTO) o;

        if ( ! Objects.equals(id, facilityChildDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
