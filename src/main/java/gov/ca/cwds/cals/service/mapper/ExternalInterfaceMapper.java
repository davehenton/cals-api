package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.cms.ExternalInterface;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper
public interface ExternalInterfaceMapper {
  @Mapping(target = "seqNo", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "submtlTs", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "tableName", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "operType", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey1", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey2", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey3", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey4", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey5", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey6", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey7", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "prmKey8", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "startDt", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "astUntCd", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "personNo", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "serialNo", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "aidTpc", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "gvrEntc", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "licenseNo", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "responsDt", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "receiveDt", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "rapId", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "fbiInd", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "crspTpc", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "otherData", ignore = true)// TODO: 8/17/2017
  @Mapping(target = "lUserid", ignore = true)// TODO: 8/17/2017
  ExternalInterface toExternalInterface(String stub);
}
