package gov.ca.cwds.cals.service.dto;

import java.util.Objects;

/**
 * A DTO for the PersonEthnicity entity.
 */

public class PersonEthnicityDTO extends BaseDTO {

    private Long id;

    private Long ethnicityId;

    private Long subEthnicityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEthnicityId() {
        return ethnicityId;
    }

    public void setEthnicityId(Long ethnicityTypeId) {
        this.ethnicityId = ethnicityTypeId;
    }

    public Long getSubEthnicityId() {
        return subEthnicityId;
    }

    public void setSubEthnicityId(Long ethnicityTypeId) {
        this.subEthnicityId = ethnicityTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonEthnicityDTO personEthnicityDTO = (PersonEthnicityDTO) o;

        return Objects.equals(id, personEthnicityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
