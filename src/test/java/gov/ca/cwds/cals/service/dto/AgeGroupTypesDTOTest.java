package gov.ca.cwds.cals.service.dto;

import gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType;
import java.util.ArrayList;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class AgeGroupTypesDTOTest {

  @Test
  public void equalsTest() throws Exception {

    AgeGroupTypesDTO ageGroupTypesDTO1 = new AgeGroupTypesDTO();
    ArrayList<AgeGroupType> ageGroupTypesDTOS1 = new ArrayList<>();

    AgeGroupType type1 = new AgeGroupType();
    type1.setId(1L);
    type1.setName("name");
    ageGroupTypesDTOS1.add(type1);

    AgeGroupType type2 = new AgeGroupType();
    type2.setId(2L);
    type2.setName("name2");
    ageGroupTypesDTOS1.add(type2);

    ageGroupTypesDTO1.setAgeGroupTypes(ageGroupTypesDTOS1);

    AgeGroupTypesDTO ageGroupTypesDTO2 = new AgeGroupTypesDTO();
    ArrayList<AgeGroupType> ageGroupTypesDTOS2 = new ArrayList<>();

    type1 = new AgeGroupType();
    type1.setId(1L);
    type1.setName("name");
    ageGroupTypesDTOS2.add(type1);

    type2 = new AgeGroupType();
    type2.setId(2L);
    type2.setName("name2");
    ageGroupTypesDTOS2.add(type2);

    ageGroupTypesDTO2.setAgeGroupTypes(ageGroupTypesDTOS2);

  }

}