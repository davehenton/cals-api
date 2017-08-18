package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.cms.CountyOwnership;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@Mapper(imports={Utils.class, Constants.class})
public interface CountyOwnershipMapper {
  @Mapping(target = "entityId", source = "entityId")
  @Mapping(target = "entityCd", source = "discriminator")
  @Mapping(target = "multiFlg", ignore = true)
  @Mapping(target = "cty00Flg", expression = "java(Utils.County.getFlag(counties, 99, Constants.Y, Constants.N))")
  @Mapping(target = "cty01Flg", expression = "java(Utils.County.getFlag(counties, 1, Constants.Y, Constants.N))")
  @Mapping(target = "cty02Flg", expression = "java(Utils.County.getFlag(counties, 2, Constants.Y, Constants.N))")
  @Mapping(target = "cty03Flg", expression = "java(Utils.County.getFlag(counties, 3, Constants.Y, Constants.N))")
  @Mapping(target = "cty04Flg", expression = "java(Utils.County.getFlag(counties, 4, Constants.Y, Constants.N))")
  @Mapping(target = "cty05Flg", expression = "java(Utils.County.getFlag(counties, 5, Constants.Y, Constants.N))")
  @Mapping(target = "cty06Flg", expression = "java(Utils.County.getFlag(counties, 6, Constants.Y, Constants.N))")
  @Mapping(target = "cty07Flg", expression = "java(Utils.County.getFlag(counties, 7, Constants.Y, Constants.N))")
  @Mapping(target = "cty08Flg", expression = "java(Utils.County.getFlag(counties, 8, Constants.Y, Constants.N))")
  @Mapping(target = "cty09Flg", expression = "java(Utils.County.getFlag(counties, 9, Constants.Y, Constants.N))")
  @Mapping(target = "cty10Flg", expression = "java(Utils.County.getFlag(counties, 10, Constants.Y, Constants.N))")
  @Mapping(target = "cty11Flg", expression = "java(Utils.County.getFlag(counties, 11, Constants.Y, Constants.N))")
  @Mapping(target = "cty12Flg", expression = "java(Utils.County.getFlag(counties, 12, Constants.Y, Constants.N))")
  @Mapping(target = "cty13Flg", expression = "java(Utils.County.getFlag(counties, 13, Constants.Y, Constants.N))")
  @Mapping(target = "cty14Flg", expression = "java(Utils.County.getFlag(counties, 14, Constants.Y, Constants.N))")
  @Mapping(target = "cty15Flg", expression = "java(Utils.County.getFlag(counties, 15, Constants.Y, Constants.N))")
  @Mapping(target = "cty16Flg", expression = "java(Utils.County.getFlag(counties, 16, Constants.Y, Constants.N))")
  @Mapping(target = "cty17Flg", expression = "java(Utils.County.getFlag(counties, 17, Constants.Y, Constants.N))")
  @Mapping(target = "cty18Flg", expression = "java(Utils.County.getFlag(counties, 18, Constants.Y, Constants.N))")
  @Mapping(target = "cty19Flg", expression = "java(Utils.County.getFlag(counties, 19, Constants.Y, Constants.N))")
  @Mapping(target = "cty20Flg", expression = "java(Utils.County.getFlag(counties, 20, Constants.Y, Constants.N))")
  @Mapping(target = "cty21Flg", expression = "java(Utils.County.getFlag(counties, 21, Constants.Y, Constants.N))")
  @Mapping(target = "cty22Flg", expression = "java(Utils.County.getFlag(counties, 22, Constants.Y, Constants.N))")
  @Mapping(target = "cty23Flg", expression = "java(Utils.County.getFlag(counties, 23, Constants.Y, Constants.N))")
  @Mapping(target = "cty24Flg", expression = "java(Utils.County.getFlag(counties, 24, Constants.Y, Constants.N))")
  @Mapping(target = "cty25Flg", expression = "java(Utils.County.getFlag(counties, 25, Constants.Y, Constants.N))")
  @Mapping(target = "cty26Flg", expression = "java(Utils.County.getFlag(counties, 26, Constants.Y, Constants.N))")
  @Mapping(target = "cty27Flg", expression = "java(Utils.County.getFlag(counties, 27, Constants.Y, Constants.N))")
  @Mapping(target = "cty28Flg", expression = "java(Utils.County.getFlag(counties, 28, Constants.Y, Constants.N))")
  @Mapping(target = "cty29Flg", expression = "java(Utils.County.getFlag(counties, 29, Constants.Y, Constants.N))")
  @Mapping(target = "cty30Flg", expression = "java(Utils.County.getFlag(counties, 30, Constants.Y, Constants.N))")
  @Mapping(target = "cty31Flg", expression = "java(Utils.County.getFlag(counties, 31, Constants.Y, Constants.N))")
  @Mapping(target = "cty32Flg", expression = "java(Utils.County.getFlag(counties, 32, Constants.Y, Constants.N))")
  @Mapping(target = "cty33Flg", expression = "java(Utils.County.getFlag(counties, 33, Constants.Y, Constants.N))")
  @Mapping(target = "cty34Flg", expression = "java(Utils.County.getFlag(counties, 34, Constants.Y, Constants.N))")
  @Mapping(target = "cty35Flg", expression = "java(Utils.County.getFlag(counties, 35, Constants.Y, Constants.N))")
  @Mapping(target = "cty36Flg", expression = "java(Utils.County.getFlag(counties, 36, Constants.Y, Constants.N))")
  @Mapping(target = "cty37Flg", expression = "java(Utils.County.getFlag(counties, 37, Constants.Y, Constants.N))")
  @Mapping(target = "cty38Flg", expression = "java(Utils.County.getFlag(counties, 38, Constants.Y, Constants.N))")
  @Mapping(target = "cty39Flg", expression = "java(Utils.County.getFlag(counties, 39, Constants.Y, Constants.N))")
  @Mapping(target = "cty40Flg", expression = "java(Utils.County.getFlag(counties, 40, Constants.Y, Constants.N))")
  @Mapping(target = "cty41Flg", expression = "java(Utils.County.getFlag(counties, 41, Constants.Y, Constants.N))")
  @Mapping(target = "cty42Flg", expression = "java(Utils.County.getFlag(counties, 42, Constants.Y, Constants.N))")
  @Mapping(target = "cty43Flg", expression = "java(Utils.County.getFlag(counties, 43, Constants.Y, Constants.N))")
  @Mapping(target = "cty44Flg", expression = "java(Utils.County.getFlag(counties, 44, Constants.Y, Constants.N))")
  @Mapping(target = "cty45Flg", expression = "java(Utils.County.getFlag(counties, 45, Constants.Y, Constants.N))")
  @Mapping(target = "cty46Flg", expression = "java(Utils.County.getFlag(counties, 46, Constants.Y, Constants.N))")
  @Mapping(target = "cty47Flg", expression = "java(Utils.County.getFlag(counties, 47, Constants.Y, Constants.N))")
  @Mapping(target = "cty48Flg", expression = "java(Utils.County.getFlag(counties, 48, Constants.Y, Constants.N))")
  @Mapping(target = "cty49Flg", expression = "java(Utils.County.getFlag(counties, 49, Constants.Y, Constants.N))")
  @Mapping(target = "cty50Flg", expression = "java(Utils.County.getFlag(counties, 50, Constants.Y, Constants.N))")
  @Mapping(target = "cty51Flg", expression = "java(Utils.County.getFlag(counties, 51, Constants.Y, Constants.N))")
  @Mapping(target = "cty52Flg", expression = "java(Utils.County.getFlag(counties, 52, Constants.Y, Constants.N))")
  @Mapping(target = "cty53Flg", expression = "java(Utils.County.getFlag(counties, 53, Constants.Y, Constants.N))")
  @Mapping(target = "cty54Flg", expression = "java(Utils.County.getFlag(counties, 54, Constants.Y, Constants.N))")
  @Mapping(target = "cty55Flg", expression = "java(Utils.County.getFlag(counties, 55, Constants.Y, Constants.N))")
  @Mapping(target = "cty56Flg", expression = "java(Utils.County.getFlag(counties, 56, Constants.Y, Constants.N))")
  @Mapping(target = "cty57Flg", expression = "java(Utils.County.getFlag(counties, 57, Constants.Y, Constants.N))")
  @Mapping(target = "cty58Flg", expression = "java(Utils.County.getFlag(counties, 58, Constants.Y, Constants.N))")
  @Mapping(target = "cty59Flg", constant = Constants.N)
  @Mapping(target = "cty60Flg", constant = Constants.N)
  @Mapping(target = "cty61Flg", constant = Constants.N)
  @Mapping(target = "cty62Flg", constant = Constants.N)
  @Mapping(target = "cty63Flg", constant = Constants.N)
  @Mapping(target = "deleteDt", ignore = true)
  CountyOwnership toCountyOwnership(String entityId, String discriminator, List<CountyType> counties);

  @AfterMapping
  default void afterMapping(@MappingTarget CountyOwnership countyOwnership,
      String entityId, String discriminator) {
    int yFlagsCount = 0;
    for (Method method : countyOwnership.getClass().getMethods()) {
      if(method.getName().startsWith("getCty")) {
        String flag;
        try {
          flag = (String) method.invoke(countyOwnership);
        } catch (IllegalAccessException | InvocationTargetException e) {
          throw new RuntimeException("Cannot calculate multi flag for county ownership {id: "
              + entityId + ", code: " + discriminator + "}", e);
        }
        if (Constants.Y.equals(flag)) {
          yFlagsCount += 1;
        }
      }
    }
    String multiFlag = yFlagsCount > 1 ? Constants.Y : Constants.N;
    countyOwnership.setMultiFlg(multiFlag);
  }
}
