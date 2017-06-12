package gov.ca.cwds.cals.persistence.model.calsns;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class AgeGroupTypeTest {

  @Test
  public void equalsTest() throws Exception {

    AgeGroupType ageGroupType1 = new AgeGroupType();
    ageGroupType1.setId(1L);
    ageGroupType1.setName("name");

    AgeGroupType ageGroupType2 = new AgeGroupType();
    ageGroupType2.setId(1L);
    ageGroupType2.setName("name");

    Assert.assertEquals(ageGroupType1, ageGroupType2);

  }

}