package gov.ca.cwds.cals.service.dto;

/**
 * @author CWDS CALS API Team
 */
public class CountyDTO {

    private String id;

    private String shortDescription;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountyDTO countyDTO = (CountyDTO) o;

        if (id != null ? !id.equals(countyDTO.id) : countyDTO.id != null) return false;
        return shortDescription != null ? shortDescription.equals(countyDTO.shortDescription) : countyDTO.shortDescription == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        return result;
    }
}
