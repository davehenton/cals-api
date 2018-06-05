package gov.ca.cwds.cals.persistence.dao.fas;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

/**
 * @author CWDS CALS API Team
 */
public class InspectionDaoTest {

  @Test
  public void formatFacilityNumber() throws Exception {
    int facNum = 678;
    String expectedFacNumStr = "000000678";
    assertEquals(expectedFacNumStr, InspectionDao.formatFacilityNumber(facNum));
  }

}