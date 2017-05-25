package gov.ca.cwds.cals.service.dto;

import java.util.Objects;

/**
 * A DTO for the PersonAddress entity.
 */

public class PersonAddressDTO extends BaseDTO {

    private Long id;

    private Long personId;

    private Long typeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long addressTypeId) {
        this.typeId = addressTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonAddressDTO personAddressDTO = (PersonAddressDTO) o;

        return Objects.equals(id, personAddressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
