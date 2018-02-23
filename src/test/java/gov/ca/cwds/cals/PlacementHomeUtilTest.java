package gov.ca.cwds.cals;

import gov.ca.cwds.cals.service.dto.rfa.ResidenceDTO;
import gov.ca.cwds.cals.util.Utils;
import org.junit.Assert;
import org.junit.Test;

import static gov.ca.cwds.cals.util.Utils.PlacementHome.WATER_BODY;
import static gov.ca.cwds.cals.util.Utils.PlacementHome.WEAPON_IN_HOME_BODY;

/**
 * @author CWDS CALS API Team
 */

public class PlacementHomeUtilTest {

  @Test
  public void test1() throws Exception {
    ResidenceDTO residenceDto = new ResidenceDTO();
    residenceDto.setWeaponInHome(true);
    residenceDto.setBodyOfWaterExist(true);
    Assert.assertEquals(WATER_BODY + ", " + WEAPON_IN_HOME_BODY,
        Utils.PlacementHome.getHazardsDescription(residenceDto));
  }

  @Test
  public void test2() throws Exception {
    ResidenceDTO residenceDto = new ResidenceDTO();
    residenceDto.setWeaponInHome(true);
    residenceDto.setBodyOfWaterExist(false);
    Assert
        .assertEquals(WEAPON_IN_HOME_BODY, Utils.PlacementHome.getHazardsDescription(residenceDto));
  }

  @Test
  public void test3() throws Exception {
    ResidenceDTO residenceDto = new ResidenceDTO();
    residenceDto.setWeaponInHome(false);
    residenceDto.setBodyOfWaterExist(true);
    Assert.assertEquals(WATER_BODY, Utils.PlacementHome.getHazardsDescription(residenceDto));
  }

  @Test
  public void test4() throws Exception {
    ResidenceDTO residenceDto = new ResidenceDTO();
    residenceDto.setWeaponInHome(false);
    residenceDto.setBodyOfWaterExist(false);
    Assert.assertEquals(" ", Utils.PlacementHome.getHazardsDescription(residenceDto));
  }


}